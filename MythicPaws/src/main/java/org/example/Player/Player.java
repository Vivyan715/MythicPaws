package org.example.Player;

//TODO: ADD INVENTORY

public class Player {

    private int hp;
    private int attackPower;

    public Player() {
        this.hp = 100;
        this.attackPower = 20;
    }

    public void heal(int amount) {
        if (this.hp < 100) {
            this.hp += amount;
        } else {
            System.out.println("You're already healed!");
            this.hp = 100;
        }
    }

    public void takeDamage(int amount) {
        if (hp > 0) this.hp -= amount;
        else this.hp = 0;

        if (this.hp < 0) this.hp = 0;
        // TODO: GAME OVER
    }

//    GETTER ÉS SETTER

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getHp() {
        return hp;
    }
}
