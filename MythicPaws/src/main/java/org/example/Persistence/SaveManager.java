package org.example.Persistence;

import org.example.Animals.Creatures.Creature;
import org.example.Animals.Factories.AdultCreatureFactory;
import org.example.Animals.Factories.PuppyCreatureFactory;
import org.example.GameControl.GameSetting;
import org.example.GameControl.Scenes.Scene_1;
import org.example.GameControl.Scenes.Scene_2.S2_Agressive;
import org.example.GameControl.Scenes.Scene_2.S2_Pacific;
import org.example.GameControl.Scenes.Scene_3.S3_Aggressive;
import org.example.GameControl.Scenes.Scene_3.S3_Pacific;
import org.example.GameControl.Scenes.Scene_4.S4_Aggressive;
import org.example.GameControl.Scenes.Scene_4.S4_Pacific;
import org.example.GameControl.Scenes.Scene_5.S5_Aggressive;
import org.example.GameControl.Scenes.Scene_5.S5_Pacific;
import org.example.GameControl.StartMenu;
import org.example.GameControl.StoryState;
import org.example.Player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private static final String SaveFile = "savedGame.txt";

    public static boolean hasSave() {
        File file = new File(SaveFile);

        return file.exists() && file.length() > 0;
    }

    public static void GameSaver(StoryState currentScene) {
        File file = new File(SaveFile);

        Player player = GameSetting.getInstance().getPlayer();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            ez lesz az akualis jlente
            writer.write("SCENE=" + currentScene.getClass().getSimpleName());

            writer.newLine();

            writer.write("HP=" + player.getHp());

            writer.newLine();

            writer.write("PACIFIST=" + GameSetting.getInstance().getPacifistChoices());

            writer.newLine();
            writer.write("AGGRESSIVE=" + GameSetting.getInstance().getAggressiveChoices());

            writer.newLine();

//            listák mentései
            List<String> friendNames = new ArrayList<>();
            List<String> deathNames = new ArrayList<>();

            for (Creature c : player.getFriends()) {
                friendNames.add(c.getName());
            }
            writer.write("FRIENDS=" + String.join(",", friendNames));

            writer.newLine();

            for (Creature c : player.getDeathList()) {
                deathNames.add(c.getName());
            }
            writer.write("DEATHS=" + String.join(",", deathNames));

            writer.newLine();


        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    //    ez fogja visszatölteni az adatokat
    public static StoryState GameLoader() {
        File file = new File(SaveFile);

        if (!file.exists()) {
            System.out.println("No saved game found!");
            return new StartMenu();
        }

//        kezdeti értékek
        String sceneName = "StartMenu";
        int hp = 100;
        int pacifist = 0;
        int aggressive = 0;
        String friendsLine = "";
        String deathsLine = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SCENE=")) {

                    //a substring egy megadott karaktertől kezdve fog kivágni egy darabot a sz9vegből a szöveg végéig
                    sceneName = line.substring(6).trim();

                } else if (line.startsWith("HP=")) {

                    hp = Integer.parseInt(line.substring(3).trim());

                } else if (line.startsWith("PACIFIST=")) {

                    pacifist = Integer.parseInt(line.substring(9).trim());

                } else if (line.startsWith("AGGRESSIVE=")) {
                    aggressive = Integer.parseInt(line.substring(11).trim());
                } else if (line.startsWith("FRIENDS=")) {
                    friendsLine = line.length() > 8 ? line.substring(8).trim() : "";
                } else if (line.startsWith("DEATHS=")) {
                    deathsLine = line.length() > 7 ? line.substring(7).trim() : "";
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading save file: " + e.getMessage());
            return new StartMenu();
        }

//        itt állítom vissza az adtaokat
        GameSetting setting = GameSetting.getInstance();

        setting.setPacifistChoices(pacifist);

        setting.setAggressiveChoices(aggressive);

        Player player = setting.getPlayer();
        player.setHp(hp);
        player.clearLists();

//        állatok visszaállítása
        restoreCreatures(friendsLine, player, true);
        restoreCreatures(deathsLine, player, false);
        return restoreSceneByName(sceneName);

    }


    //ez fog segíteni abban hogy vissza állítsam a creatureöket
    private static void restoreCreatures(String line, Player player, boolean isFriend) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }


        String[] names = line.split(",");
//        mivel két fajta cretaure van ezért itt két factoryt kell használjak
        AdultCreatureFactory adultFactory = new AdultCreatureFactory();
        PuppyCreatureFactory puppyFactory = new PuppyCreatureFactory();

        for (String name : names) {
            String trimmedName = name.trim();
            Creature creature = null;


            if (trimmedName.equalsIgnoreCase("Catashtrophe")) {
                creature = puppyFactory.createCatashtrophe();

            } else if (trimmedName.equalsIgnoreCase("Adult Catashtrophe")) {

                creature = adultFactory.createCatashtrophe();

            } else if (trimmedName.equalsIgnoreCase("InvisiPig")) {

                creature = adultFactory.createInvisiPig();

            } else if (trimmedName.equalsIgnoreCase("FrostNoodle")) {

                creature = adultFactory.createFrostNoodle();

            } else if (trimmedName.equalsIgnoreCase("Shadhop")) {

                creature = adultFactory.createShadHop();
            }


            if (creature != null) {
                if (isFriend) {
                    player.addFriend(creature);
                } else {
                    player.addDeathList(creature);
                }
            }
        }
    }

    //ez fog segíteni abban hogy a sztorit vissza tudjam állítani az adott scene-hez
    private static StoryState restoreSceneByName(String sceneName) {
        switch (sceneName) {
            case "Scene_1":
                return new Scene_1();
            case "S2_Agressive":
                return new S2_Agressive();
            case "S2_Pacific":
                return new S2_Pacific(new PuppyCreatureFactory().createCatashtrophe());
            case "S3_Aggressive":
                return new S3_Aggressive();
            case "S3_Pacific":
                return new S3_Pacific();
            case "S4_Aggressive":
                return new S4_Aggressive();
            case "S4_Pacific":
                return new S4_Pacific();
            case "S5_Aggressive":
                return new S5_Aggressive();
            case "S5_Pacific":
                return new S5_Pacific();
            default:
                return new StartMenu();
        }
    }
}
