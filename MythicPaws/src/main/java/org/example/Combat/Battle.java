package org.example.Combat;

import org.example.Animals.Creatures.Creature;
import org.example.Player.Player;
import org.example.Tools.TextEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Battle {
    // protected --> így a package-en belül elérik a leszármazott osztályok a változókat
    protected Player player;
    protected Creature creature;
    protected Scanner input;
    protected boolean isDefending;
    protected Map<Integer, CombatAction> actionList;

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

            int choice = 0;

            try {
                choice = Integer.parseInt(input.nextLine());
                selectedAction = actionList.get(choice);

                if (selectedAction == null) {
                    TextEffect.typeWriter("Invalid option! Please choose [1], [2] or [3]!\n", 30);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }

        selectedAction.execute(player, creature, this);
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
    protected abstract void BattleStart();  // harc elkezdése

    protected abstract void CreatureTurn(); // a lény köre

    protected abstract void Victory();      // player nyeresége

    protected abstract void Defeat();       // player veszít

    // SETTEREK ÉS GETTEREK
    public void setDefending(boolean isDefending) {
        this.isDefending = isDefending;
    }

    public boolean isDefending() {
        return isDefending;
    }
}
