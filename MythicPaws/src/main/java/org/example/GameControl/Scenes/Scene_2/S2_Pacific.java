package org.example.GameControl.Scenes.Scene_2;

import org.example.Animals.Creatures.Creature;
import org.example.GameControl.StoryState;
import org.example.Tools.AsciiArt;
import org.example.Tools.TextEffect;

public class S2_Pacific implements StoryState {

    private Creature kitten;
    private Creature catashtrophe;

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
        TextEffect.typeWriter("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 70);

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

        TextEffect.typeWriter("\nPeering through the tall grass, you realize your little companion has returned...\n", 70);
        TextEffect.sleep(100);
        TextEffect.typeWriter("...and it hasn't come alone.\n", 70);

        TextEffect.sleep(2500);

        System.out.println("\nA cold jolt of fear courses through you. The adult beast standing next to it is massive.\nIts smoldering eyes lock onto yours, and for a tense, breathless moment, you cannot tell if it is about to attack.\n");

        TextEffect.sleep(500);


        AsciiArt.drawTwoCat();

        TextEffect.typeWriter("\nWhat do you want to do?", 70);
        TextEffect.typeWriter("[1] Grab your shotgun and open fire on the beast.", 32);
        TextEffect.typeWriter("[2] Slowly back away. Reaching into your pocket, you pull out a few dog treats, crouch down, and gently offer them to the creature.", 32);

        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
