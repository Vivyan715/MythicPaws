# MythicPaws console game

- **Készítette:** Kiss Vivien Brigitta [Neptun kód: B9OLMI]
- **Projekt:** MythicPaws – Szöveges RPG és Kalandjáték
- **Dátum:** 2026. november

## Amiben a játék íródott:
- Java 17 (JDK 17)
- Maven 3.9+
- - **Konzol támogatás:** ANSI escape kódokat támogató terminál (pl. IntelliJ beépített terminál, Windows Terminal)
 
## Fordítás (Build)
A projekt tiszta forrásból való lefordításához és összeállításához futtasd a gyökérkönyvtárban az alábbi parancsot:
```bash
mvn clean compile
```

## Összefoglaló a játék menetéről és történetéről

### Az alapötlet és kiindulás
A játékhoz az alapötletet az Undertale játék szolgáltatta. Ezt a koncepciót egyszerűsítettem le egy rövid döntés és sztori alapú játékká.

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

- **Használat:**
    - _Strategy interfész:_  `CombatAction`
    - _Stratégiák:_
        -  `AttackAction` --> levonja a sebzést a creature-től
        -  `DefendAction` --> ezzel tud a játékos védekezni
        -  `HealAction` --> ezzel tud a játékos heal-elni
     

### Template minta
- Egy többlépcsős algoritmus (a körökre osztott harc menetének) csontvázát rögzíti egyetlen fő metódusban, miközben a konkrét részfeladatokat önálló lépésekre osztja.
- **Használat:**
    - Osztály és sablonmetódus: `Battle.startBattle()`
    - A rögzített folyamatlépések:
        - `BattleStart()` --> kezdeti állapot
        - Ciklus:
            - `gameStatus()` --> életerő-doboz kirajzolása
            - `playerTurn()` --> játékos lépésének bekérése és futtatása
            - ellenőrzés --> ha a lény meghalt, kilép a ciklusból
            - `CreatureTurn()` --> a lény döntése --> heal, attack vagy szupererő használata
            - kör végén reset-el --> `isDefending = false`
        - Lezárás és kiértékelés:
            - `Victory()` vagy `Defeat()`, majd vissza adja a `BattleResult`-ot
         
### Singleton minta
- Biztosítja, hogy az osztályból pontosan egyetlen példány létezzen a memóriában a teljes játék alatt, és ahhoz egy globális hozzáférési pontot ad.
- Központilag kezeli a játékos egyetlen példányát (Player), a kutyát (Dog), a döntésszámlálókat (pacifistChoices, aggressiveChoices), és a játék újrakezdését (resetGame()). Bármelyik jelenetből vagy a SaveManager-ből azonnal elérhető.
- **Használat:** `GameSetting`
- **Megvalósítás:**
    - Privát konstruktor: `private GameSetting()`, kívülről tilos példányosítani
    - Statikus belső mező: `private static GameSetting instance;`
    - Globális elérési pont: `public static GameSetting getInstance()`.
 
> A kutya azért került bele ebbe a mintába, mert az első koncepció szerint a lények választhattak volna hogy a Playert vagy a kutyát támadják, egyfajta csapatként működtek volna. Az idő szűke miatti egyszerűsítések során a kutya mellék szereplővé vált, viszont későbbi ötletek megvalósításához bent maradt mind a Kutya osztálya mind az elkezdett megvalósítása.

> **_Eredeti koncepció:_** Itt lett volna lehetősége a Playernek választani, hogy megvédi magát és a kutyáját is, illetve a játék során fény derült volna, hogy a kutyája is rendelkezik különleges képességgel. Ezen verzió során a Player a kutyája képességei mellett tudott volna mágikus tárgyakat használni a harc és a szelidítések során, illetve lett volna lehetősége a menekülésre is ha a harc nem úgy alakul ahogy azt eltervezte. Ebben a verzióban részletesebb kimenetek lettek volna, a döntéseknek súlyosabb következményei lettek volna a történetre.
> Ezen felül a kezdeti tervek szerint minden jelenet tartalmazott volna egy ASCII art rajzot, viszont ez egyenlőre csak az első két jelenetben került megvalósításra.





