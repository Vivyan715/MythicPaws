package org.example.Animals;

// ez a fájl az állatok alapja
public abstract class Creature {

    private String name;
    private int hp;

    public Creature(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public abstract void superPower();  //csak megmondja, hogy majd kell lennie egy szuperképességnek
}
