package org.example.Animals.Creatures;

import org.example.Tools.TextEffect;

//láthatatlan malac
public class InvisiPig extends Creature {

    public InvisiPig() {
        super("InvisiPig", 90);
    }

    @Override
    public void superPower() {
        TextEffect.typeWriter("\n>>> The " + getName() + " vanishes into thin air! <<<\n", 30);
        TextEffect.typeWriter("Your greed was a trap. A blood-curdling squeal erupts from behind as a terrifying, invisible force slams you violently to the ground!\n", 30);
    }
}
