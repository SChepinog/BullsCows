package input;

import java.util.Scanner;

import game.common.GameSpec;
import game.common.Variation;
import game.passive.SuggestionValidator;

public class ConsoleVariationSupplier implements VariationSupplier {
    @Override
    public Variation getVariation() {
        String result = getStringFromConsole();
        while (!SuggestionValidator.isValid(result)) {
            System.out.printf("You entered incorrect variation. It should be consisted of %d digits%nTry again", GameSpec.LENGTH);
            result = getStringFromConsole();
        }
        return Variation.of(result);
    }

    private String getStringFromConsole() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
