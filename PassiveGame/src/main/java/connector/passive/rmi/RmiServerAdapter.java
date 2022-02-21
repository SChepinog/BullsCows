package connector.passive.rmi;

import game.common.Variation;
import game.common.VariationResult;

public class RmiServerAdapter {

    private Variation variation = null;
    private VariationResult variationResult = null;

    public RmiServerAdapter() {
        RmiServer.INSTANCE
            .setVariationConsumer(v -> this.variation = v)
            .setVariationResultSupplier(this::getResult);
    }

    public Variation getVariation() {
        while (variation == null) {
            Thread.yield();
        }
        String variationValue = variation.getValue();
        variation = null;
        return Variation.of(variationValue);
    }

    private VariationResult getResult() {
        while (variationResult == null) {
            Thread.yield();
        }
        VariationResult result = new VariationResult(variationResult.getBulls(), variationResult.getCows());
        variationResult = null;
        return result;
    }

    public void sendResult(VariationResult result) {
        this.variationResult = result;
    }
}
