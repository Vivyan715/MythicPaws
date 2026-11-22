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
import org.example.Persistence.SaveManager;
import org.example.Player.Player;
import org.example.Tools.InputHandler;
import org.example.Tools.TextEffect;

public class S4_Aggressive implements StoryState {

    private final Player player;
    private final Creature ferret;

    public S4_Aggressive() {
        this.player = GameSetting.getInstance().getPlayer();
        this.ferret = new AdultCreatureFactory().createFrostNoodle();
    }

    @Override
    public void display() {
        System.out.println("\n=====================");
        System.out.println("    CHAPTER FOUR     ");
        System.out.println("=====================");


        TextEffect.typeWriter("\nAfter the chaotic fight with the invisible pig, you head over to check your\n rainwater barrel and your dog's water bowl—only to find both frozen solid, even though it isn't winter.\n", 70);

        TextEffect.typeWriter("Balancing on the rim of the barrel is a snow-white ferret.\n As it leaps down, a thick trail of frost instantly forms on the wooden planks beneath its paws.\n", 70);

        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nIt spots you, puffs up its fur, and lets out an aggressive hiss.\n", 70);
        TextEffect.sleep(800);

        TextEffect.typeWriter("\nWhat do you want to do?\n", 70);

        TextEffect.typeWriter("[1] Raise your weapon and attack before it ruins the rest of your supplies!\n", 32);

        TextEffect.typeWriter("[2] Lower your guard, crouch down, and toss it a piece of meat to stop the bloodshed.\n", 32);
        TextEffect.typeWriter("[3] Save game and exit.\n", 32);
        TextEffect.typeWriter("========================================================================\n", 10);
    }

    @Override
    public StoryState handleChoice(int choice) {
        if (choice == 1) {
            TextEffect.typeWriter("\nYou refuse to let another freak ruin your water supply. Raising your weapon, you charge straight at it!\n", 70);

            TextEffect.typeWriter("The ferret puffs up, its fur coated in frost, and with a piercing hiss, it darts straight at you!\n", 70);

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

            Battle battle = new Battle(player, ferret);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) {
                return new StartMenu();
            } else {
                player.addDeathList(ferret);
                return new S5_Aggressive();
            }

        } else if (choice == 2) {
            TextEffect.typeWriter("\nTired of fighting everything in these woods, you lower your weapon, crouch down, and gently toss a piece of meat onto the frosted planks.\n", 70);

            TextEffect.typeWriter("The ferret sniffs the air cautiously, stops hissing, and pads over to nibble the food—leaving tiny, harmless frost prints at your feet.\n", 70);

            player.addFriend(ferret);

            GameSetting.getInstance().addPacifistChoice();

            return new S5_Pacific();

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
