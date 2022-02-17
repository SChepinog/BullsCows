package game.passive;

import game.common.Game;

public class PassiveMain {

    public static void main(String[] args) throws InterruptedException {
//        PassiveBullsCowsGame pGame = new PassiveBullsCowsGame(new ConsoleConnector());
        Game passiveGame = new PassiveBullsCowsGameRMI();
        passiveGame.doFullGame();
//        System.exit(30);
    }
}
