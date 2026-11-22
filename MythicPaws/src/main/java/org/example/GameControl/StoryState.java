package org.example.GameControl;

//közös
public interface StoryState {

    void display(); // for displaying the story and the choices

    StoryState handleChoice(int choice);  //returns the next scene
}
