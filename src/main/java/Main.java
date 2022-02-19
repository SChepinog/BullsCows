public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread rmiThread = new Thread(new RmiServerLauncher());
        Thread passiveThread = new Thread(new PassiveGameLauncher());
        Thread activeThread = new Thread(new ActiveGameLauncher());

        rmiThread.start();
        passiveThread.start();
        Thread.sleep(1000);
        activeThread.start();
    }
}
