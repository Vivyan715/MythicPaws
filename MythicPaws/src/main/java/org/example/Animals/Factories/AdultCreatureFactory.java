package org.example.Animals.Factories;

import org.example.Animals.Creatures.*;

//this is for creating the adult versions of creatures
public class AdultCreatureFactory implements CreatureFactory {

    @Override
    public Creature createCatashtrophe() {
        Catashtrophe creature = new Catashtrophe("Adult Catashtrophe", 120);
        creature.setAttackPower(15);
        creature.setHealAmount(15);
        creature.setSuperPowerDamage(40);
        return creature;
    }

    @Override
    public Creature createInvisiPig() {
        InvisiPig creature = new InvisiPig();
        creature.setAttackPower(12);
        creature.setHealAmount(20);
        creature.setSuperPowerDamage(35);
        return creature;
    }

    @Override
    public Creature createFrostNoodle() {
        FrostNoodle creature = new FrostNoodle();
        creature.setAttackPower(18);
        creature.setHealAmount(5);
        creature.setSuperPowerDamage(45);
        return creature;
    }

    @Override
    public Creature createShadHop() {
        ShadHop creature = new ShadHop();
        creature.setAttackPower(10);
        creature.setHealAmount(15);
        creature.setSuperPowerDamage(30);
        return creature;
    }
}
