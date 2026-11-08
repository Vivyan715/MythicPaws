package org.example.GameControl;

public class GameSetting {

    private static GameSetting instance;

    private int pacifistChoices = 0;
    private int aggressiveChoices = 0;

    private GameSetting() {
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

    public void resetGame() {
        this.pacifistChoices = 0;
        this.aggressiveChoices = 0;
    }


}
