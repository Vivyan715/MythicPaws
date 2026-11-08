package org.example.GameControl.Scenes.Scene_2;

import org.example.GameControl.StoryState;

public class S2_Pacific implements StoryState {

    @Override
    public void display() {

    }

    @Override
    public StoryState handleChoice(int choice) {

//        TODO: döntések kezelése

        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
