package active;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class VariationsUtils {

    public static List<Variation> generateAll() {
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

    static boolean hasSameResult(Variation usedVariation, @NotNull VariationResult result, Variation testVariation) {
        VariationResult testResult = countBullsAndCows(usedVariation, testVariation);
        return result.equals(testResult);
    }

    static @NotNull VariationResult countBullsAndCows(@NotNull Variation usedVariation, @NotNull Variation testVariation) {
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

    /**
     * "Stupid" variation chooser, just get first element of left variations
     *
     * @param variationsToTest list of left variations
     * @return first element
     */
    static Variation getFirstElement(@NotNull List<Variation> variationsToTest) {
        return variationsToTest.get(0);
    }

    /**
     * Variation chooser, based on max-min algorithm. Memory demanding
     *
     * @param variationsToTest list of left variations
     * @return best choice
     */
    static Variation getBestMaxMinChoice(@NotNull List<Variation> variationsToTest) {
        System.out.println("Start to make best choice for " + variationsToTest.size() + " variations");
//        long millisStart = System.currentTimeMillis();
        if (variationsToTest.size() == 10_000) { //default first move for best time first suggestion
            return Variation.of("1234");
        }
        List<VariationResult> allVariationResults = VariationResult.getAllPossibleResults();
        Variation result = variationsToTest.get(0);
        int maxMin = 0;
        for (Variation variationToTest : variationsToTest) {
//            long millisForVariation = System.currentTimeMillis();
            int minValue = countMinDiscardedVariations(variationToTest, allVariationResults, variationsToTest);
            if (minValue > maxMin) {
                maxMin = minValue;
                result = variationToTest;
            }
//            System.out.println("Processed one variation in " + (System.currentTimeMillis() - millisForVariation) + " millis");
        }
//        System.out.println("Best choice for " + variationsToTest.size() + " found in " + (System.currentTimeMillis() - millisStart) + " millis");
        return result;
    }

    private static int countMinDiscardedVariations(Variation variationToTest, List<VariationResult> allVariationResults, List<Variation> variationsToTest) {
        int minVariationCountToDiscard = 10000;
        for (Variation tempVar : variationsToTest) {
            long tempMinimum = allVariationResults.stream()
                .filter(tempVarRes -> hasSameResult(variationToTest, tempVarRes, tempVar))
                .count();
            if (minVariationCountToDiscard > tempMinimum) {
                minVariationCountToDiscard = (int) tempMinimum;
            }
        }
        return minVariationCountToDiscard;
    }
}
