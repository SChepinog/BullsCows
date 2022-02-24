package game.common;

import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

public class VariationsUtils {

    public static List<Variation> filterVariations(Variation usedVariation, VariationResult result, @NotNull List<Variation> leftVariations) {
        return leftVariations.stream()
            .filter(v -> hasSameResult(usedVariation, result, v))
            .collect(Collectors.toList());
    }

    public static boolean hasSameResult(Variation usedVariation, @NotNull VariationResult result, Variation testVariation) {
        VariationResult testResult = countBullsAndCows(usedVariation, testVariation);
        return result.equals(testResult);
    }

    public static @NotNull VariationResult countBullsAndCows(@NotNull Variation usedVariation, @NotNull Variation testVariation) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < usedVariation.getValue().length(); i++) {
            if (testVariation.getValue().contains(usedVariation.getValue().substring(i, i + 1))) {
                if (testVariation.getValue().charAt(i) == usedVariation.getValue().charAt(i)) {
                    bulls++;
                } else {
                    cows++;
                }
            }
        }
        return VariationResult.of(bulls, cows);
    }
}
