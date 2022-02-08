package rmi;

import java.rmi.RemoteException;

import game.common.Variation;
import game.common.VariationResult;
import input.VariationSupplier;
import output.ResultConsumer;

public class RmiConnectorAdapter implements RmiConnector, VariationSupplier, ResultConsumer {

    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    public static RmiConnectorAdapter INSTANCE = new RmiConnectorAdapter();
    RmiConnector rmiConnector = null;
    private Variation variation = null;
    private VariationResult variationResult = null;

    private RmiConnectorAdapter() {
    }

    @Override
    public Variation getVariation() {
        while (variation == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got variation " + variation.getValue());
        return null;
    }

    @Override
    public void sendResult(VariationResult result) {
        this.variationResult = result;
    }

    @Override
    public void sendMessage(String message) {
    }

    @Override
    public VariationResult testVariation(Variation variation) throws RemoteException {
        this.variation = variation;
        while (variationResult == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return variationResult;
    }
}
