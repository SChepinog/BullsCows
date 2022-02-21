package connector.passive.rmi;

import connector.passive.FullConnector;
import game.common.Variation;
import game.common.VariationResult;

public class RmiConnectorImpl implements FullConnector {

    private final RmiServerAdapter serverAdapter = new RmiServerAdapter();

    @Override
    public Variation getVariation() {
        return serverAdapter.getVariation();
    }

    @Override
    public void sendResult(VariationResult result) {
        serverAdapter.sendResult(result);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Passive - message to send: " + message);
    }
}
