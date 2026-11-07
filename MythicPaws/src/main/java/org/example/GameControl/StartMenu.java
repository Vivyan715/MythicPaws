package org.example.GameControl;

import org.example.GameControl.Scenes.Scene_1;

public class StartMenu implements StoryState {

    @Override
    public void display() {
        System.out.println("=================================");
        System.out.println("     WELCOME TO MYTHIC PAWS!     ");
        System.out.println("=================================");
        System.out.println("\nYou can navigate with pressing the numbers displayed beside the choices.");
        System.out.println("For the best gaming experience please try to maximize your command panel as you can. Thank you!\n");
        System.out.println("Let's try it out!");
        System.out.println("Do you want to play?");
        System.out.println("[1] Play a new game");
        System.out.println("[2] Exit");
    }

    @Override
    public StoryState handleChoice(int choice) {
        if (choice == 1) {
            return new Scene_1();   // Enters the first scene
        } else if (choice == 2) {
            System.out.println("Oh you already leaving? Alright.");
            System.out.println("Goodbye then!");
            System.exit(0);
        }
        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
