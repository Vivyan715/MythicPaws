package org.example.GameControl.Scenes;

import org.example.Animals.Creatures.Creature;
import org.example.GameControl.GameSetting;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Closing_Scene implements StoryState {

    private final Player player;

    public Closing_Scene() {
        this.player = GameSetting.getInstance().getPlayer();
    }

    @Override
    public void display() {
        if (player.getDeathList().isEmpty()) {

            TextEffect.typeWriter("\nThe next morning, you pull out your ranger journal. Not to file an official report on the strange anomalies, but to flip through the pages with a smile, reading the nicknames you gave them.\n", 70);
            TextEffect.sleep(1000);

            TextEffect.typeWriter("\nStepping out onto the porch, you see the forest's extraordinary residents playing peacefully in the morning sun: the Emberpaw family basking in the grass, Invisipig floating apples nearby, Frostnoodle leaving tiny frost sparkles on the railing, and Shadhop darting playfully between the shadows.\n", 70);
            TextEffect.typeWriter("Right in the middle of it all, your dog wags its tail happily among its new friends.\n", 70);
            TextEffect.sleep(1000);

            TextEffect.typeWriter("\nYou close the journal and realize this cabin is no longer a lonely outpost, but a living sanctuary. Some secrets aren't meant to be reported—they're meant to be protected, and you proudly remain their guardian.\n", 70);
            TextEffect.sleep(1000);

        } else if (player.getFriends().size() > player.getDeathList().size()) {

            TextEffect.typeWriter("\nBy the end of the day, the forest finally grows quiet.\n", 70);
            TextEffect.typeWriter("From a safe distance among the trees, the surviving creatures watch you cautiously—they no longer fear you, though they haven't forgotten what happened either.\n", 70);
            TextEffect.sleep(1000);

            TextEffect.typeWriter("\nYour dog sits quietly beside you on the porch. It is a fragile truce, but you've learned a vital lesson: surviving in these woods doesn't always require a weapon. Sometimes, it just takes patience.\n", 70);
            TextEffect.sleep(1000);
        } else if (player.getDeathList().size() > player.getFriends().size()) {
            TextEffect.typeWriter("\nAs night falls, a deep, ominous rumble echoes from the heart of the forest, as if the very trees were mourning the fallen creatures.\n", 70);
            TextEffect.sleep(1000);

            TextEffect.typeWriter("\nThough you survived the first wave, countless pairs of glowing eyes begin to flicker in the distant darkness.\n", 70);
            TextEffect.typeWriter("You grip your weapon tighter. This wasn't the end of the fight—it was only the beginning of the war.\n", 70);
            TextEffect.sleep(1000);
        }

        List<Creature> friends = new ArrayList<>(player.getFriends());
        friends.sort(Comparator.comparing(Creature::getName));

        List<Creature> defeated = new ArrayList<>(player.getDeathList());
        defeated.sort((creature1, creature2) -> creature1.getName().compareToIgnoreCase(creature2.getName()));

        System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║                    THANK YOU FOR PLAYING!                   ║");
        System.out.println("╠═════════════════════════════════════════════════════════════╣");
        System.out.println(String.format("║  %-57s  ║", "GAME STATISTICS:"));
        System.out.println(String.format("║  %-57s  ║", "  Creature friends: " + player.getFriends().size()));
        if (friends.isEmpty()) {
            System.out.println(String.format("║  %-57s  ║", " --- (none, you've killed everyone) "));
        } else {
            for (Creature c : friends) {
                System.out.println(String.format("║  %-57s  ║", "   - " + c.getName()));
            }
        }

        System.out.println(String.format("║  %-57s  ║", "  Creatures defeated: " + player.getDeathList().size()));

        if (defeated.isEmpty()) {
            System.out.println(String.format("║  %-57s  ║", "   (None - You saved everyone!)"));
        } else {
            for (Creature c : defeated) {
                System.out.println(String.format("║  %-57s  ║", "   - " + c.getName()));
            }
        }
        System.out.println("╠═════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                             ║");
        System.out.println(String.format("║  %-57s  ║", "What would you like to do next?"));
        System.out.println(String.format("║  %-57s  ║", "  [1] Play a new game"));
        System.out.println(String.format("║  %-57s  ║", "  [2] Exit"));
        System.out.println("║                                                             ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝");

    }

    @Override
    public StoryState handleChoice(int choice) {
        if (choice == 1) {
            System.out.println("\nStarting a new adventure...\n");

            return new StartMenu();
        } else if (choice == 2) {
            System.out.println("\n╔═════════════════════════════════════════════════════════════╗");
            System.out.println("║  Oh, you are already leaving? Alright.                      ║");
            System.out.println("║  Goodbye then!                                              ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝\n");
            System.exit(0);
        }

        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }
}
