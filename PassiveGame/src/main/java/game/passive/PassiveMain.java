package game.passive;

import connector.rmi.RmiConnectorAdapter;

public class PassiveMain {

    public static void main(String[] args) throws InterruptedException {
//        PassiveBullsCowsGame pGame = new PassiveBullsCowsGame(new ConsoleConnector());
        PassiveBullsCowsGame passiveGame = new PassiveBullsCowsGame(RmiConnectorAdapter.INSTANCE);
        passiveGame.doFullGame();
        Thread.sleep(200);
        System.exit(30);
    }
}
