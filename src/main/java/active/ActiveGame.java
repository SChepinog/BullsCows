package active;

import java.util.List;
import java.util.function.Supplier;

public class ActiveGame {
    List<Variation> leftVariations;
    int attempts;
    private boolean isFinished = false;

    public ActiveGame() {
        this.leftVariations = VariationsUtils.generateAll();
        this.attempts = 0;
    }

    public Variation getBestMaxMinVariation() {
        return VariationsUtils.getBestMaxMinChoice(leftVariations);
    }

    public Variation getFirstResult() {
        return VariationsUtils.getFirstElement(leftVariations);
    }

    public void doIteration(Supplier<Variation> variationSupplier, Supplier<VariationResult> resultSupplier) {
        Variation usedVariation = variationSupplier.get();
        logIterationInput();
        increaseAttempts();
        suggestVariation(usedVariation);
        VariationResult variationResult = resultSupplier.get();
        if (variationWasCorrect(variationResult)) {
            isFinished = true;
        } else {
            updateVariations(usedVariation, variationResult);
        }
    }

    private void increaseAttempts() {
        attempts++;
    }

    private boolean variationWasCorrect(VariationResult variationResult) {
        return variationResult.getBulls() == 4;
    }

    private void updateVariations(Variation usedVariation, VariationResult variationResult) {
        leftVariations = VariationsUtils.filterVariations(usedVariation, variationResult, leftVariations);
    }

    private void suggestVariation(Variation testVariation) {
        System.out.print(attempts + " attempt. ");
        System.out.println("Hmm, Is it '" + testVariation.getValue() + "'?");
    }

    private void logIterationInput() {
        System.out.println("-------iteration started--------");
        System.out.println("Left variations: " + leftVariations.size());
    }

    public void doCongratulations() {
        System.out.println("Hooray! It took " + attempts + " attempts!");
    }

    public boolean isFinished() {
        return this.isFinished;
    }
}
