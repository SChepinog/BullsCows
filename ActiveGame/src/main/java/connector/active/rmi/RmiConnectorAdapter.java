package connector.active.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import connector.RmiConnector;
import connector.active.FullConnector;
import game.common.Variation;
import game.common.VariationResult;

public class RmiConnectorAdapter implements FullConnector {
    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    public static final RmiConnectorAdapter INSTANCE = new RmiConnectorAdapter();
    private final RmiConnector connector;
    private Variation variation = null;

    private RmiConnectorAdapter() {
        try {
            connector = (RmiConnector) LocateRegistry.getRegistry(2732).lookup(UNIQUE_BINDING_NAME);
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
        System.out.println("Active - getResult " + result);
        variation = null;
        return result;
    }

    @Override
    public void sendMessage(String messageToSend) {
        System.out.println("Active - Message to send " + messageToSend);
    }

    @Override
    public void sendVariation(Variation variation) {
        System.out.println("Active - sendVariation " + variation.getValue());
        this.variation = variation;
    }
}
