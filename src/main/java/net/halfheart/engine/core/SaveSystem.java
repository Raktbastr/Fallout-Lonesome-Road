package net.halfheart.engine.core;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class SaveSystem {
    static String SAVE_DIR = "save/";
    public static void saveMenu() throws IOException {
        TerminalFunctions.clear();
        if (!Files.isReadable( Path.of(SAVE_DIR))){
            System.out.println("ERROR: Unable to read save directory");
            System.out.println("Please check permissions and try again");
            return;
        }
        if (!Files.isWritable( Path.of(SAVE_DIR))){
            System.out.println("ERROR: Unable to write to save directory");
            System.out.println("Please check permissions and try again");
            return;
        }
        System.out.println("============================");
        System.out.println("SAVE MENU");
        System.out.println("Pick a save slot to write to");
        System.out.println("============================");
        System.out.println("0) New Save");
        File directory = new File(SAVE_DIR);
        File[] saves = directory.listFiles();
        int saveAmt = 0;
        if (saves != null) {
            for (File save : saves) {
                if (save.getName().contains(".json")) {
                    System.out.println(saveAmt+1 + ")" + save.getName().replace(".json",""));
                    saveAmt++;
                }
            }
        }
        System.out.println(saveAmt+1 + ") Exit");
        Scanner smScanner = new Scanner(System.in);
        System.out.print("============================\nSelect: ");
        int smInt = smScanner.nextInt();
        if (smInt == 0) {
            Scanner nsScanner = new Scanner(System.in);
            System.out.print("What would you like to name this save? ");
            String nsName = nsScanner.nextLine();
            if (nsName.isEmpty() || Files.exists(Path.of(SAVE_DIR+nsName+".json"))) {
                System.out.println("Invalid name");
                System.out.println("Press enter to return to the menu");
                nsScanner.nextLine();
                saveMenu();
                return;
            }
            System.out.println("Saving to slot "+(saveAmt+1));
            saveGame(nsName+".json");
            System.out.println("Saved");
        } else if (smInt > 0 && smInt <= saveAmt) {
            System.out.println("Saving to slot "+smInt);
            File fuckassDir = new File(SAVE_DIR);
            File[] fuckassSaves = fuckassDir.listFiles();
            for (File ugh : fuckassSaves) {
                if (fuckassDir.getName().contains(".json")) {
                    System.out.println(saveAmt + 1 + ")" + ugh.getName().replace(".json", ""));
                    saveAmt++;
                    if (smInt == saveAmt) {
                        saveGame(ugh.getName());
                    }
                }
            }
            System.out.println("Saved");
        } else if (smInt == saveAmt+1) {
            System.out.println("Exiting");
        } else {
            System.out.println("Invalid input");
            saveMenu();
        }
    }
    public static void loadMenu() throws IOException {
        if (!Files.isReadable( Path.of(SAVE_DIR))){
            System.out.println("ERROR: Unable to read save directory");
            System.out.println("Please check permissions and try again");
        }
        if (!Files.isWritable( Path.of(SAVE_DIR))){
            System.out.println("ERROR: Unable to write to save directory");
            System.out.println("Please check permissions and try again");
        }
        System.out.println("============================");
        System.out.println("LOAD MENU");
        System.out.println("Pick a save slot to load");
        System.out.println("============================");
        File directory = new File(SAVE_DIR);
        File[] saves = directory.listFiles();
        int saveAmt = 0;
        if (saves != null) {
            for (File save : saves) {
                if (save.getName().contains(".json")) {
                    System.out.println(saveAmt+1 + ")" + save.getName().replace(".json",""));
                    saveAmt++;
                }
            }
        } else {
            System.out.println("No saves found");
        }
        System.out.println(saveAmt+1 + ") Exit");
        Scanner lmScanner = new Scanner(System.in);
        System.out.print("============================\nSelect: ");
        int lmInt = lmScanner.nextInt();
        if (lmInt == 0 || lmInt > saveAmt) {
            System.out.println("Invalid input");
            loadMenu();
        } else {
            System.out.println("Loading save ");
            File fuckassDir = new File(SAVE_DIR);
            File[] fuckassSaves = fuckassDir.listFiles();
            for (File ugh : fuckassSaves) {
                if (fuckassDir.getName().contains(".json")) {
                    System.out.println(saveAmt+1 + ")" + ugh.getName().replace(".json",""));
                    saveAmt++;
                    if (lmInt == saveAmt) {
                        loadGame(ugh.getName());
                    }
                }
            }
        }
    }
    public static void saveGame(String fileName) throws IOException {
        PlayerDataJsonWriter.savePlayerData(Path.of(SAVE_DIR+fileName));
    }
    public static void loadGame(String fileName) throws IOException {
        PlayerDataJsonLoader.loadPlayerData(Path.of(SAVE_DIR+fileName));
    }
}