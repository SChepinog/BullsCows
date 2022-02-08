package connector;

import game.common.Variation;

public interface MessageSender {

    void sendMessage(String messageToSend);

    void sendVariation(Variation variation);
}
