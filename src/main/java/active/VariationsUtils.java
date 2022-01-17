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

    /**
     * "Stupid" variation chooser, just get first element of left variations
     *
     * @param variationsToTest list of left variations
     * @return first element
     */
    static Variation getFirstElement(List<Variation> variationsToTest) {
        return variationsToTest.get(0);
    }

    /**
     * Variation chooser, based on max-min algorithm. Memory demanding
     *
     * @param variationsToTest list of left variations
     * @return best choice
     */
    static Variation getBestMaxMinChoice(List<Variation> variationsToTest) {
        System.out.println("Start to make best choice for " + variationsToTest.size() + " variations");
        if (variationsToTest.size() == 10_000) { //default first move for best time first suggestion
            return Variation.of("1234");
        }
        List<VariationResult> allVariationResults = VariationResult.getAllPossibleResults();
        Variation result = variationsToTest.get(0);
        int maxMin = 0;
        for (Variation variationToTest : variationsToTest) {
            int minValue = countMinDiscardedVariations(variationToTest, allVariationResults, variationsToTest);
            if (minValue > maxMin) {
                maxMin = minValue;
                result = variationToTest;
            }
        }
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
