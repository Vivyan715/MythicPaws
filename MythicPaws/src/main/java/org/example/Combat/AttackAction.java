package org.example.Combat;

import org.example.Animals.Creatures.Creature;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

public class AttackAction implements CombatAction {
    private final int damage;

    public AttackAction(int damage) {
        this.damage = damage;
    }

    @Override
    public void execute(Player player, Creature creature, Battle battle) {
        TextEffect.typeWriter("\nYou strike " + creature.getName() + " dealing " + damage + " damage!", 30);

        creature.takeDamage(damage);
    }
}
