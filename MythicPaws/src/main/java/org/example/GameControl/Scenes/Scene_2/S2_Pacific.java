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
import org.example.Player.Player;
import org.example.Tools.AsciiArt;
import org.example.Tools.TextEffect;

public class S2_Pacific implements StoryState {

    private Creature kitten;

    public S2_Pacific(Creature kitten) {
        this.kitten = kitten;
    }

    @Override
    public void display() {

        TextEffect.typeWriter("Though your palm still throbs slightly, your curiosity outweighs your fear.\nYou carefully slide a small bowl of food toward the little stranger.\n", 70);

        TextEffect.sleep(2500);

        TextEffect.typeWriter("It eyes you suspiciously at first, but hunger eventually wins out.\nAs it begins to eat, the unnatural heat radiating from its fur slowly subsides.\n", 70);

        TextEffect.sleep(2500);

        TextEffect.typeWriter("\nThe turbulent encounter fades into a peaceful evening.\nAs you drift off to sleep, you resolve to get a better look at your new guest in the morning.\n", 70);
        TextEffect.typeWriter("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 35);

        TextEffect.sleep(2500);

        System.out.println("\n=====================");
        System.out.println("     CHAPTER TWO     ");
        System.out.println("=====================");

        TextEffect.typeWriter("\nWhen you wake up the next morning, the creature you took in is nowhere to be found.", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("After a quick search of the cabin, you decide to proceed with your day.\nYou let the dog out, brew a fresh cup of coffee, and step out onto the terrace for breakfast.\n", 70);

        TextEffect.sleep(2500);

        TextEffect.typeWriter("\nYou are halfway through the morning paper when a sudden rustling at the edge of the garden catches your attention.\nSetting your mug down, you walk over to investigate.\n", 70);

        TextEffect.sleep(2500);

        TextEffect.typeWriter("Peering through the tall grass, you realize your little companion has returned...\n", 70);
        TextEffect.sleep(100);
        TextEffect.typeWriter("...and it hasn't come alone.\n", 70);

        TextEffect.sleep(2500);

        System.out.println("\nA cold jolt of fear courses through you. The adult beast standing next to it is massive.\nIts smoldering eyes lock onto yours, and for a tense, breathless moment, you cannot tell if it is about to attack.");

        TextEffect.sleep(500);


        AsciiArt.drawTwoCat();

        TextEffect.typeWriter("\nWhat do you want to do?", 70);
        TextEffect.typeWriter("[1] Grab your shotgun and open fire on the beast.", 32);
        TextEffect.typeWriter("[2] Slowly back away. Reaching into your pocket, you pull out a few dog treats, crouch down, and gently offer them to the creature.", 32);

        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            GameSetting.getInstance().addAggressiveChoice();
//        itt hozom létre a macskát is és itt kérem le a playert is mivel csak ebben a döntésben van használva

            Creature adultCat = new AdultCreatureFactory().createCatashtrophe();
            Player player = GameSetting.getInstance().getPlayer();

            Battle battle = new Battle(player, adultCat);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) return new StartMenu();
            else return new S3_Aggressive();


        } else if (choice == 2) {
            GameSetting.getInstance().addPacifistChoice();

            TextEffect.typeWriter("\nThe creature accepts the food from you and begins to approach in a friendly manner.\nMeanwhile, its kitten watches you curiously. This time, it comes closer and curls up in your lap.\n", 70);

            TextEffect.sleep(2500);

            TextEffect.typeWriter("You have found new friends.\nAlongside your dog, your family has now grown to include two cats.\n", 70);

            TextEffect.sleep(2500);

            TextEffect.typeWriter("You build them a shelter in your yard against the rain and cold weather, which they gladly make use of.\nYou share your food with them, though you aren't entirely sure what you should feed them. You mostly offer them raw meat.\n", 70);

            return new S3_Pacific(kitten);
        }

        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
