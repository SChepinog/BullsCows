package rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import game.common.Variation;
import game.common.VariationResult;
import input.VariationSupplier;
import output.ResultConsumer;

public class RmiConnectorAdapter implements RmiConnector, VariationSupplier, ResultConsumer {

    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    public static RmiConnectorAdapter INSTANCE = new RmiConnectorAdapter();
    private final Registry registry;
    private RmiConnector rmiConnector = null;
    private Variation variation = null;
    private VariationResult variationResult = null;

    private RmiConnectorAdapter() {
        try {
            this.registry = LocateRegistry.createRegistry(2732);
            Remote stub = UnicastRemoteObject.exportObject(this, 0);
            registry.bind(UNIQUE_BINDING_NAME, stub);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (RemoteException | AlreadyBoundException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Variation getVariation() {
        System.out.println("Ask for variation");
        while (variation == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got variation " + variation.getValue());
        return variation;
    }

    @Override
    public void sendResult(VariationResult result) {
        System.out.println("Send result " + result);
        this.variationResult = result;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Message to send: " + message);
    }

    @Override
    public VariationResult testVariation(Variation variation) throws RemoteException {
        System.out.println("Got variation " + variation.getValue());
        this.variation = variation;
        while (variationResult == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got result " + variationResult);
        return variationResult;
    }
}
