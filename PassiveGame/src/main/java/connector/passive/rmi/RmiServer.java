package connector.passive.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Consumer;
import java.util.function.Supplier;

import connector.RmiConnector;
import game.common.Variation;
import game.common.VariationResult;

public enum RmiServer implements RmiConnector {

    INSTANCE;

    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    private Consumer<Variation> variationConsumer;
    private Supplier<VariationResult> variationResultSupplier;

    RmiServer() {
        try {
            Registry registry = LocateRegistry.createRegistry(2732);
            Remote stub = UnicastRemoteObject.exportObject(this, 0);
            registry.bind(UNIQUE_BINDING_NAME, stub);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public RmiServer setVariationConsumer(Consumer<Variation> variationConsumer) {
        this.variationConsumer = variationConsumer;
        return this;
    }

    public RmiServer setVariationResultSupplier(Supplier<VariationResult> variationResultSupplier) {
        this.variationResultSupplier = variationResultSupplier;
        return this;
    }

    @Override
    public VariationResult testVariation(Variation variation) throws RemoteException {
        System.out.println("Got variation " + variation.getValue());
        while (variationConsumer == null) {
            Thread.yield();
        }
        variationConsumer.accept(variation);
        return variationResultSupplier.get();
    }
}
