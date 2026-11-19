package org.example.GameControl.Scenes.Scene_4;

import org.example.GameControl.StoryState;

public class S4_Pacific implements StoryState {

    @Override
    public void display() {

    }

    @Override
    public StoryState handleChoice(int choice) {


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
