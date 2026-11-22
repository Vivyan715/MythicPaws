# MythicPaws console game

## Amiben a játék íródott:
- Java 17 (JDK 17)
- Maven 3.9+

## Összefoglaló a játék menetéről és történetéről

### Történet:
Egy vadőr szerepébe helyezed magad, aki a kutyájával az erdőben él egy fa kuckóban. A játék során az erdő különleges lakóival találkozhatsz és ismerkedhetsz meg. Te, mint játékos döntöd el, hogy ezekkel az állatokkal barátságot kötsz vagy háborút kezdeményezel. A történeti szálak részletes leírását és magyar szövegét a `Concept.md` fájl tartalmazza. 

### A játék menete
**Javaslat:** A játék terminálját külön ablakban megnyitni és a betűméretet alacsonyabbra venni. Ezáltal a szöveg és az első két jelenet rajza nagyrészt kifér a képernyőre. Igyekeztem a sorokat úgy tördelni, hogy oldalra ne lógjanak ki.

#### Szöveg megjelenítése:
A szöveg megjelenítésére saját Typewriter funkciót írtam, amely `ENTER` lenyomására kiírja a sorból fentmaradó részt. Ezt nem teljesen sikerült optimalizálni, így érdemes akkor leütni a billentyűt amikor épp szóköz jelenített meg a rendszer, hogy a szavak ne csússzanak külön sorba. 

#### Játék vezérlése:
A játék vezérlése döntés alapú, tehát a player `[1] [2] [3]` gombok lenyomásával tudja irányítani a játékot. 

A játék során hozott döntések befolyásolják a következő jelenetet és a végkimenetelt, azonban egyetlen rossz döntés nem feltétlenül vezet az aggresszív kimenetel felé. A játék az utolsó jelenet megjelenítése közben bírálja el a döntéseket. Amikor mindenki a barátok listájára került akkor találkozol a tiszta happy endinggel. Ha a barátok és az elhullott állatok listájára is kerültek elemek, akkor az dönt melyik lista a hosszabb. Ha az áldozatok listája akkor a háborús véggel találkozol, ha pedig a barátok listáján szerepel több név akkor egy semleges befejezéssel ér véget a játék.

A játék a döntések során lehetőséget kínál a harcok előtt gyógyulásra, ha a játékos HP-ja 40 alá esik, így a harcban nem indul túlságosan nagy hátrányból.

#### Harcok kivitelezése
Az állatok a játékos döntésének megfelelően reagálnak. Az alap eset, hogy támadnak. Ha a játékos egymás után 2x választotta a támadás opciót az állat beveti a szuperképességét amely nagyobb sebzéssel jár. Ha a játékos a gyógyulást választja, az állat is visszavonul gyógyítani magát ha még maradt lehetősége. Egy körön belül az állat 3x gyógyulhat.

Minden állat egyedi HP-val és támadási képességekkel rendelkezik.

A 2. jelenettől kezdve van lehetőség a játék mentésére, illetve ha van elmentett játék menet akkor annak betöltésére a Start menüben.

Részletesebben a működést a `Structure.md` fájl tartalmazza. Ezt a fejlesztés során főként a saját gondolat meneteim és a megírt funkciók vezetésére használtam. Ugyan itt részletesebben látható az állatok HP és erő elosztása is.

## Design patternek

### Abstract Factory minta
- Az egymással összefüggő állatok és azok variációinak (tehát a kölyök és a felnőtt) létrehozása.
- **Használat:**
    -  Interfész: `CreatureFactory`
- **Konkrét factory-k:**
    - `AdultCreatureFactory`: ez hozza létre az állatok többségét
    - `PuppyCreatureFactory`: Ez hozza létre a kiscicát a legelső jelenethez
- Az állatok ősosztálya a `Creature`, amiből leszármazik az összes állat (`Catashtrophe`, `InvisiPig`, `FrostNoodle`, `ShadHop`)
- Az állatok a jelenetek / Scenek osztályaiban vannak a jelenetekben példányosítva. Ezen felül a mentést végző osztály, azaz a Save manager használja őket.

### State minta
- A játék történetének és fejezeteinek kezelését vezérli.
- A játék viselkedése és menüje a belső állapottól függően változik úgy, hogy minden fejezet egy önálló, zárt osztály.
- Megszünteti az óriási, átláthatatlan if-else vagy switch-case hegyeket a főprogramban.

- **Használat:**
    - _Állapot interfész:_  `StoryState` --> Két metódusa van: `display()` — A szöveg megjelenítésére, `handleChoice(choice)` — a játékos döntésének vezérlésére.
 
- **Konkrét állapotok:**
    - `StartMenu`
    - `Scene_1`
    - `S2_Agressive`, `S2_Pacific`
    - `S3_Aggressive`, `S3_Pacific`
    - `S4_Aggressive`, `S4_Pacific`
    - `S5_Aggressive`, `S5_Pacific`
    - `Closing_Scene`
- A `Main.java` while ciklusa, ami kizárólag a `StoryState` interfészen keresztül vezérli a játékot

### Strategy minta
- A harci döntések (akciók) különálló, dinamikusan cserélhető algoritmusokként való megvalósítása ahelyett, hogy a harci motorban egy nagy elágazás lenne.
