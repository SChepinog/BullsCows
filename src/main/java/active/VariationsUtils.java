package active;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class VariationsUtils {

    public static List<Variation> generateAll() {
        return IntStream.range(0, 9999)
            .mapToObj(String::valueOf)
            .map(s -> StringUtils.leftPad(s, 4, "0"))
            .map(Variation::of)
            .collect(Collectors.toList());
    }

    public static List<Variation> filterVariations(Variation usedVariation, VariationResult result, @NotNull List<Variation> leftVariations) {
        return leftVariations.parallelStream()
            .filter(v -> hasSameResult(usedVariation, result, v))
            .collect(Collectors.toList());
    }

    static boolean hasSameResult(Variation usedVariation, @NotNull VariationResult result, Variation testVariation) {
        VariationResult testResult = new VariationResult(countBulls(usedVariation, testVariation), countCows(usedVariation, testVariation));
        return result.equals(testResult);
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
}
