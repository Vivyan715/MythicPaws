package org.example.Player;

public class Dog {

    private int hp;
    private String name;

    public Dog() {
        this.hp = 80;
    }

    public void heal(int amount) {
        if (this.hp < 100) {
            this.hp += amount;
        } else {
            System.out.println("The dog is already healed!");
            this.hp = 100;
        }
    }

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }
}
