import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Skapa enum för att kontrollera gamestate
    enum GameState {
        Menu,
        Game,
        End,
        Exit
    }
    
    private static Scanner input = new Scanner(System.in);
    // Skapa arraylist som innehåller alla rum
    private static ArrayList<Room> rooms = new ArrayList<>();
    // Variabel för att hålla koll på nuvarande gamestate
    private static GameState gameState = GameState.Menu;
    // Variabel som håller koll på vilket är det nuvarande rummet
    private static int currentRoom = 0;

    public static void main(String[] args) {
        setupGame();    // Skapar rum och dörrar
        createPlayer(); // Skapa spelare
        while (gameState != GameState.Exit) { // så länge gamestate inte är exit körs spelet
            switch (gameState) {
                case Menu:
                    // Om gamestate är menu visas meny
                    handleMenu();
                    break;

                case Game:
                    // Om gamestate är game körs metod för att skriva ut rum
                    if (currentRoom == 9) {
                        gameState = GameState.End; // Om spelaren är i rum 9 avslutas spelet
                    } else {
                        renderRoom(currentRoom);   // Annars skrivs rum ut
                    }
                    break;

                case End: // Avslutar spelet
                    handleEnd();
                    gameState = GameState.Exit;
                    break;

                default: // Om gamestate är okänt, avslutas spelet
                    System.out.println("ERROR: Unknown state!");
                    gameState = GameState.Exit;
            }
        }
    }

    // Hanterar menyn
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

    // Metod som skapar spelaren
    private static void createPlayer() {
        System.out.println("Enter username:");
        String userInput = input.nextLine();
        new Player(userInput);
        gameState = GameState.Menu;
    }

    // Metod som skriver ut rum och hanterar navigering
    private static void renderRoom(int roomIndex) {
        clearScreen();
        Room room = rooms.get(roomIndex);
        System.out.printf("Location: Room " + roomIndex + "%n");
        room.doNarrative();
        room.getItems();
        // System.out.printf("%n- - -%nPress [M] then [Enter] to return to MENU (NO SAVING).%n%nInput: ");
        System.out.printf("%n%nInput: ");
        String userInput = input.nextLine().toLowerCase();
        // Loopar igenom alla dörrar ett rum har
        for (Door door : room.getDoors()) {
            // Hämtar riktning och hämtar den första bokstaven av enumvärdet direction och konverterar till liten bokstav
            String direction = String.valueOf(door.getDirection().name().charAt(0)).toLowerCase(); // jag vet, lite hemsk kodrad men den gör jobbet.
            // Kontrollerar om det användaren har skrivit ner är ett giltigt enumvärde
            if (userInput.equals(direction)) {
                currentRoom = rooms.indexOf(door.getNextRoom());
            } else if (!userInput.equals("m")) { // Om användaren inte skriver in 'm' är det ogiltigt
                System.out.println("Invalid input.");
            } else {
                gameState = GameState.Menu; // om använder skriver 'm' går spelaren tillbaka till meny
            }
        }
    }
    
    // Metod för att avsluta spel. Tanken är att senare lägga till statistik när spelet tar slut
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

        Item item1 = new Key("key1", "Opens a door");
        room8.setItem(item1);
    }

    // Rensar terminalen.
    // KRAV: Terminalen måste ha support för ANSI escape codes.
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
