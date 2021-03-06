package connector.active;

import java.util.Scanner;

import game.active.Animal;
import game.common.Variation;
import game.common.VariationResult;
import game.common.VariationResultUtils;

public class ConsoleConnector implements FullConnector {

    @Override
    public VariationResult getResult() {
        int bulls = getNumberZeroToFour(Animal.BULLS);
        int cows = variationIsCorrect(bulls) ? 0 : getNumberZeroToFour(Animal.COWS);
        VariationResult inputResult = VariationResult.of(bulls, cows);
        if (VariationResultUtils.getAllPossibleResults().contains(inputResult)) {
            return inputResult;
        } else {
            sendMessage("You entered not possible combination of bulls and cows.\nPlease, check it and enter again");
            return getResult();
        }
    }

    private boolean variationIsCorrect(int bulls) {
        return bulls == 4;
    }

    private int getNumberZeroToFour(Animal bullsOrCows) {
        sendMessage("Enter number of " + bullsOrCows.getReadableName());
        int count = getIntFromConsole();
        if (count < 0 || count > 4) {
            sendMessage("You entered wrong number, it should be from 0 to 4 inclusive");
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
            sendMessage("You entered wrong value. It should be a number");
            return getIntFromConsole();
        }
    }

    @Override
    public void sendMessage(String messageToSend) {
        System.out.println(messageToSend);
    }

    @Override
    public void sendVariation(Variation variation) {
        sendMessage("Is it " + variation.getValue() + " ?");
    }
}
