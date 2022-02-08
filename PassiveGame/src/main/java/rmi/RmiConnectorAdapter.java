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

    public static final RmiConnectorAdapter INSTANCE = new RmiConnectorAdapter();
    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    private Variation variation = null;
    private VariationResult variationResult = null;

    private RmiConnectorAdapter() {
        try {
            Registry registry = LocateRegistry.createRegistry(2732);
            Remote stub = UnicastRemoteObject.exportObject(this, 0);
            registry.bind(UNIQUE_BINDING_NAME, stub);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
        String variationValue = variation.getValue();
        variation = null;
        return Variation.of(variationValue);
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
        this.variation = null;
        VariationResult result = VariationResult.of(variationResult.getBulls(), variationResult.getCows());
        this.variationResult = null;
        return result;
    }
}
