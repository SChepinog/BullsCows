package connector.passive.rmi;

import connector.passive.FullConnector;
import game.common.Variation;
import game.common.VariationResult;

public class RmiConnectorImpl implements FullConnector {

    private Variation variation = null;
    private VariationResult variationResult = null;

    public RmiConnectorImpl() {
        RmiServer.INSTANCE
            .setVariationConsumer(v -> this.variation = v)
            .setVariationResultSupplier(this::getResult);
    }

    @Override
    public Variation getVariation() {
        while (variation == null) {
            Thread.yield();
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

    private VariationResult getResult() {
        while (variationResult == null) {
            Thread.yield();
        }
        VariationResult result = new VariationResult(variationResult.getBulls(), variationResult.getCows());
        variationResult = null;
        return result;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Message to send: " + message);
    }
}
