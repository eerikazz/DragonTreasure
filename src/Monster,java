// Base class for monsters
public abstract class Monster {
    private String name;
    private int health;
    private int attackPower;

    // Constructor
    public Monster(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    // Common methods
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void attack(Monster target) {
        System.out.println(name + " attacks " + target.getName() + " for " + attackPower + " damage!");
        target.setHealth(target.getHealth() - attackPower);
    }
}

// Dragon class
class Dragon extends Monster {
    public Dragon(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }
}

// Goblin class
class Goblin extends Monster {
    public Goblin(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }
}

// Troll class
class Troll extends Monster {
    public Troll(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }
}

// // Main class demostration
// public class MonsterGame {
//     public static void main(String[] args) {
//         Dragon dragon = new Dragon("Red Dragon", 200, 50);
//         Goblin goblin = new Goblin("Sneaky Goblin", 100, 20);
//         Troll troll = new Troll("Mountain Troll", 150, 40);

//         dragon.attack(goblin);
//         goblin.attack(troll);
//         troll.attack(dragon);

//         System.out.println(goblin.getName() + " has " + goblin.getHealth() + " health left.");
//         System.out.println(troll.getName() + " has " + troll.getHealth() + " health left.");
//         System.out.println(dragon.getName() + " has " + dragon.getHealth() + " health left.");
//     }
// }
