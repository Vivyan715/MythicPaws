package org.example.Animals.Creatures;

import org.example.Tools.TextEffect;

//tűzokádó macska
public class Catashtrophe extends Creature {

    public Catashtrophe(String name, int maxHp) {
        super(name, maxHp);
    }

    @Override
    public void superPower() {
        TextEffect.typeWriter("\n>>> The" + getName() + " lets out a deafening screech! <<<\n", 30);
        TextEffect.typeWriter("Its entire body erupts in ethereal blue fire as it unleashes a catastrophic flame burst!\n", 30);
    }
}
