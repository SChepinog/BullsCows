import game.common.Variation;
import game.common.generator.AllPossibleGenerator;
import util.Timer;

public class AllGamesMain {

    public static void main(String[] args) throws InterruptedException {
        Thread rmiThread = new Thread(new RmiServerLauncher());
        rmiThread.start();
        Thread.sleep(1000);
        Thread passiveThread = null;
        Thread activeThread = null;
        Timer allGames = new Timer("All games").start();
        for (Variation var : new AllPossibleGenerator().generateAllVariations()) {
            Timer oneGameTimer = new Timer("One game").start();
            passiveThread = new Thread(new PassiveGameLauncher(var));
            activeThread = new Thread(new ActiveGameLauncher());
            passiveThread.start();
            activeThread.start();

            while (passiveThread.isAlive() || activeThread.isAlive()) {
                Thread.yield();
            }
            System.out.println(oneGameTimer.stopAndGetElapsedTimeAsString());
            Thread.sleep(100);
        }
        System.out.println(allGames.stopAndGetElapsedTimeAsString());

        activeThread.interrupt();
        passiveThread.interrupt();
        rmiThread.interrupt();
        System.exit(30);
    }
}
