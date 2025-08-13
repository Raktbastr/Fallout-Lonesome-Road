package net.halfheart.engine;
import net.halfheart.engine.core.SaveSystem;
import java.io.IOException;

public class Debug {
    public static void test() throws IOException {
        SaveSystem.saveGame();
    }
}
