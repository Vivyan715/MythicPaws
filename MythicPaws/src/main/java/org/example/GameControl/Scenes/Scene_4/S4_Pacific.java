package org.example.GameControl.Scenes.Scene_4;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_5.S5_Aggressive;
import org.example.GameControl.Scenes.Scene_5.S5_Pacific;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

import java.util.Scanner;

public class S4_Pacific implements StoryState {

    private final Player player;
    private final Creature ferret;

    public S4_Pacific() {
        this.player = GameSetting.getInstance().getPlayer();
        this.ferret = new AdultCreatureFactory().createFrostNoodle();
    }

    @Override
    public void display() {


        if (!player.getDeathList().isEmpty()) {
            TextEffect.typeWriter("\nThough the weight of your past actions still lingers, sparing the little pig feels like the first real step toward making things right.\n", 70);
        } else {
            TextEffect.typeWriter("\nThe small clearing around your cabin is slowly turning into a peaceful sanctuary where these strange creatures finally feel safe.\n", 70);
        }
        TextEffect.sleep(800);

        System.out.println("\n=====================");
        System.out.println("    CHAPTER FOUR     ");
        System.out.println("=====================");


        TextEffect.typeWriter("\nThe next morning, you try to head outside, only to find the doorknob freezing cold and the lock completely iced over.\n", 70);

        TextEffect.typeWriter("Peering through the window, you spot the culprit: a snow-white ferret darting across the porch steps, leaving a thick trail of frost wherever its paws touch the wood.\n", 70);

        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nAfter forcing the frozen door open, the creature doesn't run away—instead, it turns to face you and lets out a sharp hiss.\n", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nWhat do you want to do?\n", 70);

        TextEffect.typeWriter("[1] Refuse to let it wreck your cabin—grab your weapon and attack!\n", 32);

        TextEffect.typeWriter("[2] Crouch down on the threshold and offer a piece of food to show you mean no harm.\n", 32);

        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            TextEffect.typeWriter("\nYou refuse to let this little menace freeze your home. Grabbing your weapon, you lunge forward!\n", 70);

            TextEffect.typeWriter("The ferret puffs up, its fur coated in frost, and with a piercing hiss, it darts straight at you!\n", 70);

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

            Battle battle = new Battle(player, ferret);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) {
                return new StartMenu();
            } else {
                player.addDeathList(ferret);
                return new S5_Aggressive();
            }


        } else if (choice == 2) {
            TextEffect.typeWriter("\nYou slowly crouch on the icy doorstep and gently slide a piece of meat across the frosted wood.\n", 70);

            TextEffect.typeWriter("The ferret sniffs the air cautiously, stops hissing, and pads over to nibble the food—leaving tiny, harmless frost prints at your feet.\n", 70);

            player.addFriend(ferret);

            GameSetting.getInstance().addPacifistChoice();

            return new S5_Pacific();
        }


        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
