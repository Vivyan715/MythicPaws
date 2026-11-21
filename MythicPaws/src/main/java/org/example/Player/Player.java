package org.example.Player;

//TODO: ADD INVENTORY


import org.example.Animals.Creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int hp;
    private int attackPower;

    private int combatHeal;  //itt adja meg a heal amountot harc közben

    private List<Creature> friends = new ArrayList<>();  //ide gyűjti az állat barátokat --> sztori vezérlés
    private List<Creature> deathList = new ArrayList<>();
    //ide gyűjti a megölt állatokat --> sztori vezérlés

    public Player() {
        this.hp = 100;
        this.attackPower = 20;
        this.combatHeal = 25;
    }

    public void heal(int amount) {
        if (this.hp >= 100) {
            System.out.println("You're already healed!");
            this.hp = 100;
        } else if ((this.hp + amount) > 100) {
            this.hp = 100;
        } else {
            this.hp += amount;
        }
    }

    public void takeDamage(int amount) {
        if (amount <= 0) {
            return;
        }
        
        this.hp -= amount;

        if (this.hp <= 0) {
            this.hp = 0;
        }
    }

//    GETTER ÉS SETTER

    public int getAttackPower() {
        return attackPower;
    }


    public int getHp() {
        return hp;
    }

    public int getCombatHeal() {
        return combatHeal;
    }

    public List<Creature> getFriends() {
        return friends;
    }

    public List<Creature> getDeathList() {
        return deathList;
    }

    public void addFriend(Creature creature) {
        friends.add(creature);
    }

    public void addDeathList(Creature creature) {
        deathList.add(creature);
    }

}
