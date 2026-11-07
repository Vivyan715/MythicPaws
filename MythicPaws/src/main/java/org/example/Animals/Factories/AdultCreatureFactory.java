package org.example.Animals.Factories;

import org.example.Animals.Creatures.*;

//this is for creating the adult versions of creatures
public class AdultCreatureFactory implements CreatureFactory {

    @Override
    public Creature createCatashtrophe() {
        return new Catashtrophe("Adult Catashtrophe", 120);
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
