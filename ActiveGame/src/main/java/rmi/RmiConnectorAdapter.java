package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import game.common.Variation;
import game.common.VariationResult;
import input.VariationResultSupplier;
import output.MessageSender;

public class RmiConnectorAdapter implements MessageSender, VariationResultSupplier {
    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    public static RmiConnectorAdapter INSTANCE = new RmiConnectorAdapter();
    private final RmiConnector connector;
    private Variation variation = null;

    private RmiConnectorAdapter() {
        try {
            Registry registry = LocateRegistry.getRegistry(2732);
            connector = (RmiConnector) registry.lookup(UNIQUE_BINDING_NAME);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public VariationResult getResult() {
        VariationResult result;
        try {
            result = connector.testVariation(variation);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("getResult " + result);
        return result;
    }

    @Override
    public void sendMessage(String messageToSend) {
//        System.out.println("Message to send " + messageToSend);
    }

    @Override
    public void sendVariation(Variation variation) {
        System.out.println("Send variation " + variation.getValue());
        this.variation = variation;
    }
}
