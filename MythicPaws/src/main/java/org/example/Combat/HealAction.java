package org.example.Combat;

import org.example.Animals.Creatures.Creature;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

public class HealAction implements CombatAction {
    private final int healAmount;

    public HealAction(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void execute(Player player, Creature creature, Battle battle) {
        player.heal(healAmount);

        TextEffect.typeWriter("You retreated for a few minutes to tend to your wounds. Your HP has increased.", 30);
    }
}
