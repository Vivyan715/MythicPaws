package org.example.GameControl.Scenes.Scene_2;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_3.S3_Aggressive;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

public class S2_Agressive implements StoryState {

    private Creature kitten;
    private Creature catashtrophe;

    public S2_Agressive(Creature kitten) {
        this.kitten = kitten;

    }

    @Override
    public void display() {

        TextEffect.typeWriter("\nThe beast collapses lifelessly, but its body does not grow cold. Quite the opposite!\n", 40);
        TextEffect.typeWriter("Upon his death, his energy is unleashed, and the unnatural heat radiating from his fur sets the carpet ablaze.\n", 40);
        TextEffect.typeWriter("In the midst of chaos, your priorities change immediately: saving the house becomes your top priority.\n", 40);
        TextEffect.typeWriter("However, while putting out the flames, you suffer severe second-degree burns on your hand and arm.\n", 40);

        TextEffect.typeWriter("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 35);

        TextEffect.sleep(2500);

        System.out.println("\n=====================");
        System.out.println("     CHAPTER TWO     ");
        System.out.println("=====================");

    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            GameSetting.getInstance().addAggressiveChoice();
//        itt hozom létre a macskát is és itt kérem le a playert is mivel csak ebben a döntésben van használva

            Creature adultCat = new AdultCreatureFactory().createCatashtrophe();
            Player player = GameSetting.getInstance().getPlayer();

            Battle battle = new Battle(player, adultCat);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) return new StartMenu();
            else return new S3_Aggressive();


        }

//        TODO: PACIFISTA FOLYTATÁS


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }

}
