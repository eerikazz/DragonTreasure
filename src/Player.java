import java.util.ArrayList;

public class Player {
    private final String name;
    private int health;
    private int attackPower;
    private ArrayList<Item> inventory;

    public Player(String name, int health, int attackPower){
        this.name           = name;
        this.health         = health;
        this.attackPower    = attackPower;
        this.inventory      = new ArrayList<>();
    }

    // Returnerar en String med värdet name
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

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.add(item);
    }
}
