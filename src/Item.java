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
    int unlockdoor;
}

class Potion extends Item {
    int healing;
}

class Treasure extends Item {
    int goldValue;
}

class weapon extends Item {
    int increaseDamage;
}