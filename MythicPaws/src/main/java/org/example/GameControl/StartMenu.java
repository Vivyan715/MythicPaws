package org.example.GameControl;

import org.example.GameControl.Scenes.Scene_1;


public class StartMenu implements StoryState {

    @Override
    public void display() {
        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║                   WELCOME TO MYTHIC PAWS!                   ║");
        System.out.println("╠═════════════════════════════════════════════════════════════╣");
        System.out.println(String.format("║  %-57s  ║", "You can navigate with pressing the numbers displayed"));
        System.out.println(String.format("║  %-57s  ║", "beside the choices."));
        System.out.println("║                                                             ║");
        System.out.println(String.format("║  %-57s  ║", "For the best gaming experience please try to maximize"));
        System.out.println(String.format("║  %-57s  ║", "your command panel as you can. Thank you!"));
        System.out.println("╠═════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                             ║");
        System.out.println(String.format("║  %-57s  ║", "Do you want to play?"));
        System.out.println(String.format("║  %-57s  ║", "  [1] Play a new game"));
        System.out.println(String.format("║  %-57s  ║", "  [2] Exit"));
        System.out.println("║                                                             ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝");
    }

    @Override
    public StoryState handleChoice(int choice) {
        if (choice == 1) {
            GameSetting.getInstance().resetGame();
            return new Scene_1();
        } else if (choice == 2) {
            System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║  Oh, you are already leaving? Alright.                      ║");
            System.out.println("║  Goodbye then!                                              ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n");
            System.exit(0);
        }
        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
