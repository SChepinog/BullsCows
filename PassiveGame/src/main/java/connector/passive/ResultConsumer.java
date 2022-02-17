package connector.passive;

import game.common.VariationResult;

public interface ResultConsumer {
    void sendResult(VariationResult result);

    void sendMessage(String message);
}
