package active;

import java.util.Scanner;

public class ActiveGameEntryPoint {

    public static void main(String[] args) {
        ActiveGameEntryPoint activeGameEntryPoint = new ActiveGameEntryPoint();
        int bulls = activeGameEntryPoint.getNumberZeroToFour(Animal.BULLS);
        System.out.println("Yep, it worked! Result is " + bulls);
        int cows = activeGameEntryPoint.getNumberZeroToFour(Animal.COWS);
        new VariationResult(bulls, cows);
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
