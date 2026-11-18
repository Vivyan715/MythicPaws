package org.example.Animals.Creatures;

import org.example.Tools.TextEffect;

public class FrostNoodle extends Creature {
    public FrostNoodle() {
        super("Frostnoodle", 80);
    }

    @Override
    public void superPower() {
        TextEffect.typeWriter("\n>>> The " + getName() + " dodges your rapid shots like lightning! <<<\n", 30);
        TextEffect.typeWriter("With a single leap, it lunges at your arm! The unnatural cold radiating from its fur freezes your weapon and skin in seconds, causing excruciating pain!\n", 30);
    }
}
