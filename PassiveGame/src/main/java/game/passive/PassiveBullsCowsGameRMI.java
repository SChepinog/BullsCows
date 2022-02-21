package game.passive;

import connector.passive.rmi.RmiConnectorImpl;
import game.common.Variation;

public class PassiveBullsCowsGameRMI extends PassiveBullsCowsGame {

    BicycleLogger logger = new BicycleLogger();

    public PassiveBullsCowsGameRMI() {
        super(new RmiConnectorImpl());
    }

    public PassiveBullsCowsGameRMI(Variation secret) {
        super(new RmiConnectorImpl(), secret);
    }

    @Override
    public void doCongratulations() {
        logger.log(this.secret, this.attempts);
        super.doCongratulations();
    }
}
