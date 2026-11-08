package org.example.GameControl.Scenes.Scene_2;

import org.example.Animals.Creatures.Creature;
import org.example.GameControl.StoryState;
import org.example.Tools.AsciiArt;

public class S2_Pacific implements StoryState {

    private Creature kitten;
    private Creature catashtrophe;

    public S2_Pacific(Creature kitten) {
        this.kitten = kitten;
    }

    @Override
    public void display() {

        System.out.println("Though your palm still throbs slightly, your curiosity outweighs your fear.");
        System.out.println("You carefully slide a small bowl of food toward the little stranger.");
        System.out.println("It eyes you suspiciously at first, but hunger eventually wins out.\n");

        System.out.println("As it begins to eat, the unnatural heat radiating from its fur slowly subsides.");
        System.out.println("The turbulent encounter fades into a peaceful evening.\n");

        System.out.println("As you drift off to sleep, you resolve to get a better look at your new guest in the morning.\n");

        System.out.println("=====================");
        System.out.println("     CHAPTER TWO     ");
        System.out.println("=====================");

        System.out.println("\n#############################################################################################################");

        System.out.println("\nWhen you wake up the next morning, the creature you took in is nowhere to be found.");
        System.out.println("After a quick search of the cabin, you decide to proceed with your day.");
        System.out.println("You let the dog out, brew a fresh cup of coffee, and step out onto the terrace for breakfast.\n");


        System.out.println("You are halfway through the morning paper when a sudden rustling at the edge of the garden catches your attention.");
        System.out.println("Setting your mug down, you walk over to investigate.");
        System.out.println("Peering through the tall grass, you realize your little companion has returned...");
        System.out.println("...and it hasn't come alone.\n");


        System.out.println("A cold jolt of fear courses through you. The adult beast standing next to it is massive.");
        System.out.println("Its smoldering eyes lock onto yours, and for a tense, breathless moment, you cannot tell if it is about to attack.\n");

        AsciiArt.drawTwoCat();

        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Grab your shotgun and open fire on the beast.");
        System.out.println("[2] Slowly back away. Reaching into your pocket, you pull out a few dog treats, crouch down, and gently offer them to the creature.");

        System.out.println("\n#############################################################################################################");

    }

    @Override
    public StoryState handleChoice(int choice) {


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
