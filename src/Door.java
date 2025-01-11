public class Door {
    private Room nextRoom;
    private Direction direction;
    private boolean isLocked;

    enum Direction {
        NORTH,
        EAST,
        WEST,
        SOUTH
    }

    public Door(Room nextRoom, Direction direction, boolean isLocked) {
        this.nextRoom = nextRoom;
        this.direction = direction;
        this.isLocked = isLocked;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    // A helper to unlock the door
    public void setUnlocked() {
        this.isLocked = false;
    }
}
