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

public class RmiServer implements RmiConnector {

    public static final RmiServer INSTANCE = new RmiServer();
    private static final String UNIQUE_BINDING_NAME = "bulls.cows";
    private Consumer<Variation> variationConsumer;
    private Supplier<VariationResult> variationResultSupplier;

    public RmiServer setVariationConsumer(Consumer<Variation> variationConsumer) {
        this.variationConsumer = variationConsumer;
        return this;
    }

    public RmiServer setVariationResultSupplier(Supplier<VariationResult> variationResultSupplier) {
        this.variationResultSupplier = variationResultSupplier;
        return this;
    }

    private RmiServer() {
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
    public VariationResult testVariation(Variation variation) throws RemoteException {
        System.out.println("Got variation " + variation.getValue());
        variationConsumer.accept(variation);
        return variationResultSupplier.get();
    }
}
