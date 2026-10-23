package org.example.Player;

//TODO: ADD INVENTORY

public class Player {

    private int hp;


    public Player() {
        this.hp = 100;
    }

    public void heal(int amount) {
        if (hp < 100) this.hp += amount;
        else this.hp = 100;
    }

    public void damage(int amount) {
        if (hp > 0) this.hp -= amount;
        else this.hp = 0;
        // TODO: GAME OVER
    }

    public int getHp() {
        return hp;
    }
}
