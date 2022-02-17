package game.active;

import game.common.Game;

public class ActiveMain {

    public static void main(String[] args) {
//        Game activeGame = new ActiveBullsCowsGame(new ConsoleConnector());
        Game activeGame = new ActiveBullsCowsGameRMI();
        activeGame.doFullGame();
    }
}
