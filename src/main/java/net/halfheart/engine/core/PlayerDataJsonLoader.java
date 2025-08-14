package net.halfheart.engine.core;
import com.google.gson.*;
import net.halfheart.lonesomeroad.PlayerData;
import java.io.IOException;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerDataJsonLoader {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static void loadPlayerData(Path file) throws IOException {
        if (!Files.exists(file)) {
            throw new IOException("Save file not found: " + file);
        }

        try (Reader in = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            JsonElement rootEl = JsonParser.parseReader(in);
            if (rootEl == null || !rootEl.isJsonObject()) {
                throw new IOException("Invalid save format: expected a JSON object");
            }
            JsonObject root = rootEl.getAsJsonObject();

            for (Field field : PlayerData.class.getDeclaredFields()) {
                int mods = field.getModifiers();
                if (!Modifier.isStatic(mods) || field.isSynthetic()) continue;

                String name = field.getName();
                if (!root.has(name) || root.get(name).isJsonNull()) continue;

                try {
                    JsonElement el = root.get(name);
                    Object value = GSON.fromJson(el, field.getGenericType());

                    if (field.getType().isArray()
                            && field.getType().getComponentType() == String.class
                            && value != null
                            && "inventory".equals(name)) {
                        String[] fromFile = (String[]) value;
                        String[] target = new String[PlayerData.inventory.length];
                        int len = Math.min(fromFile.length, target.length);
                        System.arraycopy(fromFile, 0, target, 0, len);
                        value = target;
                    }

                    field.setAccessible(true);
                    field.set(null, value);
                } catch (IllegalAccessException e) {
                    throw new IOException("Unable to set field: " + name, e);
                } catch (JsonParseException e) {
                    throw new IOException("Failed to parse field '" + name + "': " + e.getMessage(), e);
                }
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            throw new IOException("Failed to read JSON: " + e.getMessage(), e);
        }
    }

}
