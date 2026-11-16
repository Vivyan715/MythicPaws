package org.example.Animals.Factories;

import org.example.Animals.Creatures.*;

//this is for creating the adult versions of creatures
public class AdultCreatureFactory implements CreatureFactory {

    @Override
    public Creature createCatashtrophe() {
        Catashtrophe creature = new Catashtrophe("Adult Catashtrophe", 120);
        creature.setAttackPower(30);
        creature.setHealAmount(30);
        creature.setSuperPowerDamage(40);
        return creature;
    }

    @Override
    public Creature createInvisiPig() {
        InvisiPig creature = new InvisiPig();
        creature.setAttackPower(30);
        creature.setHealAmount(30);
        creature.setSuperPowerDamage(40);
        return creature;
    }

    @Override
    public Creature createFrostNoodle() {
        FrostNoodle creature = new FrostNoodle();
        creature.setAttackPower(30);
        creature.setHealAmount(30);
        creature.setSuperPowerDamage(40);
        return creature;
    }

    @Override
    public Creature createShadHop() {
        ShadHop creature = new ShadHop();
        creature.setAttackPower(30);
        creature.setHealAmount(30);
        creature.setSuperPowerDamage(40);
        return creature;
    }
}
