package game.active;

import connector.rmi.RmiConnectorAdapter;
import game.common.Game;

public class ActiveMain {

    public static void main(String[] args) {
//        Game activeGame = new ActiveBullsCowsGame(new ConsoleConnector());
        Game activeGame = new ActiveBullsCowsGame(RmiConnectorAdapter.INSTANCE);
        activeGame.doFullGame();
    }
}
