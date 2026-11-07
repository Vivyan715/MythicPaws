package org.example;

import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        tesztelés
        Scanner scan = new Scanner(System.in);

        StoryState currentScene = new StartMenu();

        while (currentScene != null) {
            currentScene.display();
            System.out.println("\nYour choice is: ");

            try {
                int input = Integer.parseInt(scan.nextLine());
                currentScene = currentScene.handleChoice(input);


            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}