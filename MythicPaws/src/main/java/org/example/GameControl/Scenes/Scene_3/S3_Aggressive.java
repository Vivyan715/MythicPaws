package org.example.GameControl.Scenes.Scene_3;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Combat.Battle;
import org.example.Combat.BattleResult;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_4.S4_Aggressive;
import org.example.GameControl.Scenes.Scene_4.S4_Pacific;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Persistence.SaveManager;
import org.example.Player.Player;
import org.example.Tools.InputHandler;
import org.example.Tools.TextEffect;

public class S3_Aggressive implements StoryState {

    private final Player player;
    private final Creature piggy;

    public S3_Aggressive() {
        this.player = GameSetting.getInstance().getPlayer();
        this.piggy = new AdultCreatureFactory().createInvisiPig();
    }

    @Override
    public void display() {

        if (player.getDeathList().size() < 2) {
            TextEffect.typeWriter("\nThe massive mother looks at you one last time—not with anger, but with painful disbelief—before her flames die out and she collapses into glowing ash at your feet.\n", 70);
            TextEffect.typeWriter("Behind her, the kitten lets out a desperate whimper. The trust you earned yesterday shatters in an instant, and the tiny creature flees in terror into the deep woods.\n", 70);
            TextEffect.sleep(800);

            TextEffect.typeWriter("\nYou stand in the sudden, unnatural silence, a smoking shotgun in your hands.\n", 70);
            TextEffect.typeWriter("Even your dog refuses to come near you—and for the first time, it hits you: you weren't defending yourself against monsters. You became one.\n", 70);
            TextEffect.sleep(800);
        } else {
            TextEffect.typeWriter("\nThe massive beast finally collapses onto the grass, leaving behind nothing more than another pile of hot ash drifting into the wind—just like the one its cub left on your living room floor.\n", 60);
            TextEffect.sleep(2000);

            TextEffect.typeWriter("\nExhausted, bleeding, and heavy-hearted, you drag yourself back inside the cabin.\n", 60);
            TextEffect.typeWriter("The forest around you has fallen dead silent, and you can feel it in your bones: whatever lives out here does not forget, and this bloodshed has only just begun.\n", 60);
            TextEffect.sleep(2000);
        }

        System.out.println("\n=====================");
        System.out.println("     CHAPTER THREE     ");
        System.out.println("=====================");

        TextEffect.typeWriter("\nThe day after the fight, you head out to the shed for spare food and bandages.\n", 70);
        TextEffect.typeWriter("You find the door slightly ajar, with a faint clattering coming from inside, though no one is in sight.\n", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nSuddenly, a crate of apples tips over on its own, and one of the fruits stops mid-air, seemingly floating.\n", 70);
        TextEffect.typeWriter("With a startled oink, a small pig suddenly materializes out of thin air, backing into the corner and staring at you.\n", 70);
        TextEffect.sleep(1000);

        TextEffect.typeWriter("\nWhat do you want to do?\n", 70);
        TextEffect.typeWriter("[1] Freak out, shout for your dog, and scramble backward—this place is driving you insane!\n", 32);
        TextEffect.typeWriter("[2] Hold your nerve, back out of the shed quietly, and go search the internet for answers.\n", 32);
        TextEffect.typeWriter("[3] Save game and exit.\n", 32);
        TextEffect.typeWriter("========================================================================\n", 10);

    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            TextEffect.typeWriter("\nYour brain refuses to accept a floating-apple-stealing ghost pig. You scramble backward in a panic, yelling for your dog!\n", 70);
            TextEffect.typeWriter("Scared by the noise, the pig disappears again, squealing loudly in the small shed as it runs straight at you!\n", 70);

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


            Battle battle = new Battle(player, piggy);
            BattleResult result = battle.startBattle();

            if (!result.isVictory()) {
                return new StartMenu();
            } else {
                player.addDeathList(piggy);
                return new S4_Aggressive();
            }
        } else if (choice == 2) {

            TextEffect.typeWriter("\nYou force yourself to stay calm. Realizing the creature is just scared, you slowly back out of the shed and leave it be.\n", 70);

            player.addFriend(piggy);

            GameSetting.getInstance().addPacifistChoice();
            return new S4_Pacific();
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
