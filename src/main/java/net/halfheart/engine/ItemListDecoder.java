package net.halfheart.engine;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemListDecoder {

    private static List<Weapon> weapons;

    static {
        loadWeapons();
    }

    // Inner class to represent a weapon
    public static class Weapon {
        String name;
        String desc;
        int value;
        String unit;
        String type;
        String special;
        int cost;
        int weight;

        // Getters for all fields
        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public int getValue() {
            return value;
        }

        public String getUnit() {
            return unit;
        }

        public String getType() {
            return type;
        }

        public String getSpecial() {
            return special;
        }

        public int getCost() {
            return cost;
        }

        public int getWeight() {
            return weight;
        }
    }

    // Load weapons from the JSON file
    private static void loadWeapons() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/weapons.json")) {
            Type listType = new TypeToken<ArrayList<Weapon>>() {}.getType();
            weapons = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            weapons = new ArrayList<>();
        }
    }

    // Get weapon by name
    private static Weapon getWeaponByName(String weaponName) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equalsIgnoreCase(weaponName)) {
                return weapon;
            }
        }
        return null;
    }

    // Individual getters
    public static String getName(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getName() : "Weapon not found";
    }

    public static String getDescription(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getDesc() : "Weapon not found";
    }

    public static int getValue(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getValue() : -1;
    }

    public static String getUnit(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getUnit() : "Weapon not found";
    }

    public static String getType(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getType() : "Weapon not found";
    }

    public static String getSpecial(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getSpecial() : "Weapon not found";
    }

    public static int getCost(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getCost() : -1;
    }

    public static int getWeight(String weaponName) {
        Weapon weapon = getWeaponByName(weaponName);
        return weapon != null ? weapon.getWeight() : -1;
    }
}
