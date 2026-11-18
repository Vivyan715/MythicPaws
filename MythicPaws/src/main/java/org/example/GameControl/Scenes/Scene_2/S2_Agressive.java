package org.example.GameControl.Scenes.Scene_2;

import org.example.Animals.Creatures.Creature;
import org.example.GameControl.StoryState;

public class S2_Agressive implements StoryState {

    private Creature kitten;
    private Creature catashtrophe;

    public S2_Agressive(Creature kitten, Creature catashtrophe) {
        this.kitten = kitten;
        this.catashtrophe = catashtrophe;
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
