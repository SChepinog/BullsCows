package output;

public class ConsoleMessageSender implements MessageSender {
    @Override
    public void sendMessage(String messageToSend) {
        System.err.println(messageToSend);
    }
}
