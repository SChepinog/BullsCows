package game.active;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import util.Timer;

public class VariationChooser {

    /**
     * "Stupid" variation chooser, just get first element of left variations
     *
     * @param variationsToTest list of left variations
     * @return first element
     */
    public static Variation getFirstElement(@NotNull List<Variation> variationsToTest) {
        return variationsToTest.get(0);
    }

    /**
     * Variation chooser, based on max-min algorithm. Memory demanding
     *
     * @param variationsToTest list of left variations
     * @return best choice
     */
    public static Variation getBestMaxMinChoice(@NotNull List<Variation> variationsToTest) {
        Timer allVariationsTimer = new Timer("bestMaxMinChoice for " + variationsToTest.size() + " variations");
        if (variationsToTest.size() == 10_000) { //default first move for best time first suggestion
            return Variation.of("0012");
        }
        if (variationsToTest.size() == 1) {
            return variationsToTest.get(0);
        }
        allVariationsTimer.start();
        Variation result = variationsToTest.get(0);
        int maxMin = 0;
        for (Variation variationToTest : variationsToTest) {
            int minValue = countMinDiscardedVariations(variationToTest, variationsToTest);
            if (minValue > maxMin) {
                maxMin = minValue;
                result = variationToTest;
            }
        }
        System.out.println(allVariationsTimer.stopAndGetElapsedTimeAsString());
        return result;
    }

    private static int countMinDiscardedVariations(Variation variationToTest, List<Variation> variationsToTest) {
        return VariationResult.getAllPossibleResults().parallelStream()
            .mapToInt(result -> (int) variationsToTest.parallelStream()
                .filter(v -> !VariationsUtils.hasSameResult(variationToTest, result, v))
                .count())
            .min().orElse(0);
    }
}
