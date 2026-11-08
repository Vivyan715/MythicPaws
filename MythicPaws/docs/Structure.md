## Felépítés

### Választott design pattern

- **Az állatok létrehozásához:**
    - Abstract Factory pattern
- **A döntések kezeléséhez:**
    - State minta → a sztori a belső állapottól függ, amiket külön objektumokba szervez
- **Játék beállítások:**
    - singleton → így a memóriában csak egyetlen központi nyilvántartás van arról hogy van-e mentett játék és a játék
      végét → a játék betöltésekor azonnal meg kell hívódjon.
    - itt tárolódik el az a "mérleg" ami eldönti melyik végkimenetelbe fog érkezni a játékos
        - 5 döntés:
            - ha több az A válasz akkor pacifista véget ér a játék
            - ha több a B válasz akkor agresszív véget ér a játék

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
    - `CreatureFactory` → Interfész, amely előírja az állatcsalád létrehozó metódusait (`createCatashtrophe()`,
      `createInvisiPig()`, `createFrostNoodle()`, `createShadHop()`).
- **Konkrét Factories / Variánsok:**
    - `PuppyCreatureFactory` → Az állatok kölyök variánsait példányosítja (pl. alacsonyabb HP).
    - `AdultCreatureFactory` → Az állatok felnőtt variánsait példányosítja (pl. magasabb HP).
- **Kliens kód működése:**
    - A kliens kód (pl. játékvezérlő, `Main`) kizárólag a `CreatureFactory` interfészen és a `Creature` típuson
      keresztül dolgozik. Így a gyár típusa (kölyök vagy felnőtt) a játék állapotától függően bármikor cserélhető
      anélkül, hogy a kliens kódot módosítani kellene.

## Az állat fajok:

- CATASHTROPHE → tűzokádó macska → cat-ash-trophe
- INVISIPIG → láthatatlan malac
- FROSTNOODLE → fehér vadászgörény → ha szalad minden a mancsai alatt deressé válik
- SHADHOP → egy fekete nyúl aminek addig nincs fizikai teste, amíg fény nem éri. egészen addig árnyékként képes
  közlekedni, nesztelenül. képes ajtók résein stb. átfolyni mint bármilyen fény.

## helyzetek és jelenetek

- minden döntés egy másik jelenethez vezet.

## sebződés és gyógyulás

- támadás esetén a player és a kutya is önálló egyed. ha a playert támadják a kutya nem sebződik, viszont a player
  dönthet úgy hogy megvédi a kutyát.
- rossz döntés esetén, ha a lények támadnak akkor vagy a playert támadják vagy a kutyát, nincs olyan lehetőség hogy
  mindkettőt.

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
