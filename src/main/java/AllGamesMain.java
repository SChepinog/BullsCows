import game.common.Variation;
import game.common.VariationsUtils;

public class AllGamesMain {

    public static void main(String[] args) throws InterruptedException {
        Thread rmiThread = new Thread(new RmiServerLauncher());
        rmiThread.start();
        Thread.sleep(1000);
        Thread passiveThread = null;
        Thread activeThread = null;
        for (Variation var : VariationsUtils.generateAllVariations()) {
            passiveThread = new Thread(new PassiveGameLauncher(var));
            activeThread = new Thread(new ActiveGameLauncher());
            passiveThread.start();
            activeThread.start();

            while (passiveThread.isAlive() || activeThread.isAlive()) {
                Thread.yield();
            }
        }

        activeThread.interrupt();
        passiveThread.interrupt();
        rmiThread.interrupt();
        System.exit(30);
    }
}
