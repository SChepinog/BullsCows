package active.input;

import java.util.Scanner;

import active.Animal;
import active.VariationResult;

public class ConsoleResultSupplier implements VariationResultSupplier {
    @Override
    public VariationResult getResult() {
        int bulls = getNumberZeroToFour(Animal.BULLS);
        int cows = variationIsCorrect(bulls) ? 0 : getNumberZeroToFour(Animal.COWS);
        return VariationResult.of(bulls, cows);
    }

    private boolean variationIsCorrect(int bulls) {
        return bulls == 4;
    }

    private int getNumberZeroToFour(Animal bullsOrCows) {
        System.out.println("Enter number of " + bullsOrCows.getReadableName());
        int count = getIntFromConsole();
        if (count < 0 || count > 4) {
            System.out.println("You entered wrong number, it should be from 0 to 4 inclusive");
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
            System.out.println("You entered wrong value. It should be a number");
            return getIntFromConsole();
        }
    }
}
