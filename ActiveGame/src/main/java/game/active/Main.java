package game.active;

import game.common.Game;
import input.ConsoleResultSupplier;
import input.VariationResultSupplier;
import output.ConsoleMessageSender;
import output.MessageSender;

public class Main {

    public static void main(String[] args) {
        MessageSender messageSender = new ConsoleMessageSender();
        VariationResultSupplier resultSupplier = new ConsoleResultSupplier(messageSender);
        Game activeGame = new ActiveBullsCowsGame(resultSupplier, messageSender);
        activeGame.begin();
        while (!activeGame.isFinished()) {
            activeGame.doIteration();
        }
        activeGame.doCongratulations();
    }
}
