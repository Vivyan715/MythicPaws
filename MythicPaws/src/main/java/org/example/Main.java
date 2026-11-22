package org.example;

import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Tools.InputHandler;


public class Main {
    public static void main(String[] args) {


        StoryState currentScene = new StartMenu();

        while (currentScene != null) {
            currentScene.display();
            System.out.println("\nYour choice is: ");

            int input = InputHandler.readInt();
            currentScene = currentScene.handleChoice(input);
        }
    }
}