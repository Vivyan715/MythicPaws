package org.example.Combat;

import org.example.Animals.Creatures.Creature;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Battle {
    // protected --> így a package-en belül elérik a leszármazott osztályok a változókat
    protected Player player;
    protected Creature creature;
    protected Scanner input;
    protected boolean isDefending;
    protected Map<Integer, CombatAction> actionList;

    //    a harc segédváltozói
    private int playerAttackCount = 0;  //egymás utáni attack opció választása
    private int lastChoice = 0; //mi volt a player utolsó választása
    private int creatureHealCount = 0;  //hányszor healelt már az állat (max 3)

    public Battle(Player player, Creature creature) {
        this.player = player;
        this.creature = creature;
        this.input = new Scanner(System.in);
        this.isDefending = false;

        this.actionList = new HashMap<>();
        // Válaszlehetőségek hozzáadása a HashMappehez (Strategy minta)
        this.actionList.put(1, new AttackAction(player.getAttackPower()));
        this.actionList.put(2, new DefendAction());
        this.actionList.put(3, new HealAction(player.getCombatHeal()));
    }

    // Sablon a fő harchoz --> Template Method minta
    public BattleResult startBattle() {
        BattleStart();

        while (player.getHp() > 0 && creature.getHp() > 0) {
            gameStatus();

            // Ebben a körben a játékos választ lépést
            playerTurn();

            if (creature.getHp() <= 0) break;

            CreatureTurn();

            // Visszaállítom a kör végi állapotot arra az esetre, ha védekezést választott
            isDefending = false;
        }

        if (player.getHp() > 0) {
            Victory();
            return new BattleResult(true, player.getHp());
        } else {
            Defeat();
            // vereség --> lény megmaradt HP-ját adja vissza
            return new BattleResult(false, creature.getHp());
        }
    }

    protected void gameStatus() {
        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║                       [ GAME STATUS ]                       ║");
        System.out.println("╠═════════════════════════════════════════════════════════════╣");
        System.out.println(String.format("║  %-58s ║", "Player HP: " + player.getHp() + "/100" + (isDefending ? " [DEFENSE]" : "")));
        System.out.println(String.format("║  %-58s ║", "Current HP of " + creature.getName() + " is: " + creature.getHp() + "/" + creature.getMaxHp()));
        System.out.println("╚═════════════════════════════════════════════════════════════╝\n");
    }

    protected void playerTurn() {
        CombatAction selectedAction = null;

        while (selectedAction == null) {
            TextEffect.typeWriter(" -------- YOUR TURN --------", 80);
            System.out.println("\n[1] Attack!\n[2] Defend\n[3] Heal");
            TextEffect.typeWriter("Choose your action: ", 30);

            int choice;

            try {
                choice = Integer.parseInt(input.nextLine());
                selectedAction = actionList.get(choice);
                lastChoice = choice;

                if (choice == 1) playerAttackCount++; //itt növeli ha a támadást választotta

                else playerAttackCount = 0; //ha bármi mást akkor lenullázom, hogy az egymás utáni támadásokat számolja

                if (selectedAction == null) {
                    TextEffect.typeWriter("Invalid option! Please choose [1], [2] or [3]!\n", 30);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }

        selectedAction.execute(player, creature, this);
    }

    // a lény köre
    protected void CreatureTurn() {
        TextEffect.sleep(500);
        TextEffect.typeWriter("\n --------" + creature.getName() + "'S TURN --------", 80);

        boolean canHeal = (creatureHealCount < 3) && (creature.getHp() < creature.getMaxHp());

        boolean isLastBreaths = creature.getHp() <= (creature.getMaxHp() * 0.2);

        if (playerAttackCount >= 2) {
            TextEffect.typeWriter("You angered the creature, so it unleashed its superpowers.", 30);

            creature.superPower();

            damagePlayer(creature.getSuperPowerDamage());

            playerAttackCount = 0;

        } else if (canHeal && isLastBreaths) {
//            creature healel mert az utolsó HP-jain funkcionál

            TextEffect.typeWriter(creature.getName() + " is critically wounded and retreats to heal.", 30);

            TextEffect.typeWriter("[Heals left for " + creature.getName() + ":" + (3 - creatureHealCount) + "]", 30);

            creatureHealCount++;

            creature.heal(creature.getHealAmount());

        } else if (lastChoice == 3 && canHeal) {
//            creature healel mert a player is
            TextEffect.typeWriter(creature.getName() + " follows your example, and retreats to heal.", 30);

            creatureHealCount++;

            creature.heal(creature.getHealAmount());
        } else {

//            ez a default eset
            TextEffect.typeWriter("\n" + creature.getName() + " attacks you!", 30);

            damagePlayer(creature.getAttackPower());
        }

        TextEffect.sleep(500);

    }

    public void damagePlayer(int baseDamage) {
        if (isDefending) {
            TextEffect.typeWriter("\nYou defended yourself! The incoming attack was completely blocked!\n", 30);
            return;
        }

        player.takeDamage(baseDamage);
        System.out.println("You took " + baseDamage + " damage!");
    }

    // ezeket a jelenetkhez lehet igazítani
    protected void BattleStart() {
    }  // harc elkezdése

    protected void Victory() {
        // player nyer
        TextEffect.typeWriter("\n>>>> YOU ARE THE WINNER!  <<<<<", 70);
        TextEffect.typeWriter("\nAlthough you killed an innocent animal, you won the battle. Congratulation... I guess...", 50);
    }

    protected void Defeat() {
        // player veszít --> elfogyott HP --> visszakerül a főmenübe mivel meghalt
        TextEffect.typeWriter("\n>>>>" + creature.getName().toUpperCase() + " WON THE BATTLE!   <<<<<", 70);

        TextEffect.typeWriter("\nSee? You kept messing with the nature and that's your punnishment. Next time try to be nice!", 50);

    }

    // SETTEREK ÉS GETTEREK
    public void setDefending(boolean isDefending) {
        this.isDefending = isDefending;
    }

    public boolean isDefending() {
        return isDefending;
    }
}
