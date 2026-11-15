package org.example.Combat;

import org.example.Animals.Creatures.Creature;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

public class DefendAction implements CombatAction {

    @Override
    public void execute(Player player, Creature creature, Battle battle) {
        battle.setDefending(true);  //a settert hívja meg ami a változóhoz lett létrehizva

        TextEffect.typeWriter("\nYou choosed to defend yourself and hide. Incoming damage will be blocked!", 30);
    }
}
