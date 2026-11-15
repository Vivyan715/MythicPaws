package org.example.Combat;

public class BattleResult {
    // a harc végeredményét foglalja össze
    private final boolean victory;
    private final int winnerHp; // győzelem esetén a player, vereség esetén a lény megmaradt HP-ja

    public BattleResult(boolean victory, int winnerHp) {
        this.victory = victory;
        this.winnerHp = winnerHp;
    }

    public boolean isVictory() {
        return victory;
    }

    public int getWinnerHp() {
        return winnerHp;
    }

    public int getRemainingHp() {
        return winnerHp;
    }
}
