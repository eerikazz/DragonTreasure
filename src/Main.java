import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    enum GameState {
        Menu,
        Game,
        End,
        Exit
    }

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static GameState gameState = GameState.Menu;
    private static Scanner input = new Scanner(System.in);
    private static int currentRoom = 0;

    public static void main(String[] args) {
        setupGame();
        createPlayer();
        while (gameState != GameState.Exit) {
            switch (gameState) {
                case Menu:
                    handleMenu();
                    break;

                case Game:
                    if (currentRoom == 9) {
                        gameState = GameState.End;
                    } else {
                        handleGame(currentRoom);
                    }
                    break;

                case End:
                    handleEnd();
                    gameState = GameState.Exit;
                    break;

                default:
                    System.out.println("ERROR: Unknown state!");
                    gameState = GameState.Exit;
            }
        }
    }

    private static void handleMenu() {
        clearScreen();
        System.out.printf("Location: Menu%n%nPress [S] then [Enter] to START.%nPress [E] then [Enter] to EXIT.%n%nInput: ");
        String userInput = input.nextLine().toLowerCase();

        if (userInput.equals("s")) {
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
        new Player(userInput);
        gameState = GameState.Menu;
    }

    private static void handleGame(int roomIndex) {
        clearScreen();
        Room room = rooms.get(roomIndex);
        System.out.printf("Location: Room " + roomIndex + "%n");
        room.doNarrative();
        // System.out.printf("%n- - -%nPress [M] then [Enter] to return to MENU (NO SAVING).%n%nInput: ");
        System.out.printf("%n%nInput: ");
        String userInput = input.nextLine().toLowerCase();

        for (Door door : room.getDoors()) {
            String direction = String.valueOf(door.getDirection().name().charAt(0)).toLowerCase();

            if (userInput.equals(direction)) {
                currentRoom = rooms.indexOf(door.getNextRoom());
            } else if (!userInput.equals("m")) {
                System.out.println("Invalid input.");
            }
        }

        if (userInput.equals("m")) {
            gameState = GameState.Menu;
        }
    }

    private static void handleEnd() {
        System.out.println("You made it out of the cave. Congratulations!");
    }

    private static void setupGame() {
        // Skapa rum
        Room room0 = new Room("Entrance.");
        Room room1 = new Room("Room description.");
        Room room2 = new Room("Room description.");
        Room room3 = new Room("Room description.");
        Room room4 = new Room("Room description.");
        Room room5 = new Room("Room description.");
        Room room6 = new Room("Drake");
        Room room7 = new Room("Room description.");
        Room room8 = new Room("Room description.");
        Room room9 = new Room("Game end");
        rooms.add(room0);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        rooms.add(room7);
        rooms.add(room8);
        rooms.add(room9);

        // Dörr för room 0 
        // ändrat room 1 door till false så mak kan gå vidare
        Door door0 = new Door(room1, Door.Direction.NORTH, false);
        room0.setDoor(door0);

        // Dörrar för rum 1
        Door door1 = new Door(room2, Door.Direction.NORTH, false);
        Door door2 = new Door(room3, Door.Direction.WEST, false);
        room1.setDoor(door1);
        room1.setDoor(door2);

        // Dörrar för rum 2
        Door door3 = new Door(room8, Door.Direction.NORTH, false);
        Door door4 = new Door(room6, Door.Direction.WEST, false);
        room2.setDoor(door3);
        room2.setDoor(door4);

        // Dörrar för rum 3
        Door door5 = new Door(room4, Door.Direction.EAST, false);
        Door door6 = new Door(room5, Door.Direction.WEST, false);
        room3.setDoor(door5);
        room3.setDoor(door6);

        // Dörrar för rum 4
        Door door7 = new Door(room6, Door.Direction.NORTH, false);
        Door back5 = new Door(room3, Door.Direction.SOUTH, false);
        room4.setDoor(door7);
        room4.setDoor(back5);

        // Dörrar för rum 5
        Door back6 = new Door(room3, Door.Direction.SOUTH, false);
        room5.setDoor(door7);
        room5.setDoor(back6);

        // Dörrar för rum 6
        Door door8 = new Door(room9, Door.Direction.NORTH, false); // Drake
        room6.setDoor(door8);

        // Dörrar för rum 7
        Door door9 = new Door(room6, Door.Direction.WEST, false);
        room7.setDoor(door9);

        // Dörrar för rum 8
        Door door10 = new Door(room6, Door.Direction.WEST, false);
        room8.setDoor(door10);
    }

    // Rensar terminalen.
    // KRAV: Terminalen måste ha support för ANSI escape codes.
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
