package org.example.GameControl.Scenes.Scene_5;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Closing_Scene;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

import java.util.Scanner;

public class S5_Pacific implements StoryState {

    private final Player player;
    private final Creature bunny;

    public S5_Pacific() {
        this.player = GameSetting.getInstance().getPlayer();
        this.bunny = new AdultCreatureFactory().createShadHop();
    }

    @Override
    public void display() {


        if (!player.getDeathList().isEmpty()) {
            TextEffect.typeWriter("\nAs the white ferret peacefully nibbles on the food, the tension inside you eases a little more—maybe it isn't too late to make up for your past mistakes.\n", 70);
        } else {
            TextEffect.typeWriter("\nA whole little gathering of strange creatures has now formed around your cabin, each one clearly trusting you.\n", 70);
        }
        TextEffect.sleep(1000);

        System.out.println("\n=====================");
        System.out.println("    CHAPTER FIVE     ");
        System.out.println("=====================");


        TextEffect.typeWriter("\nAt dusk, you are checking your small vegetable garden beside the cabin when you notice a silent, dark shadow slipping right through the gaps in the fence—even though nothing is casting it.\n", 70);
        TextEffect.typeWriter("The moment the porch light hits it, the shadow suddenly takes physical form: a pitch-black rabbit stands on the soil before you, watching your reaction tensely.\n", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nWhat do you want to do?\n", 70);
        TextEffect.typeWriter("[1] Grab a nearby spade and attack before it slips back into the shadows!\n", 32);
        TextEffect.typeWriter("[2] Crouch down calmly and let it nibble on the vegetables in peace.\n", 32);
        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {
        if (choice == 1) {

            TextEffect.typeWriter("\nUnsettled by the unnatural shadow-rabbit, you snatch up a heavy spade and lunge forward!\n", 70);
            TextEffect.typeWriter("Startled, the black rabbit flattens its ears, dark energy swirling around its paws as it prepares to strike back!\n", 70);


            GameSetting.getInstance().addAggressiveChoice();

            if (player.getHp() < 40) {
                TextEffect.typeWriter("\nYour wounds from the previous fight are throbbing painfully (Current HP: " + player.getHp() + "/100).\n", 50);
                TextEffect.typeWriter("Do you want to use a first-aid kit to bandage your wounds before engaging? [1] Yes  [2] No: ", 30);

                Scanner sc = new Scanner(System.in);
                String healChoice = sc.nextLine();

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

            TextEffect.typeWriter("\nYou slowly crouch down by the garden bed and nudge a fresh carrot closer to the dark little visitor.\n", 70);

            TextEffect.typeWriter("Sensing no danger, the black rabbit relaxes its ears and begins munching happily in the warm glow of the porch light.\n", 70);


            player.addFriend(bunny);

            GameSetting.getInstance().addPacifistChoice();

            return new Closing_Scene();
        }


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
