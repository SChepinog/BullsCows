package game.passive;

import rmi.RmiConnectorAdapter;

public class PassiveMain {

    public static void main(String[] args) {
//        VariationSupplier variationSupplier = new ConsoleVariationSupplier();
//        ResultConsumer resultConsumer = new ConsoleResultConsumer();
//        PassiveBullsCowsGame passiveGame = new PassiveBullsCowsGame(variationSupplier, resultConsumer);

        PassiveBullsCowsGame passiveGame = new PassiveBullsCowsGame(RmiConnectorAdapter.INSTANCE, RmiConnectorAdapter.INSTANCE);
        passiveGame.doFullGame();
    }
}
