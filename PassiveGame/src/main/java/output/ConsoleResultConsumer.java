package output;

import game.common.VariationResult;

public class ConsoleResultConsumer implements ResultConsumer {
    @Override
    public void sendResult(VariationResult result) {
        System.out.printf("There %d bulls and %d cows%n", result.getBulls(), result.getCows());
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
