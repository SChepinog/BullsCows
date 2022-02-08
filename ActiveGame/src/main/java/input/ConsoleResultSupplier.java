package input;

import java.util.Scanner;

import game.active.Animal;
import game.active.VariationResult;
import output.MessageSender;

public class ConsoleResultSupplier implements VariationResultSupplier {
    private final MessageSender messageSender;

    public ConsoleResultSupplier(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public VariationResult getResult() {
        int bulls = getNumberZeroToFour(Animal.BULLS);
        int cows = variationIsCorrect(bulls) ? 0 : getNumberZeroToFour(Animal.COWS);
        VariationResult inputResult = VariationResult.of(bulls, cows);
        if (VariationResult.getAllPossibleResults().contains(inputResult)) {
            return inputResult;
        } else {
            messageSender.sendMessage("You entered not possible combination of bulls and cows.\nPlease, check it and enter again");
            return getResult();
        }
    }

    private boolean variationIsCorrect(int bulls) {
        return bulls == 4;
    }

    private int getNumberZeroToFour(Animal bullsOrCows) {
        messageSender.sendMessage("Enter number of " + bullsOrCows.getReadableName());
        int count = getIntFromConsole();
        if (count < 0 || count > 4) {
            messageSender.sendMessage("You entered wrong number, it should be from 0 to 4 inclusive");
            return getNumberZeroToFour(bullsOrCows);
        } else {
            return count;
        }
    }

    private int getIntFromConsole() {
        Scanner in = new Scanner(System.in);
        String suggestion = in.nextLine();
        int result;
        try {
            result = Integer.parseInt(suggestion);
            return result;
        } catch (Exception exception) {
            messageSender.sendMessage("You entered wrong value. It should be a number");
            return getIntFromConsole();
        }
    }
}
