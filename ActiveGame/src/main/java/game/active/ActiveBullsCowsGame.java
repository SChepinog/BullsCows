package game.active;

import java.util.List;
import java.util.function.Supplier;

import connector.FullConnector;
import connector.MessageSender;
import connector.VariationResultSupplier;
import game.common.Game;
import game.common.Variation;
import game.common.VariationResult;
import game.common.VariationsUtils;

public class ActiveBullsCowsGame implements Game {
    private List<Variation> leftVariations;
    private int attempts;
    private boolean isFinished = false;
    private final VariationResultSupplier resultSupplier;
    private final MessageSender messageSender;
    private Supplier<Variation> variationSupplier = () -> VariationChooser.getBestMaxMinChoice(leftVariations);

    public ActiveBullsCowsGame(FullConnector connector) {
        this(connector, connector);
    }

    public ActiveBullsCowsGame(VariationResultSupplier resultSupplier, MessageSender messageSender) {
        this.resultSupplier = resultSupplier;
        this.messageSender = messageSender;
        this.leftVariations = VariationsUtils.generateAllVariations();
        this.attempts = 0;
    }

    public void setVariationSupplier(Supplier<Variation> variationSupplier) {
        this.variationSupplier = variationSupplier;
    }

    @Override
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
        messageSender.sendVariation(testVariation);
        messageSender.sendMessage(attempts + " attempt. ");
        messageSender.sendMessage("Hmm, Is it '" + testVariation.getValue() + "'?");
    }

    private void logIterationInput() {
        System.out.println("------------------------------------");
        System.out.println("Left variations: " + leftVariations.size());
    }

    @Override
    public void doCongratulations() {
        messageSender.sendMessage(String.format("Hooray! It took %d attempts!", attempts));
    }

    @Override
    public boolean isFinished() {
        return this.isFinished;
    }

    @Override
    public void begin() {
        messageSender.sendMessage("OK, let's go!");
    }
}
