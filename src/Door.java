public class Door {
    private Room nextRoom;          // Vilket rum är bakom dörren
    private Direction direction;    // Vilken riktning dörren har
    private boolean isLocked;       // Om dörren är låst eller inte

    enum Direction {
        NORTH,
        EAST,
        WEST,
        SOUTH
    }
    // Konstruktor
    public Door(Room nextRoom, Direction direction, boolean isLocked) {
        this.nextRoom = nextRoom;
        this.direction = direction;
        this.isLocked = isLocked;
    }
    // Returnerar värdet på nextRoom
    public Room getNextRoom() {
        return nextRoom;
    }
    // Returnerar värdet på direction
    public Direction getDirection() {
        return direction;
    }
    // Returnerar värdet på isLocked
    public boolean getIsLocked() {
        return isLocked;
    }
}
