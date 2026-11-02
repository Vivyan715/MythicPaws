## Felépítés

### Választott design pattern

- **Az állatok létrehozásához:**
    - factory pattern
- **A döntések kezeléséhez:**
    - State minta → a sztori a belső állapottól függ, amiket külön objektumokba szervez
- **Játék beállítások:**
    - singleton → így a memóriában csak egyetlen központi nyilvántartás van arról hogy van-e mentett játék és a játék
      végét → a játék betöltésekor azonnal meg kell hívódjon.
    - itt tárolódik el az a "mérleg" ami eldönti melyik végkimenetelbe fog érkezni a játékos
        - 5 döntés:
            - ha több az A válasz akkor pacifista véget ér a játék
            - ha több a B válasz akkor agresszív véget ér a játék

**Factory megvalósítása:**

- Creature → Absztrakt osztály azaz a "közös nevező."
    - minden tulajdonság ami igaz lesz az összes állatra:
        - név
        - HP
        - special power → csak egy előre definiált függvény → megmutatja hogy majd egy ilyennek lennie kell.

## Az állat fajok:

- CATASHTROPHE → tűzokádó macska → cat-ash-trophe
- INVISIPIG → láthatatlan malac
- FROSTNOODLE → fehér vadászgörény → ha szalad minden a mancsai alatt deressé válik
- SHADHOP → egy fekete nyúl aminek addig nincs fizikai teste, amíg fény nem éri. egészen addig árnyékként képes
  közlekedni, nesztelenül. képes ajtók résein stb. átfolyni mint bármilyen fény. 