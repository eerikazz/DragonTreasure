Uppgift 1 - Java

// Vad gör koden?

Koden är ett "spel" som låter en användare navigera genom rum
i en "dungeon".

Mer detaljerat så blir det att den skriver ut data från ett rums-objekt,
såsom en beskrivning och en lista med dörrar rummet har.

Dörrarna innehåller information om vilket rum det leder till och vilken riktning
dörren befinner sig i.

Koden håller också koll på i vilken "gamestate" användaren befinner sig i och
utifrån det så körs funktioner med rätt output och input kontroller, såsom
menu eller game.

// Antaganden

I "Examinationsuppgift Del 1.pdf" sida 2 punkt 3 nämns det att spelet ska startas
med methoden "playGame()" vilket inte har gjorts då gamestates används och man lika
gärna kan skriva "gameState = GameState.Game" direkt istället för att ha en method
som gör det.

Figur 2 i "Examinationsuppgift Del 1.pdf" var det svårt att se varför "DragonTrasure"
och "Dungeon" skulle representeras i koden så det har vi kombinerat under Main klassen.

// Kommentar

Vissa saker i koden skulle kunna finskrivas och förbättras bland annat skulle man kunna
använda en loop i "setupGame()" för att minska antalet kodrader och göra det mer läsbart
vilket inte gjorts än för att hålla det simpelt.
