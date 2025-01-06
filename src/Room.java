import java.util.ArrayList;

public class Room {

    private String description;
    private ArrayList<Door> doors;
<<<<<<< Updated upstream

=======
    private ArrayList<Item> items;
>>>>>>> Stashed changes
    // Konstruktor
    public Room(String description) {
        this.description = description;
        this.doors = new ArrayList<>();
    }

    // Metod som skriver ut beskrivning och dörrar
    public void doNarrative() {
        System.out.printf("%n" + description + "%n");

        for (Door door : doors) {
            // Hämta första bokstaven från enum värdet.
            char key = door.getDirection().name().charAt(0);
            if (door.getIsLocked()) {
                System.out.printf("%nThe %s door is locked", door.getDirection().name());
            } else {
                System.out.printf("%nPress [%c] then [Enter] to use the %s DOOR", key, door.getDirection().name());
            }
        }
    }

    // Settermetod som lägger till en dörr i doorslistan
    public void setDoor(Door newDoor) {
        doors.add(newDoor);
    }

    // Gettermetod som returnerar hela listan med dörrar
    public ArrayList<Door> getDoors() {
        return doors;
    }
    // Settermetod som lägger till en item i itemslistan
    public void setItem(Item newItem) {
        items.add(newItem);
    }
    // Gettermetod som returnerar hela listan med items
    public ArrayList<Item> getItems() {
        return items;
    }
}
