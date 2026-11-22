package org.example.Animals.Creatures;

import org.example.Tools.TextEffect;

public class ShadHop extends Creature {

    public ShadHop() {
        super("Shadhop", 140);
    }

    @Override
    public void superPower() {
        TextEffect.typeWriter("\n>>> The " + getName() + " dissolves into an elusive shadow! <<<\n", 30);
        TextEffect.typeWriter("Your blind aggression was a fatal mistake. It flows up from the floor like an ice-cold black liquid, wrapping around your throat and choking you!\n", 30);
    }
}
