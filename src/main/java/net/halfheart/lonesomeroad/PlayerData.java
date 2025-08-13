package net.halfheart.lonesomeroad;

public class PlayerData {
    // Health affecting stats
    public static int healthCap = 0;
    public static int health = 0;
    public static boolean isRadiated = false;
    public static int radSeverity = 0;
    public static int radPoints = 0;
    public static int walkRate = 0;
    public static int foodRate = 0;

    // Specials + Name
    public static String playerName = "Soldier";
    public static int strength = 5;
    public static int perception = 5;
    public static int endurance = 5;
    public static int charisma = 5;
    public static int intelligence = 5;
    public static int agility = 5;
    public static int luck = 5;

    // Game Stats
    public static int xpToNextLevel = 100;
    public static int xp = 0;
    public static int level = 1;
    public static int day = 0;
    public static int daysSinceScav = 0;
    public static int location = 0;

    // Reputations
    public static int mwbosRep = 0;
    public static int legionRep = 0;
    public static int ncrRep = 0;
    public static int bosRep = 0;
    public static int enclaveRep = 0;

    // Inventory
    public static String[] inventory = new String[100];
    public static int prewarMoney = 0;
    public static int caps = 0;
    public static int food = 0;
    public static String equippedWeapon = "";
    public static String equippedArmor = "";
    public static int getCarryLimit() {
        return 100 + (strength * 10);
    }
}