package org.example.GameControl.Scenes;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.CreatureFactory;
import org.example.Animals.Factories.PuppyCreatureFactory;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_2.S2_Pacific;
import org.example.GameControl.StoryState;
import org.example.Tools.AsciiArt;
import org.example.Tools.TextEffect;

public class Scene_1 implements StoryState {

    //    létrehozza a cica példányát
    private Creature kitten;

    public Scene_1() {
        CreatureFactory factory = new PuppyCreatureFactory();
        this.kitten = factory.createCatashtrophe();
        this.kitten.takeDamage(20); //mivel éhes és egyedül van
    }

    @Override
    public void display() {
        System.out.println("=====================");
        System.out.println("     CHAPTER ONE     ");
        System.out.println("=====================");

        TextEffect.sleep(1000);

        System.out.println("You live as a ranger in a small wooden cabin, hidden deep within the forest.");
        System.out.println("One evening, on your way home, you stumble upon a strange creature in the fallen leaves.");
        System.out.println("It looks like a kitten, trembling in the cold. Taking pity on it, you carefully scoop it up and carry it home.\n");

        TextEffect.sleep(10000);

        TextEffect.typeWriter("Even then, a strange feeling tells you this is no ordinary cat.\n", 25);


        System.out.println("Back at the cabin, your dog greets you, immediately sniffing the mysterious little stranger with intense curiosity.\n");

        System.out.println("As you lean in to examine the creature closer, it hisses in displeasure.");
        TextEffect.sleep(3000);
        System.out.println("Suddenly, its fur begins to heat up, glowing like hot coals. The searing pain forces you to drop the little beast onto the floor.");

        TextEffect.typeWriter("Now you know for sure: you are definitely not dealing with a normal cat.\n", 25);

        System.out.println("You inspect your hands. There are faint burn marks on your palms, but thankfully, nothing that requires medical attention.");

        TextEffect.sleep(6000);

        TextEffect.typeWriter("\nWhat do you want to do?", 25);
//        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Call the animal control / get rid of the beast");
        System.out.println("[2] Share your dinner and take care of it\n");

        AsciiArt.drawCat();

        TextEffect.typeWriter("========================================================================\n", 10);

//        System.out.println("========================================================================\n");
    }

    @Override
    public StoryState handleChoice(int choice) {

        if (choice == 1) {
            GameSetting.getInstance().addAggressiveChoice();
//            TODO: aggresszív s2 meghívása
        } else if (choice == 2) {
            GameSetting.getInstance().addPacifistChoice();

            System.out.println("\nYou shared your dinner with the kitten.");
            System.out.println(this.kitten.getName() + " is healed. Current hp of " + this.kitten.getName() + " is: " + this.kitten.getHp() + "HP");
            kitten.heal(20); //megetette és biztonságot adott neki

            return new S2_Pacific(kitten);
        }

        System.out.println("Incorrect answer! Please choose between [1] or [2].");
        return this;
    }

}
