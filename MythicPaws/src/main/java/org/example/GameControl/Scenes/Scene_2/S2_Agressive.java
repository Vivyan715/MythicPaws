package org.example.GameControl.Scenes.Scene_2;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_3.S3_Aggressive;
import org.example.GameControl.Scenes.Scene_3.S3_Pacific;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Persistence.SaveManager;
import org.example.Player.Player;
import org.example.Tools.InputHandler;
import org.example.Tools.TextEffect;

public class S2_Agressive implements StoryState {


    private final Creature adultCat;

    private final Player player;

    public S2_Agressive() {
        this.adultCat = new AdultCreatureFactory().createCatashtrophe();
        this.player = GameSetting.getInstance().getPlayer();
    }

    @Override
    public void display() {

        TextEffect.typeWriter("\nYou finally overpower the creature, leaving it lifeless on the floor.\nIt is a bitter victory—your wounds still throb, and the blood of a helpless cub is on your hands.\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\nBy the time animal control arrives, the body has vanished, leaving only a pile of hot ash.\nSeeing just a smoky room and your injured, disheveled state, they assume you are delusional and started the fire yourself—leaving you behind in anger.\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\nPhysically and mentally exhausted, you collapse into bed in the silent cabin.\nThe night was so surreal that you begin to question your own sanity...\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 35);

        TextEffect.sleep(2500);

        System.out.println("\n=====================");
        System.out.println("     CHAPTER TWO     ");
        System.out.println("=====================");

        TextEffect.typeWriter("\nA few days later, you sit on the terrace giving your dog some treats, your bandaged burns a constant reminder of that surreal night.\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\nSuddenly, a loud branch snaps in the bushes. You pocket the remaining treats as your dog freezes, tucking its tail and backing away with a low whine.\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\nKnowing it's trouble, you grab your shotgun—despite the pain in your injured hand—and step closer.\nFrom the foliage emerges a massive adult beast, its fur glowing a menacing red.\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\nIt sniffs the air and locks its eyes onto yours. You are certain it smells the ash of its fallen cub on you.\nIn the suffocating silence, the beast stands tense, waiting for your move. With the gun in your hand and the treats in your pocket, the choice is yours.\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\nWhat do you want to do?", 70);
        TextEffect.typeWriter("[1] Grab your shotgun and open fire on the beast.", 32);
        TextEffect.typeWriter("[2] Slowly back away. Reaching into your pocket, you pull out a few dog treats, crouch down, and gently offer them to the creature.", 32);
        TextEffect.typeWriter("[3] Save game and exit.\n", 32);
        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            GameSetting.getInstance().addAggressiveChoice();

            if (player.getHp() < 40) {
                TextEffect.typeWriter("\nYour wounds from the previous fight are throbbing painfully (Current HP: " + player.getHp() + "/100).\n", 50);
                TextEffect.typeWriter("Do you want to use a first-aid kit to bandage your wounds before engaging? [1] Yes  [2] No: ", 30);

                String healChoice = InputHandler.readLine();

                if (healChoice.equals("1")) {
                    player.heal(25);

                    TextEffect.typeWriter("You quickly patched yourself up. HP restored to: " + player.getHp() + "/100!\n", 40);
                }
            }

            Battle battle = new Battle(player, adultCat);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) {
                return new StartMenu();
            } else {
                player.addDeathList(adultCat);
                return new S3_Aggressive();
            }


        } else if (choice == 2) {
            GameSetting.getInstance().addPacifistChoice();

            TextEffect.typeWriter("\nYou lower your shotgun, slowly back away, and kneel with the treats in your outstretched hand.\nThe massive beast lets out a low, mournful growl, the red glow of its fur flickering with grief and rage.\n", 70);

            TextEffect.typeWriter("\nFor a breathless moment, you expect it to strike. Instead, it senses no threat in your posture.\nCautiously, it steps forward, sniffs your trembling hand, and accepts the food.\n", 70);


            TextEffect.typeWriter("\nThe fiery glow slowly fades. It will never forget what happened, but it understands your silent apology.\nWith one last piercing look, the mother turns and disappears into the forest—sparing you, and granting a fragile peace.\n", 70);


            player.addFriend(adultCat);

            return new S3_Pacific();
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
