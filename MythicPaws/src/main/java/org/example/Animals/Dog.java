package org.example.Animals;

public class Dog {

    private int hp;
    private String name;

    public Dog() {
        this.hp = 100;
    }

    public void heal(int amount) {
        if (this.hp < 100) this.hp += amount;
        else this.hp = 100;
    }

    public void damage(int amount) {
        if (this.hp > 0) this.hp -= amount;
        else this.hp = 0;
        //TODO: dog's death
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
