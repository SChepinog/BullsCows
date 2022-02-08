package rmi;

import java.rmi.RemoteException;

import game.common.Variation;
import game.common.VariationResult;
import input.VariationResultSupplier;
import output.MessageSender;

public class RmiConnectorAdapter implements MessageSender, VariationResultSupplier {
    private VariationResult result = null;
    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    public static RmiConnectorAdapter INSTANCE = new RmiConnectorAdapter();
    private RmiConnector connector = null;

    private RmiConnectorAdapter() {

    }

    @Override
    public VariationResult getResult() {
        while (result == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got result " + result);
        return result;
    }

    @Override
    public void sendMessage(String messageToSend) {

    }

    @Override
    public void sendVariation(Variation variation) {
        result = null;
        try {
            result = connector.testVariation(variation);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
