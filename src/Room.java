import java.util.ArrayList;

public class Room {

    private String description;
    private ArrayList<Door> doors;
    private Item item;
    private Monster monster;

    public enum BattleOutcome {
        NO_MONSTER,        // There was no monster in the room
        MONSTER_DEFEATED,  // Monster was killed
        PLAYER_FLED,       // Player fled
        PLAYER_DEAD        // Player died
    }

    public Room(String description) {
        this.description = description;
        this.doors = new ArrayList<>();
        this.item = null;
        this.monster = null;
    }

    public void doNarrative() {
        System.out.printf("%n%s%n", description);

        for (Door door : doors) {
            char key = door.getDirection().name().charAt(0);
            if (door.getIsLocked()) {
                System.out.printf("%nThe %s door is locked", door.getDirection().name());
            } else {
                System.out.printf("%nPress [%c] then [Enter] to use the %s DOOR", key, door.getDirection().name());
            }
        }
    }

    public BattleOutcome doBattle(Player player, java.util.Scanner input) {
        // 1) If this room has no monster, no battle needed
        if (monster == null) {
            return BattleOutcome.NO_MONSTER;
        }

        // 2) If a monster is present, begin combat
        System.out.println("\nA wild " + monster.getName() + " appears!");
        while (true) {
            // If monster has died, the player is victorious
            if (monster.getHealth() <= 0) {
                monster = null; // remove from the room
                return BattleOutcome.MONSTER_DEFEATED;
            }
            // If player died
            if (player.getHealth() <= 0) {
                return BattleOutcome.PLAYER_DEAD;
            }

            System.out.printf("%nPress [A] to Attack or [F] to Flee: ");
            String combatChoice = input.nextLine().trim().toLowerCase();

            switch (combatChoice) {
                case "a":
                    // -- PLAYER ATTACKS --
                    System.out.println(player.getName() + " attacks " + monster.getName()
                            + " for " + player.getAttackPower() + " damage!");
                    monster.setHealth(monster.getHealth() - player.getAttackPower());

                    // Check if monster died from this attack
                    if (monster.getHealth() <= 0) {
                        System.out.println("You defeated the " + monster.getName() + "!");
                        monster = null;
                        return BattleOutcome.MONSTER_DEFEATED;
                    }

                    // Monster attacks back
                    System.out.println(monster.getName() + " attacks " + player.getName()
                            + " for " + monster.getAttackPower() + " damage!");
                    player.setHealth(player.getHealth() - monster.getAttackPower());

                    // Check if player died
                    if (player.getHealth() <= 0) {
                        System.out.println("You have been defeated...");
                        return BattleOutcome.PLAYER_DEAD;
                    }
                    break;

                case "f":
                    // Player flees
                    System.out.println("You attempt to flee...");
                    return BattleOutcome.PLAYER_FLED;

                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    public void setDoor(Door door) {
        doors.add(door);
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
