package org.example.Animals.Creatures;

//láthatatlan malac
public class InvisiPig extends Creature {

    public InvisiPig() {
        super("InvisiPig", 100);
    }

    @Override
    public void superPower() {
        System.out.println("The pig became invisible.");

//        TODO: szupererő megírása
    }
}
