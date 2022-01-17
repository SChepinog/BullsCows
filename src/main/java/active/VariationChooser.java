package active;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import util.Timer;

import static active.VariationsUtils.hasSameResult;

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
        System.out.println("Start to make best choice for " + variationsToTest.size() + " variations");
        Timer allVariations = new Timer("beestMaxMinChoice for " + variationsToTest.size() + " variations");
        if (variationsToTest.size() == 10_000) { //default first move for best time first suggestion
            return Variation.of("1234");
        }
        allVariations.start();
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
        System.out.println(allVariations.stop().getElapsedTimeAsString());
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
