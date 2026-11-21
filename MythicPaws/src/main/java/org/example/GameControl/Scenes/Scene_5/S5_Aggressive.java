package org.example.GameControl.Scenes.Scene_5;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Closing_Scene;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Persistence.SaveManager;
import org.example.Player.Player;
import org.example.Tools.InputHandler;
import org.example.Tools.TextEffect;

public class S5_Aggressive implements StoryState {

    private final Player player;
    private final Creature bunny;

    public S5_Aggressive() {
        this.player = GameSetting.getInstance().getPlayer();
        this.bunny = new AdultCreatureFactory().createShadHop();
    }

    @Override
    public void display() {

        TextEffect.typeWriter("\nThe frost ferret collapses onto the grass, the ice slowly melting around it.\n Standing in the quiet yard with your weapon in hand, you no longer wonder why these creatures \nare coming—you just wait to see what emerges from the dark next.\n", 70);

        TextEffect.sleep(1000);

        System.out.println("\n=====================");
        System.out.println("    CHAPTER FIVE     ");
        System.out.println("=====================");


        TextEffect.typeWriter("\nAt dusk, you patrol your property with your weapon ready when you spot a silent,\n dark shadow slipping right through the fence slats of your vegetable garden.\n", 70);

        TextEffect.typeWriter("As the beam of the porch light hits it, the shadow suddenly\n takes physical form: a pitch-black rabbit stands in the mud before you, tensed and watching your every move.\n", 70);

        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nWhat do you want to do?\n", 70);

        TextEffect.typeWriter("[1] Enough is enough—raise your weapon and attack before it vanishes again!\n", 32);

        TextEffect.typeWriter("[2] Stop, lower your weapon, and let it eat from the garden in peace.\n", 32);
        TextEffect.typeWriter("[3] Save game and exit.\n", 32);
        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {
        if (choice == 1) {

            TextEffect.typeWriter("\nYou're done letting these freaks creep onto your land.\n Raising your weapon, you charge straight at it!\n", 70);

            TextEffect.typeWriter("The black rabbit flattens its ears, dark energy swirling around its paws as it lunges to defend itself!\n", 70);


            GameSetting.getInstance().addAggressiveChoice();

            if (player.getHp() < 40) {
                TextEffect.typeWriter("\nYour wounds from the previous fight are throbbing painfully (Current HP: " + player.getHp() + "/100).\n", 50);
                TextEffect.typeWriter("Do you want to use a first-aid kit to bandage your wounds before engaging? [1] Yes  [2] No: ", 30);

                String healChoice = InputHandler.readLine();

                if (healChoice.equals("1")) {
                    player.heal(25);

                    TextEffect.typeWriter("You quickly patched yourself up. HP restored to: " + player.getHp() + "/100!\n", 40);
                }
            }

            Battle battle = new Battle(player, bunny);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) {
                return new StartMenu();
            } else {
                player.addDeathList(bunny);
                return new Closing_Scene();
            }


        } else if (choice == 2) {

            TextEffect.typeWriter("\nExhausted by the endless bloodshed, you pause, lower your weapon, and step back from the garden bed.\n", 70);

            TextEffect.typeWriter("Sensing no threat, the black rabbit slowly\n relaxes and begins nibbling on a carrot in the warm glow of the porch light.\n", 70);
            TextEffect.sleep(1000);

            player.addFriend(bunny);

            GameSetting.getInstance().addPacifistChoice();

            return new Closing_Scene();
        } else if (choice == 3) {

            SaveManager.GameSaver(this);

            System.out.println("\n=================================================");
            System.out.println("  Game saved successfully! See you next time!   ");
            System.out.println("=================================================\n");

            System.exit(0);
        }


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
