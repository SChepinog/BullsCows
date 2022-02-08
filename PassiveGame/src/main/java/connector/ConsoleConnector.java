package connector;

import java.util.Scanner;

import game.common.GameSpec;
import game.common.Variation;
import game.common.VariationResult;
import game.passive.SuggestionValidator;

public class ConsoleConnector implements FullConnector {
    @Override
    public void sendResult(VariationResult result) {
        System.out.printf("There %d bulls and %d cows%n", result.getBulls(), result.getCows());
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Variation getVariation() {
        String result = getStringFromConsole();
        while (!SuggestionValidator.isValid(result)) {
            System.out.printf("You entered incorrect variation. It should be consisted of %d digits%nTry again%n", GameSpec.LENGTH);
            result = getStringFromConsole();
        }
        return Variation.of(result);
    }

    private String getStringFromConsole() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
