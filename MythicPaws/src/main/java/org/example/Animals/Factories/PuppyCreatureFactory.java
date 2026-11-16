package org.example.Animals.Factories;

import org.example.Animals.Creatures.*;

//this is for creating the puppy versions of the creatures
public class PuppyCreatureFactory implements CreatureFactory {

    @Override
    public Creature createCatashtrophe() {
        Catashtrophe creature = new Catashtrophe("Kitten Catashtrophe", 60);
        creature.setAttackPower(10);
        creature.setHealAmount(8);
        creature.setSuperPowerDamage(15);
        return creature;
    }

    //    lehetőségnek írtam meg, későbbi fejlesztésre, a sztori jelenlegi állása szerint ezekre nem lesz szükség csak a macskára
    @Override
    public Creature createInvisiPig() {
        InvisiPig creature = new InvisiPig();
        creature.setAttackPower(10);
        creature.setHealAmount(15);
        creature.setSuperPowerDamage(0);
        return creature;
    }

    @Override
    public Creature createFrostNoodle() {
        FrostNoodle creature = new FrostNoodle();
        creature.setAttackPower(10);
        creature.setHealAmount(15);
        creature.setSuperPowerDamage(0);
        return creature;
    }

    @Override
    public Creature createShadHop() {
        ShadHop creature = new ShadHop();
        creature.setAttackPower(10);
        creature.setHealAmount(15);
        creature.setSuperPowerDamage(0);
        return creature;
    }

}
