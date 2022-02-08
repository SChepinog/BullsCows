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
    private final Registry registry;
    private VariationResult result = null;
    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    public static RmiConnectorAdapter INSTANCE = new RmiConnectorAdapter();
    private RmiConnector connector = null;

    private RmiConnectorAdapter() {
        try {
            registry = LocateRegistry.getRegistry(2732);
            connector = (RmiConnector) registry.lookup(UNIQUE_BINDING_NAME);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
        System.out.println("Message to send " + messageToSend);
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
