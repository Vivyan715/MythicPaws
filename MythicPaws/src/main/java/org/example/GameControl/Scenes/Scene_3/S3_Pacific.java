package org.example.GameControl.Scenes.Scene_3;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_4.S4_Pacific;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

import java.util.Scanner;

public class S3_Pacific implements StoryState {

//    private final Creature kitten;

    private final Player player;
    private final Creature piggy;

    public S3_Pacific() {
        this.piggy = new AdultCreatureFactory().createInvisiPig();
        this.player = GameSetting.getInstance().getPlayer();

    }


    @Override
    public void display() {

        System.out.println("\n=====================");
        System.out.println("     CHAPTER THREE     ");
        System.out.println("=====================");

        if (!player.getDeathList().isEmpty()) {
            TextEffect.typeWriter("\nDays pass in uneasy quiet. As a silent peace offering, you leave a bowl of food at the edge of the yard each morning.\n", 70);
            TextEffect.sleep(800);
            TextEffect.typeWriter("Today, the clearing seems completely empty. Munching on an apple, you set the bowl down and turn to leave...\n", 70);
        } else {
            TextEffect.typeWriter("\nLife has settled into a strange new rhythm. You head out to the yard's shelter to feed your fiery feline guests.\n", 70);
            TextEffect.sleep(800);
            TextEffect.typeWriter("Today, the shelter appears empty. Munching on an apple, you leave the food inside anyway and turn to leave...\n", 70);
        }

        TextEffect.sleep(1000);
        TextEffect.typeWriter("\n...when a faint rustling and a soft oink stop you in your tracks.\nConfused, you turn around, seeing nothing. Beside you, your dog freezes in that same curious, cautious stance.\n", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nStepping closer, you stumble and drop your apple.\nBefore it hits the ground, it bounces in mid-air off something invisible! A loud squeal echoes, and a small pig suddenly flickers into view.\n", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nSnapping out of its shock, the creature snatches your apple with lightning speed, darts into the corner, and devours it while you stare in disbelief.\n", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nWhat do you want to do?\n", 70);
        TextEffect.typeWriter("[1] Grab a heavy branch and try to chase the weird creature off your property.\n", 30);
        TextEffect.typeWriter("[2] Slowly sit down on the ground and try to calm the scared animal down and earn its trust.\n", 30);
        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            TextEffect.typeWriter("\nOnce your brain processes what it just saw, you back away in sheer panic and shout for your dog.\n", 70);
            TextEffect.typeWriter("Your screaming startles the pig—it vanishes again, letting out a deafening, invisible squeal as chaos erupts!\n", 70);


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

            Battle battle = new Battle(player, piggy);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) {
                return new StartMenu();
            } else {
                player.addDeathList(piggy);
                return new S3_Aggressive();
            }


        } else if (choice == 2) {
            TextEffect.typeWriter("\nSensing no danger, the little pig stops flickering, cautiously trots over to nudge your hand, and settles down beside you.\n", 70);

            player.addFriend(piggy);

            GameSetting.getInstance().addPacifistChoice();
            return new S4_Pacific();
        }

        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
