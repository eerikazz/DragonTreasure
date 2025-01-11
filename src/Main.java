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
                    if (currentRoom == rooms.toArray().length) {
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
        new Player(userInput, 10, 1);
        gameState = GameState.Menu;
    }

    // Metod som skriver ut rum och hanterar navigering
    private static void renderRoom(int roomIndex) {
        clearScreen();
        Room room = rooms.get(roomIndex);

        // System.out.printf("Location: Room " + roomIndex + "%n");

        ArrayList<Item> itemsInRoom = room.getItems();
        ArrayList<Monster> monstersInRoom = room.getMonsters();

        room.doNarrative();

        System.out.printf("%n%nInput: ");
        String userInput = input.nextLine().toLowerCase();

        // Loopar igenom alla dörrar ett rum har
        for (Door door : room.getDoors()) {
            // Hämtar riktning och hämtar den första bokstaven av enumvärdet direction och konverterar till liten bokstav
            String direction = String.valueOf(door.getDirection().name().charAt(0)).toLowerCase(); // jag vet, lite hemsk kodrad men den gör jobbet.
            // Kontrollerar om det användaren har skrivit ner är ett giltigt enumvärde
            if (userInput.equals(direction) && !door.getIsLocked()) {
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
        Room entrance   = new Room("Entrance");
        Room room1      = new Room("Room 1");
        Room sword      = new Room("Sword");
        Room potion     = new Room("Potion");
        Room key        = new Room("key");
        Room goblin     = new Room("goblin");
        Room dragon     = new Room("drake");
        Room exit       = new Room("exit");

        Door entrance_Room1 = new Door(room1, Door.Direction.NORTH, false);
        Door room1_sword    = new Door(sword, Door.Direction.WEST, false);
        Door sword_room1    = new Door(room1, Door.Direction.EAST, false);
        Door room1_potion   = new Door(potion, Door.Direction.EAST, false);
        Door potion_room1   = new Door(room1, Door.Direction.WEST, false);
        Door room1_goblin   = new Door(goblin, Door.Direction.NORTH, false);
        Door goblin_room1   = new Door(room1, Door.Direction.SOUTH, false);
        Door goblin_key     = new Door(key, Door.Direction.NORTH, false);
        Door key_goblin     = new Door(goblin, Door.Direction.SOUTH, false);
        Door key_to_dragon  = new Door(dragon, Door.Direction.NORTH, true);
        Door dragon_to_key  = new Door(key, Door.Direction.SOUTH, false);
        Door dragon_to_exit = new Door(exit, Door.Direction.NORTH, false);

        Item swordItem  = new Weapon("Sword", "Damage: 2 points", 1);
        Item potionItem = new Weapon("Potion", "Heals", 0);
        Item keyItem    = new Weapon("key", "Opens locked door", 0);

        Monster goblinCreature = new Goblin("Goblin", 8, 1);
        Monster dragonCreature = new Dragon("Dragon", 16, 1);

        // Create map
        rooms.add(entrance);
        rooms.add(room1);
        rooms.add(sword);
        rooms.add(potion);
        rooms.add(key);
        rooms.add(goblin);
        rooms.add(dragon);
        rooms.add(exit);

        // Add doors
        entrance.setDoor(entrance_Room1);
        room1.setDoor(room1_sword);
        room1.setDoor(room1_potion);
        room1.setDoor(room1_goblin);
        sword.setDoor(sword_room1);
        potion.setDoor(potion_room1);
        goblin.setDoor(goblin_room1);
        goblin.setDoor(goblin_key);
        key.setDoor(key_goblin);
        key.setDoor(key_to_dragon);
        dragon.setDoor(dragon_to_key);
        dragon.setDoor(dragon_to_exit);

        // Add items
        sword.setItem(swordItem);
        potion.setItem(potionItem);
        key.setItem(keyItem);

        // Add monsters
        goblin.setMonster(goblinCreature);
        dragon.setMonster(dragonCreature);
    }

    // Rensar terminalen.
    // KRAV: Terminalen måste ha support för ANSI escape codes.
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
