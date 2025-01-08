import java.util.ArrayList;

public abstract class Item {
    String name;
    String itemDesc;

    public Item(String name, String description) {
        this.name = name;
        this.itemDesc = description;
    }
}

class Key extends Item {
    Door unlockdoor;

    public Key(String name, String description, Door unlockdoor){
        super(name, description);
        this.unlockdoor = unlockdoor;
    }
}

class Potion extends Item {
    int healing;

    public Potion(String name, String description, int healing){
        super(name, description);
        this.healing = healing;
    }
}

class Treasure extends Item {
    int goldValue;

    public Treasure(String name, String description, int goldValue){
        super(name, description);
        this.goldValue = goldValue;
    }
}

class Weapon extends Item {
    int increaseDamage;

    public Weapon(String name, String description, int increaseDamage){
        super(name, description);
        this.increaseDamage = increaseDamage;
    }
}