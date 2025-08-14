package net.halfheart.lonesomeroad;
import net.halfheart.engine.Debug;
import net.halfheart.engine.Game;
import net.halfheart.engine.core.Achievements;
import net.halfheart.engine.core.SaveSystem;
import net.halfheart.engine.core.TerminalFunctions;
import java.io.IOException;
import java.util.Scanner;

public class StartMenu {
    public static void start() throws IOException {
        System.out.println("===============");
        System.out.println("Fallout: Lonesome Road");
        System.out.println("===============");
        System.out.println("1) New Game\n2) Load Game\n3) Achievements\n4) Exit");
        Scanner mmInput = new Scanner(System.in);
        System.out.print("===============\nSelect:");
        int mmInputInt = mmInput.nextInt();
        switch (mmInputInt) {
            case 1:
                Game.newGame();
                System.exit(0);
            case 2:
                SaveSystem.loadMenu();
                System.exit(0);
            case 3:
                Achievements.achievementMenu();
                System.exit(0);
            case 4:
                System.exit(0);
            case 5:
                Debug.test();
            default:
                TerminalFunctions.clear();
                start();
                break;
        }
    }
}
