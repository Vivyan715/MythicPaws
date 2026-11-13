package org.example.GameControl.Scenes.Scene_3;

import org.example.Animals.Creatures.Creature;
import org.example.GameControl.StoryState;

public class S3_Pacific implements StoryState {

    private Creature kitten;

    private Creature catashtrophe;

    public S3_Pacific(Creature kitten) {
        this.kitten = kitten;
    }

    @Override
    public void display() {

    }

    @Override
    public StoryState handleChoice(int choice) {


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
