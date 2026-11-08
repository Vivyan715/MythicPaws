package org.example.GameControl.Scenes;

import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_2.S2_Pacific;
import org.example.GameControl.StoryState;
import org.example.Tools.AsciiArt;

public class Scene_1 implements StoryState {
    @Override
    public void display() {
        System.out.println("\n#############################################################################################################");
        System.out.println("You live as a ranger in a small wooden cabin, hidden deep within the forest.");
        System.out.println("One evening, on your way home, you stumble upon a strange creature in the fallen leaves.");
        System.out.println("It looks like a kitten, trembling in the cold. Taking pity on it, you carefully scoop it up and carry it home.");

        System.out.println("Even then, a strange feeling tells you this is no ordinary cat.\n");

        System.out.println("Back at the cabin, your dog greets you, immediately sniffing the mysterious little stranger with intense curiosity.\n");

        System.out.println("As you lean in to examine the creature closer, it hisses in displeasure.");
        System.out.println("Suddenly, its fur begins to heat up, glowing like hot coals. The searing pain forces you to drop the little beast onto the floor.");
        System.out.println("Now you know for sure: you are definitely not dealing with a normal cat.\n");


        System.out.println("You inspect your hands. There are faint burn marks on your palms, but thankfully, nothing that requires medical attention.");
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Call the animal control / get rid of the beast");
        System.out.println("[2] Share your dinner and take care of it\n");

        AsciiArt.drawCat();

        System.out.println("#############################################################################################################\n");
    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            GameSetting.getInstance().addAggressiveChoice();
//            TODO: aggresszív s2 meghívása
        } else if (choice == 2) {
            GameSetting.getInstance().addPacifistChoice();
            return new S2_Pacific();
        }

        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }

}
