package game.active;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import game.common.GameSpec;
import game.common.Variation;
import game.common.VariationsUtils;
import util.Timer;

public class VariationChooser {

    /**
     * "Stupid" variation chooser, just get first element of left variations
     *
     * @param variationsToTest list of left variations
     * @return first element
     */
    public static Variation getFirstElement(@NotNull List<Variation> variationsToTest) {
        return variationsToTest.iterator().next();
    }

    /**
     * Variation chooser, based on max-min algorithm. Memory demanding
     *
     * @param variationsToTest list of left variations
     * @return best choice
     */
    public static Variation getBestMaxMinChoice(@NotNull List<Variation> variationsToTest) {
        if (isFirstMove(variationsToTest)) {
            Variation defaultVariation = getDefaultFirstMove();
            if (defaultVariation != null) {
                return defaultVariation;
            }
        }
        Timer allVariationsTimer = new Timer("bestMaxMinChoice for " + variationsToTest.size() + " variations");
        if (variationsToTest.size() == 1) {
            return variationsToTest.iterator().next();
        }
        allVariationsTimer.start();
        Variation result = variationsToTest.iterator().next();
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

    private static Variation getDefaultFirstMove() {
        switch (GameSpec.LENGTH) {
            case 2:
                return Variation.of("01");
            case 3:
                return Variation.of("012");
            case 4:
                return Variation.of("0012");
//            case 5:
//                return Variation.of("00012");
            default:
                return null;
        }
    }

    private static boolean isFirstMove(List<Variation> variationsToTest) {
        return variationsToTest.size() == (int) Math.pow(10, GameSpec.LENGTH);
    }

    private static int countMinDiscardedVariations(Variation variationToTest, List<Variation> variationsToTest) {
        return VariationsUtils.getAllPossibleResults().parallelStream()
            .mapToInt(result -> (int) variationsToTest.parallelStream()
                .filter(v -> !VariationsUtils.hasSameResult(variationToTest, result, v))
                .count())
            .min().orElse(0);
    }
}
