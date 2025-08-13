package net.halfheart.engine.core;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.halfheart.lonesomeroad.PlayerData;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PlayerDataJsonWriter {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static void savePlayerData(Path file) throws IOException {
        JsonObject root = new JsonObject();

        for (Field field : PlayerData.class.getDeclaredFields()) {
            int mods = field.getModifiers();
            if (!Modifier.isStatic(mods) || field.isSynthetic()) continue;

            try {
                Object value = field.get(null);
                root.add(field.getName(), GSON.toJsonTree(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }

        try (Writer out = Files.newBufferedWriter(
                file,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE)) {
            GSON.toJson(root, out);
        }
    }
}
