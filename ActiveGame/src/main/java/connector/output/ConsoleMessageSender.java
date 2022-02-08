package connector.output;

import connector.MessageSender;
import game.common.Variation;

public class ConsoleMessageSender implements MessageSender {
    @Override
    public void sendMessage(String messageToSend) {
        System.err.println(messageToSend);
    }

    @Override
    public void sendVariation(Variation variation) {
        System.err.println("Is it " + variation.getValue() + " ?");
    }
}
