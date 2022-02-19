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
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.exit(30);
    }
}
