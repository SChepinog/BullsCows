package game.active;

import connector.active.rmi.RmiConnectorAdapter;

public class ActiveBullsCowsGameRMI extends ActiveBullsCowsGame {

    public ActiveBullsCowsGameRMI() {
        super(RmiConnectorAdapter.INSTANCE);
    }
}
