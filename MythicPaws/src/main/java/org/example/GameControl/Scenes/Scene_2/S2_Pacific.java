package org.example.GameControl.Scenes.Scene_2;

import org.example.GameControl.GameSetting;
import org.example.GameControl.StoryState;

public class S2_Pacific implements StoryState {

    @Override
    public void display() {

        System.out.println("Though your palm still throbs slightly, your curiosity outweighs your fear.");
        System.out.println("You carefully slide a small bowl of food toward the little stranger.");
        System.out.println("It eyes you suspiciously at first, but hunger eventually wins out.\n");

        System.out.println("As it begins to eat, the unnatural heat radiating from its fur slowly subsides.");
        System.out.println("The turbulent encounter fades into a peaceful evening.\n");

        System.out.println("As you drift off to sleep, you resolve to get a better look at your new guest in the morning.\n");

        System.out.println("\n#############################################################################################################");
    }

    @Override
    public StoryState handleChoice(int choice) {


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
