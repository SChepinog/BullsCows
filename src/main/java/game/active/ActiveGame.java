package game.active;

import java.util.List;
import java.util.function.Supplier;

import input.VariationResultSupplier;
import output.MessageSender;

public class ActiveGame {
    private List<Variation> leftVariations;
    private int attempts;
    private boolean isFinished = false;
    private final VariationResultSupplier resultSupplier;
    private final MessageSender messageSender;
    private Supplier<Variation> variationSupplier = () -> VariationChooser.getBestMaxMinChoice(leftVariations);

    public ActiveGame(VariationResultSupplier resultSupplier, MessageSender messageSender) {
        this.resultSupplier = resultSupplier;
        this.messageSender = messageSender;
        this.leftVariations = VariationsUtils.generateAllVariations();
        this.attempts = 0;
    }

    public void setVariationSupplier(Supplier<Variation> variationSupplier) {
        this.variationSupplier = variationSupplier;
    }

    public void doIteration() {
        Variation usedVariation = variationSupplier.get();
        logIterationInput();
        increaseAttempts();
        suggestVariation(usedVariation);
        VariationResult variationResult = resultSupplier.getResult();
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
        messageSender.sendMessage(attempts + " attempt. ");
        messageSender.sendMessage("Hmm, Is it '" + testVariation.getValue() + "'?");
    }

    private void logIterationInput() {
        System.out.println("-------iteration started--------");
        System.out.println("Left variations: " + leftVariations.size());
    }

    public void doCongratulations() {
        messageSender.sendMessage("Hooray! It took " + attempts + " attempts!");
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void begin() {
        messageSender.sendMessage("OK, let's go!");
    }
}
