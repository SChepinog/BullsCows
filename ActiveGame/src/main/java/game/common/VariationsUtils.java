package game.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class VariationsUtils {

    private static final List<VariationResult> ALL_POSSIBLE_RESULTS = Arrays.asList(
        VariationResult.of(0, 0),
        VariationResult.of(0, 1),
        VariationResult.of(0, 2),
        VariationResult.of(0, 3),
        VariationResult.of(0, 4),
        VariationResult.of(1, 0),
        VariationResult.of(1, 1),
        VariationResult.of(1, 2),
        VariationResult.of(1, 3),
        VariationResult.of(2, 0),
        VariationResult.of(2, 1),
        VariationResult.of(2, 2),
        VariationResult.of(3, 0),
        VariationResult.of(3, 1),
        VariationResult.of(4, 0)
    );

    public static List<Variation> generateAllVariations() {
        return IntStream.range(0, 10_000)
            .mapToObj(String::valueOf)
            .map(s -> StringUtils.leftPad(s, 4, "0"))
            .map(Variation::of)
            .collect(Collectors.toList());
    }

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

    static int countBulls(@NotNull Variation usedVariation, @NotNull Variation testVariation) {
        int bulls = 0;
        for (int i = 0; i < usedVariation.getValue().length(); i++) {
            if (usedVariation.getValue().charAt(i) == testVariation.getValue().charAt(i)) {
                bulls++;
            }
        }
        return bulls;
    }

    static int countCows(@NotNull Variation usedVariation, @NotNull Variation testVariation) {
        int cows = 0;
        for (int i = 0; i < usedVariation.getValue().length(); i++) {
            if (testVariation.getValue().contains(usedVariation.getValue().substring(i, i + 1))
                && testVariation.getValue().charAt(i) != usedVariation.getValue().charAt(i)
            ) {
                cows++;
            }
        }
        return cows;
    }

    public static List<VariationResult> getAllPossibleResults() {
        return ALL_POSSIBLE_RESULTS;
    }
}
