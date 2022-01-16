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
            Variation usedVariation = activeGameEntryPoint.getVariationToSuggest();
            isSuccessful = activeGameEntryPoint.doIteration(usedVariation, resultSupplier::getResult);
        }
        System.out.println("Hooray! It took " + attempts + " attempts!");

    }

    private Variation getVariationToSuggest() {
        return VariationsUtils.getBestMaxMinChoice(leftVariations);
//        return VariationsUtils.getFirstElement(leftVariations);
    }

    private boolean doIteration(Variation usedVariation, Supplier<VariationResult> resultSupplier) {
        logIterationInput(usedVariation);
        suggestVariation(usedVariation);
        VariationResult variationResult = resultSupplier.get();
        if (gameIsFinished(variationResult)) {
            return true;
        } else {
            updateVariations(usedVariation, variationResult);
            return false;
        }
    }

    private boolean gameIsFinished(VariationResult variationResult) {
        return variationResult.getBulls() == 4;
    }

    private void updateVariations(Variation usedVariation, VariationResult variationResult) {
        leftVariations = VariationsUtils.filterVariations(usedVariation, variationResult, leftVariations);
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
