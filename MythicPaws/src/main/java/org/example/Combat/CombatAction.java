package org.example.Combat;

import org.example.Animals.Creatures.Creature;
import org.example.Player.Player;

public interface CombatAction {
    //    megmondja hogy bármi ami ezt használja kötelezően rendelkezik egy végrehajtás metódussal
    void execute(Player player, Creature creature, Battle battle);
// a battle maga a harc vezérlése
}
