package org.example.GameControl;

import org.example.Player.Dog;
import org.example.Player.Player;

public class GameSetting {

    private static GameSetting instance;

    //    player és a kutya példánya
    private Player player;
    private final Dog dog;


    private int pacifistChoices = 0;
    private int aggressiveChoices = 0;

    private GameSetting() {
        this.player = new Player();
        this.dog = new Dog();
    }  //this is a private constructor --> from outside it can't be instantiated

    //    the singleton's global access point
    public static GameSetting getInstance() {
        if (instance == null) {
            instance = new GameSetting();
        }

        return instance;
    }

    public void addPacifistChoice() {
        this.pacifistChoices++;
    }

    public void addAggressiveChoice() {
        this.aggressiveChoices++;
    }

//    GETTERS

    public int getPacifistChoices() {
        return pacifistChoices;
    }

    public int getAggressiveChoices() {
        return aggressiveChoices;
    }

    public boolean isPacifistEnding() {
        if (pacifistChoices > aggressiveChoices) return true;
        else return false;
    }

    public Player getPlayer() {
        return player;
    }

    public Dog getDog() {
        return dog;
    }

    public void resetGame() {
        this.pacifistChoices = 0;
        this.aggressiveChoices = 0;
        this.player = new Player();
    }

    public void setPacifistChoices(int pacifistChoices) {
        this.pacifistChoices = pacifistChoices;
    }

    public void setAggressiveChoices(int aggressiveChoices) {
        this.aggressiveChoices = aggressiveChoices;
    }
}
