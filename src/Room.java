import java.util.ArrayList;

public class Room {
    private String description;
    private ArrayList<Door> doors;

    public Room(String description) {
        this.description = description;
        this.doors = new ArrayList<>();
    }

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

    public void setDoor(Door newDoor) {
        doors.add(newDoor);
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }
}
