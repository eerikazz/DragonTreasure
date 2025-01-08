public class Player {
    private final String name;
    private int health;
    private int attackPower;
    // Metod för att skriva in sitt namn
    public Player(String name) {
        this.name = name;
    }
    // Returnerar en String med värdet name
    public String getName() {
        return name;
    }

    public Player(String name, int health, int attackPower){
        this.name = name;
        this.attackPower = attackPower;
        this.health = health;
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
}
