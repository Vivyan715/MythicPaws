package org.example.Animals.Factories;

import org.example.Animals.Creatures.*;

//this is for creating the puppy versions of the creatures
public class PuppyCreatureFactory implements CreatureFactory {

    @Override
    public Creature createCatashtrophe() {
        return new Catashtrophe("Baby Catashtrophe", 60);
    }

    @Override
    public Creature createInvisiPig() {
        return new InvisiPig();
    }

    @Override
    public Creature createFrostNoodle() {
        return new FrostNoodle();
    }

    @Override
    public Creature createShadHop() {
        return new ShadHop();
    }

}
