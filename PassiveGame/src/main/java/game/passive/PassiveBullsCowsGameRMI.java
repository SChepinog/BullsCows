package game.passive;

import connector.passive.rmi.RmiConnectorAdapter;
import game.common.Variation;

public class PassiveBullsCowsGameRMI extends PassiveBullsCowsGame {

    public PassiveBullsCowsGameRMI() {
        super(RmiConnectorAdapter.INSTANCE);
    }

    public PassiveBullsCowsGameRMI(Variation secret) {
        super(RmiConnectorAdapter.INSTANCE, secret);
    }

    @Override
    public void doCongratulations() {
        super.doCongratulations();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(30);
    }
}
