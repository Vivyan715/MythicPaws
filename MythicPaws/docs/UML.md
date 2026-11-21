# Mythic Paws - UML Class Diagram & Architecture

A diagram bemutatja az alkalmazás rétegeit, az osztályok közötti kapcsolatokat, valamint a beépített tervezési mintákat
(**Abstract Factory**, **State**, **Strategy**, **Template Method**, **Singleton**).

```mermaid
classDiagram
    direction TB

    %% ==========================================
    %% 1. DOMAIN & CREATURES (Abstract Factory)
    %% ==========================================
    class Creature {
        <<abstract>>
        -String name
        -int maxHp
        -int hp
        -int attackPower
        -int healAmount
        -int superPowerDamage
        +superPower()* void
        +getName() String
        +getMaxHp() int
        +getHp() int
        +getAttackPower() int
        +getHealAmount() int
        +getSuperPowerDamage() int
        +takeDamage(int amount) int
        +heal(int amount) int
    }

    class Catashtrophe {
        +superPower() void
    }
    class InvisiPig {
        +superPower() void
    }
    class FrostNoodle {
        +superPower() void
    }
    class ShadHop {
        +superPower() void
    }

    Creature <|-- Catashtrophe
    Creature <|-- InvisiPig
    Creature <|-- FrostNoodle
    Creature <|-- ShadHop

    class CreatureFactory {
        <<interface>>
        +createCatashtrophe() Creature
        +createInvisiPig() Creature
        +createFrostNoodle() Creature
        +createShadHop() Creature
    }

    class AdultCreatureFactory {
        +createCatashtrophe() Creature
        +createInvisiPig() Creature
        +createFrostNoodle() Creature
        +createShadHop() Creature
    }

    class PuppyCreatureFactory {
        +createCatashtrophe() Creature
        +createInvisiPig() Creature
        +createFrostNoodle() Creature
        +createShadHop() Creature
    }

    CreatureFactory <|.. AdultCreatureFactory
    CreatureFactory <|.. PuppyCreatureFactory
    AdultCreatureFactory ..> Creature : creates
    PuppyCreatureFactory ..> Creature : creates

    %% ==========================================
    %% 2. PLAYER & SETTINGS (Singleton)
    %% ==========================================
    class Player {
        -int hp
        -int attackPower
        -int combatHeal
        -List~Creature~ friends
        -List~Creature~ deathList
        +heal(int amount) void
        +takeDamage(int amount) void
        +getHp() int
        +setHp(int hp) void
        +getAttackPower() int
        +getCombatHeal() int
        +getFriends() List~Creature~
        +getDeathList() List~Creature~
        +addFriend(Creature creature) void
        +addDeathList(Creature creature) void
        +clearLists() void
    }

    class Dog {
        -String name
        -int hp
        +heal(int amount) void
        +getHp() int
    }

    class GameSetting {
        <<singleton>>
        -static GameSetting instance
        -Player player
        -Dog dog
        -int pacifistChoices
        -int aggressiveChoices
        +static getInstance() GameSetting
        +addPacifistChoice() void
        +addAggressiveChoice() void
        +setPacifistChoices(int count) void
        +setAggressiveChoices(int count) void
        +getPacifistChoices() int
        +getAggressiveChoices() int
        +isPacifistEnding() boolean
        +resetGame() void
        +getPlayer() Player
        +getDog() Dog
    }

    GameSetting *-- Player
    GameSetting *-- Dog
    Player o-- Creature : friends / deathList

    %% ==========================================
    %% 3. COMBAT (Strategy & Template Method)
    %% ==========================================
    class CombatAction {
        <<interface>>
        +execute(Player player, Creature creature, Battle battle) void
    }

    class AttackAction {
        -int attackPower
        +execute(Player player, Creature creature, Battle battle) void
    }

    class DefendAction {
        +execute(Player player, Creature creature, Battle battle) void
    }

    class HealAction {
        -int healAmount
        +execute(Player player, Creature creature, Battle battle) void
    }

    CombatAction <|.. AttackAction
    CombatAction <|.. DefendAction
    CombatAction <|.. HealAction

    class Battle {
        #Player player
        #Creature creature
        #boolean isDefending
        #Map~Integer, CombatAction~ actionList
        +startBattle() BattleResult
        #gameStatus() void
        #playerTurn() void
        #CreatureTurn() void
        +damagePlayer(int baseDamage) void
        #Victory() void
        #Defeat() void
    }

    class BattleResult {
        -boolean victory
        -int remainingHp
        +isVictory() boolean
        +getRemainingHp() int
    }

    class InvalidCombatActionException {
        +InvalidCombatActionException(String message)
    }

    Battle *-- CombatAction : Strategy
    Battle --> Player
    Battle --> Creature
    Battle ..> BattleResult : returns
    Battle ..> InvalidCombatActionException : throws

    %% ==========================================
    %% 4. GAME CONTROL & SCENES (State Pattern)
    %% ==========================================
    class StoryState {
        <<interface>>
        +display() void
        +handleChoice(int choice) StoryState
    }

    class StartMenu {
        +display() void
        +handleChoice(int choice) StoryState
    }

    class Scene_1 {
        -Player player
        -Creature kitten
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S2_Agressive {
        -Player player
        -Creature adultCat
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S2_Pacific {
        -Player player
        -Creature adultCat
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S3_Aggressive {
        -Player player
        -Creature piggy
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S3_Pacific {
        -Player player
        -Creature piggy
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S4_Aggressive {
        -Player player
        -Creature ferret
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S4_Pacific {
        -Player player
        -Creature ferret
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S5_Aggressive {
        -Player player
        -Creature bunny
        +display() void
        +handleChoice(int choice) StoryState
    }

    class S5_Pacific {
        -Player player
        -Creature bunny
        +display() void
        +handleChoice(int choice) StoryState
    }

    class Closing_Scene {
        -Player player
        +display() void
        +handleChoice(int choice) StoryState
    }

    StoryState <|.. StartMenu
    StoryState <|.. Scene_1
    StoryState <|.. S2_Agressive
    StoryState <|.. S2_Pacific
    StoryState <|.. S3_Aggressive
    StoryState <|.. S3_Pacific
    StoryState <|.. S4_Aggressive
    StoryState <|.. S4_Pacific
    StoryState <|.. S5_Aggressive
    StoryState <|.. S5_Pacific
    StoryState <|.. Closing_Scene

    %% ==========================================
    %% 5. PERSISTENCE & TOOLS
    %% ==========================================
    class SaveManager {
        -static String SaveFile
        +static hasSave() boolean
        +static GameSaver(StoryState currentScene) void
        +static GameLoader() StoryState
        -static restoreCreatures(String line, Player player, boolean isFriend) void
        -static restoreSceneByName(String sceneName) StoryState
    }

    class InputHandler {
        -static Scanner sc
        +static readLine() String
        +static readInt() int
    }

    class TextEffect {
        +static boolean FAST_MODE
        +static sleep(int milliseconds) void
        +static typeWriter(String text, int delayMillis) void
        +static clearInputBuffer() void
    }

    class AsciiArt {
        +static drawCat() void
        +static drawTwoCat() void
    }

    class Main {
        +static main(String[] args) void
    }

    Main --> StoryState
    Main ..> InputHandler
    StoryState ..> SaveManager : saves / loads
    SaveManager ..> GameSetting
    SaveManager ..> StoryState
    SaveManager ..> CreatureFactory