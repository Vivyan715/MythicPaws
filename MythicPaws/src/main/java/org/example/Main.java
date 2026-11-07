package org.example;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.CreatureFactory;
import org.example.Animals.Factories.PuppyCreatureFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        tesztelés
        CreatureFactory factory = new PuppyCreatureFactory();

        Creature cat = factory.createCatashtrophe();

        System.out.println("Állat neve: " + cat.getName());
    }
}