package game.passive;

import connector.passive.rmi.RmiConnectorImpl;
import game.common.Variation;

public class PassiveBullsCowsGameRMI extends PassiveBullsCowsGame {

    public PassiveBullsCowsGameRMI() {
        super(new RmiConnectorImpl());
    }

    public PassiveBullsCowsGameRMI(Variation secret) {
        super(new RmiConnectorImpl(), secret);
    }

    @Override
    public void doCongratulations() {
        super.doCongratulations();
    }
}
