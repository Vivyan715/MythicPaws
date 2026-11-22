## Felépítés

### Választott design pattern

- **Az állatok létrehozásához:**
    - Abstract Factory pattern
- **A döntések kezeléséhez:**
    - State minta → a sztori a belső állapottól függ, amiket külön objektumokba szervez
- **Játék beállítások:**
    - Singleton (`GameSetting`) → így a memóriában csak egyetlen központi nyilvántartás van arról, hogy van-e mentett
      játék és a játék végét jelző állapotokról → a játék betöltésekor azonnal elérhető.
    - Itt tárolódik el az a "mérleg", ami eldönti, melyik végkimenetelbe fog érkezni a játékos:
        - `pacifistChoices`, `aggressiveChoices` számlálók
        - Metódusok: `addPacifistChoice()`, `addAggressiveChoice()`, `isPacifistEnding()`, `resetGame()`
        - 5 döntés:
            - Ha több az agresszív válasz (1), akkor agresszív véget ér a játék
            - Ha több a pacifista válasz (2), akkor pacifista véget ér a játék

**Abstract Factory megvalósítása:**

Az Abstract Factory minta célja egymással összefüggő termékcsaládok (állatok) és azok variánsainak (kölyök és felnőtt)
létrehozása anélkül, hogy a kliens kód konkrét osztályokhoz kötődne.

- **Absztrakt Példány:**
    - `Creature` → Absztrakt ősosztály.
    - Közös állapot és logika: `name`, `hp`, `maxHp`, `takeDamage(amount)`, getterek.
    - Absztrakt metódus: `superPower()` → minden állatnak saját szuperképessége van.
- **Konkrét példányok:**
    - `Catashtrophe`, `InvisiPig`, `FrostNoodle`, `ShadHop` → a `Creature` osztály konkrét megvalósításai.
- **Abstract Factory interfész:**
    - `CreatureFactory` → Interfész, amely előírja az állatcsalád létrehozó
      metódusait:
        - `createCatashtrophe()`
        - `createInvisiPig()`
        - `createFrostNoodle()`
        - `createShadHop()`
- **Konkrét Factories / Variánsok:**
    - `PuppyCreatureFactory` → Az állatok kölyök variánsait példányosítja (pl. alacsonyabb HP).
    - `AdultCreatureFactory` → Az állatok felnőtt variánsait példányosítja (pl. magasabb HP).
- **Kliens kód működése:**
    - A kliens kód (pl. játékvezérlő, `Main`) kizárólag a `CreatureFactory` interfészen és a `Creature` típuson
      keresztül dolgozik. Így a gyár típusa (kölyök vagy felnőtt) a játék állapotától függően bármikor cserélhető
      anélkül, hogy a kliens kódot módosítani kellene.

## Az állat fajok:

- CATASHTROPHE → tűzokádó macska → cat-ash-trophe
    - _puppy:_
        - max HP: 60
        - attack: 10HP
        - heal: 8HP
        - superpower: 15HP
    - _adult:_ [✓]
        - max HP: 120
        - attack: 15HP
        - superpower: 40HP
        - heal: 15HP

- INVISIPIG → láthatatlan malac
    - _puppy:_
        - a sztori jelenlegi állása szerint ilyennel nem találkozik, ha lesz időm megírom
    - _adult:_ [✓]
        - max HP: 90
        - attack: 12HP
        - heal: 20HP
        - superpower: 35HP


- FROSTNOODLE → fehér vadászgörény → ha szalad minden a mancsai alatt deressé válik
    - _puppy:_
        - a sztori jelenlegi állása szerint ilyennel nem találkozik, ha lesz időm megírom
    - _adult:_ [✓]
        - max HP: 80HP
        - attack: 18HP
        - heal: 5HP
        - superpower: 45HP

- SHADHOP → egy fekete nyúl aminek addig nincs fizikai teste, amíg fény nem éri. egészen addig árnyékként képes
  közlekedni, nesztelenül. képes ajtók résein stb. átfolyni mint bármilyen fény.
    - _puppy:_
        - a sztori jelenlegi állása szerint ilyennel nem találkozik, ha lesz időm megírom
    - _adult:_
        - max HP: 140
        - attack: 10HP
        - heal: 15HP
        - superpower: 30HP

## helyzetek és jelenetek

- minden döntés egy másik jelenethez vezet.

- **Játékos (`Player` - `org.example.Player`):**
    - Állapot: `hp` (alapértelmezetten 100).
    - Metódusok: `heal(amount)` (maximum 100 HP-ig), `takeDamage(amount)` (0-nál Game Over).
- **Kutya (`Dog` - `org.example.Player`):**
    - Állapot: `name`, `hp` (alapértelmezetten 80).
    - Metódusok: `heal(amount)`, getterek/setterek.
- **Sebződés és gyógyulás szabályai:**
    - Támadás esetén a Player és a Dog önálló entitások. Ha a playert támadják, a kutya nem sebződik automatikusan,
      viszont a player dönthet úgy, hogy megvédi a kutyát.
    - Rossz döntés esetén, ha a lények támadnak, akkor vagy a playert támadják, vagy a kutyát — sosem mindkettőt
      egyszerre.

---

### Segédosztályok (`Tools` csomag)

- **ASCII Grafikai Megjelenítés (`AsciiArt` - `org.example.Tools`):**
    - Feladata: a konzolos vizuális elemek megjelenítése statikus metódusokon keresztül.
    - Független a játékmotortól, így bármelyik jelenetből vagy menüből közvetlenül meghívható a megfelelő illusztráció
      kirajzolására.
    - Jelenlegi metódusok:
        - `drawCat()`: a Catashtrophe (macska) karakteres ASCII rajzának megjelenítése.

### Játék működési alapelvei -- State (állapot) kezeléses pattern

- csak két végkimenetel lehet: pacifista és az aggresszív
- a játék mindig két döntést ad: egy 1. és egy 2. választ
- a végkimenetel attól függ hogy a pacifista vagy az aggresszív válaszokból van-e több.
- attól hogy az első döntésnél az aggresszív irányba terelte a sztorit nem feltétlenül kell ilyen véget érnie. lehetnek
  esetek ahol keresztezik egymást a scene-k
- a C opció később kerül beépítésre, itt lesz meg a lehetősége hogy kilépjen a játékból és esetlegesen mentsen. --> ezt
  majd a végén ha kész a játék
- 5 döntési lehetőség --> a player 5 scene-t lát egy játék során.
    - elvileg 12 screne kell összesen
- +2 scene:
    - nyitó, amikor belép a játékba és esetlegesen megkapja az opciót, hogy folytassa az aktuális mentésnél --> az
      aktuális mentést is a végén írom meg
    - záró scene:
        1. eset - ha meghalt a player
        2. eset - ha a végére ért a játéknak
- +1 scene: mentés
    - TODO a megírása későbbre.

## Story telling

- be vannak állítva a történet kiírásához tartozó delay-ek illetve egy typewriter effekt is be van építve a TOOLS
  mappában
- TODO: optimalizálni a delayeket és a typewriter gyorsaságát. [✓]

## Harcok megírása

- temple method (LINK → https://refactoring.guru/design-patterns/template-method) használva a strategy method-al
  (LINK → https://refactoring.guru/design-patterns/strategy)
    - ősosztály
    - futtatja a while ciklust és ellenőrzi mikor ér véget a harc
    - körök kezelése --> player kre, ellenfél támadása

    - strategy-vel --> az akciók ([1] támadás, [2] védekezés, [3] gyógyulás) kezelése --> Catashtrophe tüzet okád
      stb. --> külön álló cserélhetp stratégiák a CombatAction-ön keresztül
    - BattleResult --> ez egy eredmény objektum ami leírja mi lett a harc végkimenetele és kinek mennyi HP-ja maradt -->
      ezt fogja a Scene feldolgozni
    - a játékosnak egyenlőre egy attackPower tulajdonságban van rögzítve mekkora az alap sebzése --> TODO: ha lesz idő
      rá akkor lehetne csinálni fegyvereket amikkel támadhat és így megadva hogy melyikkel harcolva mennyi a támadási
      ereje --> egyenlőre csak puskával lövöldözik a player.

    - **player heal and attackPower értékei:**
        - heal --> 25HP
        - attack --> 20HP
- a superpower akkor kerül bevetésre miután a player egymás után 2x is az attack opciót választotta
- állat healelése:
    - ha a HP-ja utolsó 20%-ában van
    - körönként max 3x tud healelni --> így nem lesz végtelen pingpongozás
    - Ha a játékos a Heal opciót választotta ÉS a lény nem teljes életerőn van ÉS van még gyógyulási lehetősége --> az
      állat is gyógyít.
- egyébként a harc során az állat alapértelmezett reakciója a támadás

- a kutya szerepét egyenlőre félre teszem, ha lesz rá időm megírom

---

## Végére hagyott finomítások

### Perzisztencia réteg (`Persistence` csomag)

- **Mentéskezelő (`SaveManager` - `org.example.Persistence`):**
    - Felelősség: az aktuális játékállapot fájlba mentése és visszatöltése a `savedGame.txt` fájl használatával.
    - **Mentett állapotok:**
        - Aktuális fejezet/jelenet osztályneve (`SCENE`)
        - Játékos aktuális életereje (`HP`)
        - Döntési számlálók (`PACIFIST`, `AGGRESSIVE`)
        - Megmentett és megbarátkozott állatok listája vesszővel elválasztva (`FRIENDS`)
        - Legyőzött állatok halállistája vesszővel elválasztva (`DEATHS`)
    - **Visszatöltési folyamat (`GameLoader()`):**
        - Helyreállítja a `GameSetting` számlálóit és a `Player` állapotát (`hp`, listák ürítése).
        - A szöveges nevekből az `AdultCreatureFactory` és `PuppyCreatureFactory` segítségével újra legyártja az élő
          `Creature` objektumokat.
        - Egy `switch` elágazással név alapján példányosítja a megfelelő jelenetet (`restoreSceneByName()`), így a játék
          pontosan a mentett pontról folytatódik.
    - **Jelenetekbe integrált mentési lehetőség (3. opció):**
        - Minden jelenet választási menüjében megjelenik a `[3] Save game and exit.` opció.
        - Kiválasztásakor meghívódik a `SaveManager.GameSaver(this)`, elmenti az állapotot és kilép a programból.
    - **Főmenü integráció (`StartMenu`):**
        - A `SaveManager.hasSave()` ellenőrzi, létezik-e korábbi mentés.
        - Megjelenik a `[2] Continue saved game` opció, amely közvetlenül visszatölti a mentett fejezetet.

### Segédosztályok bővülése (`Tools` csomag)

- **Központi bemenetkezelő (`InputHandler` - `org.example.Tools`):**
    - Feladata: a konzolos bemenet egységes, ütközésmentes kezelése a teljes alkalmazásban.
    - Megszünteti a többszörös `new Scanner(System.in)` példányosításból eredő puffer-összeakadást.

### Lezárás és statisztikák

- **Történeti lezárások kiértékelése:**
    - Három különböző epilógus szöveg a döntések és a halállista alapján:
        1. *Tiszta békés befejezés* (ha a halállista üres).
        2. *fegyverszünet* (ha a barátok száma nagyobb, mint a halottaké).
        3. *Háború a sötét erdővel* (ha a halottak száma dominál).
- **Generikus gyűjtemények rendezése:**
    - A játék végén a megmentett állatok és a legyőzött lények ábécésorrendbe rendezve jelennek meg.
- **Játék újraindítási ciklus:**
    - A statisztikai tábla után a játékos választhat:
        - `[1] Play a new game` --> visszalép a `StartMenu`-be és nullázza az állapotot.
        - `[2] Exit` --> kilép a játékból.

### Kivételkezelés (`Combat` csomag)

- **`InvalidCombatActionException`:**
    - Egyedi, ellenőrizetlen domén kivétel, amely érvénytelen harci cselekvés (nem 1, 2, 3) kiválasztásakor dobódik és
      kerül elkapásra a `Battle.playerTurn()` ciklusában.
