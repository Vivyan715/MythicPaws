package org.example.Animals.Creatures;

// ez a fájl az állatok alapja
public abstract class Creature {

    private String name;
    private final int maxHp;
    private int hp;
    private int attackPower;
    private int healAmount;
    private int superPowerDamage;

    public Creature(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public abstract void superPower();  //csak megmondja, hogy majd kell lennie egy szuperkézpessségnek

    //    GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public int getSuperPowerDamage() {
        return superPowerDamage;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public void setSuperPowerDamage(int superPowerDamage) {
        this.superPowerDamage = superPowerDamage;
    }

    public int takeDamage(int amount) {
        if (this.hp <= 0) {
            System.out.println(this.name + "'s already dead!");
            return 0;
        }

        if (amount <= 0) {
            System.out.println("The amount of damage cannot be a negative value!");
            return 0;
            //ha a sebzés értéke negatív visszatér --> érvénytelen
        }

        this.hp -= amount;

        if (this.hp <= 0) {
            this.hp = 0;
            System.out.println(this.name + " died! RIP.");
            return this.hp;
        }

        return this.hp;

    }

    public int heal(int amount) {
        if (this.hp <= 0) {
            System.out.println(this.name + "'s dead. Please don't mess with the dead, you're not God!");
            return 0;
        }

        this.hp += amount;
        if (this.hp > this.maxHp) this.hp = this.maxHp;

        return this.hp;
    }
}
