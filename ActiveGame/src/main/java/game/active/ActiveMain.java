package game.active;

import game.common.Game;
import rmi.RmiConnectorAdapter;

public class ActiveMain {

    public static void main(String[] args) {
//        MessageSender messageSender = new ConsoleMessageSender();
//        VariationResultSupplier resultSupplier = new ConsoleResultSupplier(messageSender);
//        Game activeGame = new ActiveBullsCowsGame(resultSupplier, messageSender);

        Game activeGame = new ActiveBullsCowsGame(RmiConnectorAdapter.INSTANCE, RmiConnectorAdapter.INSTANCE);
        activeGame.doFullGame();
    }
}
