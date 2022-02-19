import game.common.Variation;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread rmiThread = new Thread(new RmiServerLauncher());
        rmiThread.start();
        Thread.sleep(1000);

        Thread passiveThread = new Thread(new PassiveGameLauncher(Variation.of("0987")));
        Thread activeThread = new Thread(new ActiveGameLauncher());
        passiveThread.start();
        activeThread.start();

        while (passiveThread.isAlive() || activeThread.isAlive()) {
            Thread.yield();
        }

        passiveThread = new Thread(new PassiveGameLauncher(Variation.of("0986")));
        activeThread = new Thread(new ActiveGameLauncher());
        passiveThread.start();
        activeThread.start();

        while (passiveThread.isAlive() || activeThread.isAlive()) {
            Thread.yield();
        }

        activeThread.interrupt();
        passiveThread.interrupt();
        rmiThread.interrupt();
        System.exit(30);
    }
}
