package active;

import java.util.List;
import java.util.function.Supplier;

import active.input.ConsoleResultSupplier;
import active.input.VariationResultSupplier;

public class ActiveGameEntryPoint {
    List<Variation> leftVariations = VariationsUtils.generateAll();
    static int attempts = 0;

    public static void main(String[] args) {
        ActiveGameEntryPoint activeGameEntryPoint = new ActiveGameEntryPoint();
        VariationResultSupplier resultSupplier = new ConsoleResultSupplier();

        boolean isSuccessful = false;
        while (!isSuccessful) {
            attempts ++;
            Variation testVariation = activeGameEntryPoint.getVariationToSuggest();
            isSuccessful = activeGameEntryPoint.doIteration(testVariation, resultSupplier::getResult);
        }
        System.out.println("Hooray! It took " + attempts + " attempts!");

    }

    private Variation getVariationToSuggest() {
        return leftVariations.get(0);
    }

    private boolean doIteration(Variation testVariation, Supplier<VariationResult> resultSupplier) {
        logIterationInput(testVariation);
        suggestVariation(testVariation);
        VariationResult variationResult = resultSupplier.get();
        if (gameIsFinished(variationResult)) {
            return true;
        } else {
            updateVariations(testVariation, variationResult);
            return false;
        }
    }

    private boolean gameIsFinished(VariationResult variationResult) {
        return variationResult.getBulls() == 4;
    }

    private void updateVariations(Variation testVariation, VariationResult variationResult) {
        leftVariations = VariationsUtils.filterVariations(testVariation, variationResult, leftVariations);
    }

    private void suggestVariation(Variation testVariation) {
        System.out.print(attempts + " attempt. ");
        System.out.println("Hmm, Is it '" + testVariation.getValue() + "'?");
    }

    private void logIterationInput(Variation testVariation) {
        System.out.println("-------iteration started--------");
        System.out.println("test variation is " + testVariation.getValue());
        System.out.println("Left variations: " + leftVariations.size());
    }
}
