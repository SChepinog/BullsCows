package game.active.chooser;

import java.util.List;

import game.common.GameSpec;
import game.common.Variation;
import game.common.VariationResultUtils;
import game.common.VariationsUtils;
import util.Timer;

public class MaxMinChooser implements VariationChooser {

    /**
     * Variation chooser, based on max-min algorithm. Memory demanding
     *
     * @param variations list of left variations
     * @return best choice
     */
    @Override
    public Variation chooseVariation(List<Variation> variations) {
        if (isFirstMove(variations)) {
            Variation defaultVariation = getDefaultFirstMove();
            if (defaultVariation != null) {
                return defaultVariation;
            }
        }
        if (variations.size() == 1) {
            return variations.iterator().next();
        }
        Timer allVariationsTimer = new Timer("bestMaxMinChoice for " + variations.size() + " variations").start();
        Variation result = variations.iterator().next();
        int maxMin = 0;
        for (Variation variationToTest : variations) {
            int minValue = countMinDiscardedVariations(variationToTest, variations);
            if (minValue > maxMin) {
                maxMin = minValue;
                result = variationToTest;
            }
        }
        System.out.println("Active - " + allVariationsTimer.stopAndGetElapsedTimeAsString());
        return result;
    }

    private static Variation getDefaultFirstMove() {
        switch (GameSpec.getLength()) {
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
        return variationsToTest.size() == GameSpec.getVariationsTotal();
    }

    private static int countMinDiscardedVariations(Variation variationToTest, List<Variation> variationsToTest) {
        return VariationResultUtils.getResultsToMinCheck().parallelStream()
            .mapToInt(result -> (int) variationsToTest.parallelStream()
                .filter(v -> !VariationsUtils.hasSameResult(variationToTest, result, v))
                .count())
            .min().orElse(0);
    }
}
