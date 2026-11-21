package org.example.GameControl.Scenes;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.CreatureFactory;
import org.example.Animals.Factories.PuppyCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_2.S2_Agressive;
import org.example.GameControl.Scenes.Scene_2.S2_Pacific;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Persistence.SaveManager;
import org.example.Player.Player;
import org.example.Tools.AsciiArt;
import org.example.Tools.TextEffect;

public class Scene_1 implements StoryState {

    //    létrehozza a cica példányát
    private final Creature kitten;
    private final Player player;   //attól h final a belső állapota változhat --> ezért lehet final

    public Scene_1() {
        CreatureFactory factory = new PuppyCreatureFactory();
        this.kitten = factory.createCatashtrophe();
        this.kitten.takeDamage(20); //mivel éhes és egyedül van

        this.player = GameSetting.getInstance().getPlayer();
    }

    @Override
    public void display() {
        System.out.println("=====================");
        System.out.println("     CHAPTER ONE     ");
        System.out.println("=====================");

        TextEffect.sleep(800);

        TextEffect.typeWriter("You live as a ranger in a small wooden cabin, hidden deep within the forest. \nOne evening, on your way home, you stumble upon a strange creature in the fallen leaves.", 70);

        TextEffect.sleep(2500);
        TextEffect.typeWriter("It looks like a kitten, trembling in the cold. Taking pity on it, you carefully scoop it up and carry it home.\nEven then, a strange feeling tells you this is no ordinary cat.", 70);

        TextEffect.sleep(3000);


        TextEffect.typeWriter("Back at the cabin, your dog greets you, immediately sniffing the mysterious little stranger with intense curiosity.\n", 70);

        TextEffect.sleep(1800);

        TextEffect.typeWriter("As you lean in to examine the creature closer, it hisses in displeasure.", 70);
        TextEffect.sleep(1200);

        TextEffect.typeWriter("Suddenly, its fur begins to heat up, glowing like hot coals. The searing pain forces you to drop the little beast onto the floor.", 70);

        TextEffect.sleep(2000);

        TextEffect.typeWriter("Now you know for sure: you are definitely not dealing with a normal cat.\n", 70);

        TextEffect.sleep(1500);

        TextEffect.typeWriter("You inspect your hands. There are faint burn marks on your palms, but thankfully, nothing that requires medical attention.", 70);

        TextEffect.sleep(2100);


        TextEffect.typeWriter("\nWhat do you want to do?", 70);

        TextEffect.typeWriter("[1] Call the animal control / get rid of the beast", 32);
        TextEffect.typeWriter("[2] Share your dinner and take care of it\n", 32);
        TextEffect.typeWriter("[3] Save game and exit.\n", 32);
        AsciiArt.drawCat();

    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {

            TextEffect.typeWriter("While you're waiting for the police, you decide not to take any chances.\n", 40);
            TextEffect.typeWriter("You try to corner it and herd your beast into a box...\n", 40);
            TextEffect.typeWriter("But when a stressed animal is cornered, its defensive instinct kicks in, and it bursts into a fierce blaze!\n", 40);

            GameSetting.getInstance().addAggressiveChoice();

//            Player player = GameSetting.getInstance().getPlayer();

            Battle battle = new Battle(player, kitten);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) {
                return new StartMenu();
            } else {
                player.addDeathList(this.kitten);
                return new S2_Agressive();
            }


        } else if (choice == 2) {
            GameSetting.getInstance().addPacifistChoice();
            kitten.heal(20); //megetette és biztonságot adott neki


            System.out.println("\nYou shared your dinner with the kitten.\n");

            System.out.println("╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║                       [ GAME STATUS ]                       ║");
            System.out.println("╠═════════════════════════════════════════════════════════════╣");
            System.out.println(String.format("║  %-58s ║", this.kitten.getName() + " is healed."));
            System.out.println(String.format("║  %-58s ║", "Current HP of " + this.kitten.getName() + " is: " + this.kitten.getHp() + " HP"));
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n");

            player.addFriend(kitten);

            return new S2_Pacific(kitten);
        } else if (choice == 3) {

            SaveManager.GameSaver(this);

            System.out.println("\n=================================================");
            System.out.println("  Game saved successfully! See you next time!   ");
            System.out.println("=================================================\n");

            System.exit(0);
        }

        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }

}
