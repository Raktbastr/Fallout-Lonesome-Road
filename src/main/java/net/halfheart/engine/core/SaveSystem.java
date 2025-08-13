package net.halfheart.engine.core;
import net.halfheart.lonesomeroad.PlayerData;

import java.io.IOException;
import java.nio.file.Path;
import java.time.*;

public class SaveSystem {
    public static void saveMenu() {

    }
    public static void loadMenu() {

    }
    public static void saveGame() throws IOException {
        PlayerDataJsonWriter.savePlayerData(Path.of("save/"+ PlayerData.playerName+LocalDateTime.now()+".json"));
    }
    public static void loadGame() {

    }
}