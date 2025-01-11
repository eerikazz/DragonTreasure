import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    enum GameState {
        Menu,
        Game,
        End,
        Death,
        Exit
    }

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static GameState gameState = GameState.Menu;
    private static int currentRoom = 0;
    private static int previousRoomIndex;
    private static Player player;

    public static void main(String[] args) {
        setupGame();
        createPlayer();
        while (gameState != GameState.Exit) {
            switch (gameState) {
                case Menu:
                    handleMenu();
                    break;

                case Game:
                    if (currentRoom >= rooms.size()) {
                        gameState = GameState.End;
                    } else {
                        renderRoom(currentRoom);
                    }
                    break;

                case End:
                    handleEnd();
                    gameState = GameState.Exit;
                    break;

                case Death:
                    handleDeath();
                    break;

                default:
                    System.out.println("ERROR: Unknown state!");
                    gameState = GameState.Exit;
            }
        }
    }

    private static void handleMenu() {
        clearScreen();
        System.out.printf(
                "Location: Menu%n%nPress [S] then [Enter] to START.%nPress [E] then [Enter] to EXIT.%n%nInput: "
        );
        String userInput = input.nextLine().toLowerCase();

        if (userInput.equals("s")) {
            currentRoom = 0;
            gameState = GameState.Game;
        } else if (userInput.equals("e")) {
            gameState = GameState.Exit;
        } else {
            System.out.println("Invalid input. Try again.");
        }
    }

    private static void createPlayer() {
        System.out.println("Enter username:");
        String userInput = input.nextLine();
        player = new Player(userInput, 8, 1);
        gameState = GameState.Menu;
    }

    private static void renderRoom(int roomIndex) {
        clearScreen();
        Room room = rooms.get(roomIndex);
        room.doNarrative();

        // 2) If item, take it
        if (room.getItem() != null) {
            Item foundItem = room.getItem();
            System.out.println("\nYou found an item: " + foundItem.name);

            if (foundItem.name.equalsIgnoreCase("sword")) {
                System.out.print("Damage set to 2");
                player.setAttackPower(2);
            }
            else if (foundItem.name.equalsIgnoreCase("potion")) {
                System.out.print("Health set to 8");
                player.setHealth(8);
            }

            player.addItem(foundItem);
            room.setItem(null);  // Remove the item from the room
        }

        // 3) Battle
        Room.BattleOutcome outcome = room.doBattle(player, input);

        switch (outcome) {
            case NO_MONSTER:
                break;

            case MONSTER_DEFEATED:
                room.doNarrative();
                break;

            case PLAYER_FLED:
                currentRoom = previousRoomIndex;
                return;

            case PLAYER_DEAD:
                // Player died
                gameState = GameState.End;
                return;
        }

        // Quick check on the player to see if they died mid-battle
        if (player.getHealth() <= 0) {
            gameState = GameState.Death;
            return;
        }

        while (true) {
            System.out.printf("%nInput: ");
            String userInput = input.nextLine().toLowerCase();

            if (userInput.equals("m")) {
                gameState = GameState.Menu;
                return;
            }

            ArrayList<Door> doors = room.getDoors();
            boolean validInput = false;

            for (Door door : doors) {
                String directionChar = String.valueOf(door.getDirection().name().charAt(0)).toLowerCase();
                if (userInput.equals(directionChar)) {
                    validInput = true;

                    if (door.getIsLocked()) {
                        if (tryToUnlockDoor(door)) {
                            System.out.println("The door unlocks!");
                            previousRoomIndex = currentRoom;
                            currentRoom = rooms.indexOf(door.getNextRoom());
                            return;
                        } else {
                            System.out.println("The door is locked and you have no key!");
                        }
                    } else {
                        previousRoomIndex = currentRoom;
                        currentRoom = rooms.indexOf(door.getNextRoom());
                        return;
                    }
                }
            }

            if (!validInput) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static boolean tryToUnlockDoor(Door door) {
        for (Item item : player.getInventory()) {
            if (item.name.equalsIgnoreCase("key")) {
                door.setUnlocked();
                player.removeItem(item);
                return true;
            }
        }
        return false;
    }

    private static void handleEnd() {
        System.out.println("You made it out of the cave. Congratulations!");
    }

    private static void handleDeath() {
        System.out.println("You died :D");
        gameState = GameState.Menu;
    }

    private static void setupGame() {
        Room entrance   = new Room("Entrance");
        Room room1      = new Room("Room 1");
        Room sword      = new Room("Sword");
        Room potion     = new Room("Potion");
        Room key        = new Room("key");
        Room goblin     = new Room("goblin");
        Room dragon     = new Room("drake");
        Room exit       = new Room("exit");

        Door entrance_Room1 = new Door(room1, Door.Direction.NORTH, false);
        Door room1_sword    = new Door(sword, Door.Direction.WEST, false);
        Door sword_room1    = new Door(room1, Door.Direction.EAST, false);
        Door room1_potion   = new Door(potion, Door.Direction.EAST, false);
        Door potion_room1   = new Door(room1, Door.Direction.WEST, false);
        Door room1_goblin   = new Door(goblin, Door.Direction.NORTH, false);
        Door goblin_room1   = new Door(room1, Door.Direction.SOUTH, false);
        Door goblin_key     = new Door(key, Door.Direction.NORTH, false);
        Door key_goblin     = new Door(goblin, Door.Direction.SOUTH, false);
        Door key_to_dragon  = new Door(dragon, Door.Direction.NORTH, true);
        Door dragon_to_key  = new Door(key, Door.Direction.SOUTH, false);
        Door dragon_to_exit = new Door(exit, Door.Direction.NORTH, false);

        Item swordItem  = new Weapon("Sword", "Damage: 2 points", 1);
        Item potionItem = new Weapon("Potion", "Heals", 0);
        Item keyItem    = new Weapon("key", "Opens locked door", 0);

        Monster goblinCreature = new Goblin("Goblin", 8, 1);
        Monster dragonCreature = new Dragon("Dragon", 16, 1);

        // Create map
        rooms.add(entrance);
        rooms.add(room1);
        rooms.add(sword);
        rooms.add(potion);
        rooms.add(key);
        rooms.add(goblin);
        rooms.add(dragon);
        rooms.add(exit);

        // Add doors
        entrance.setDoor(entrance_Room1);
        room1.setDoor(room1_sword);
        room1.setDoor(room1_potion);
        room1.setDoor(room1_goblin);
        sword.setDoor(sword_room1);
        potion.setDoor(potion_room1);
        goblin.setDoor(goblin_room1);
        goblin.setDoor(goblin_key);
        key.setDoor(key_goblin);
        key.setDoor(key_to_dragon);
        dragon.setDoor(dragon_to_key);
        dragon.setDoor(dragon_to_exit);

        // Add items
        sword.setItem(swordItem);
        potion.setItem(potionItem);
        key.setItem(keyItem);

        // Add monsters
        goblin.setMonster(goblinCreature);
        dragon.setMonster(dragonCreature);
    }

    // Rensar terminalen.
    // KRAV: Terminalen måste ha support för ANSI escape codes.
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
