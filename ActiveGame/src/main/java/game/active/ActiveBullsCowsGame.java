package game.active;

import java.util.List;

import connector.active.FullConnector;
import connector.active.MessageSender;
import connector.active.VariationResultSupplier;
import game.active.chooser.MaxMinChooser;
import game.active.chooser.VariationChooser;
import game.common.Game;
import game.common.GameSpec;
import game.common.Variation;
import game.common.VariationResult;
import game.common.VariationsUtils;
import game.common.generator.AllPossibleGenerator;
import game.common.generator.VariationsGenerator;

public class ActiveBullsCowsGame implements Game {
    private VariationsGenerator variationsGenerator = new AllPossibleGenerator();
    private List<Variation> leftVariations;
    private int attempts;
    private boolean isFinished = false;
    private final VariationResultSupplier resultSupplier;
    private final MessageSender messageSender;
    private final VariationChooser variationChooser = new MaxMinChooser();

    public ActiveBullsCowsGame(FullConnector connector) {
        this(connector, connector);
    }

    public ActiveBullsCowsGame(VariationResultSupplier resultSupplier, MessageSender messageSender) {
        this.resultSupplier = resultSupplier;
        this.messageSender = messageSender;
        this.leftVariations = variationsGenerator.generateAllVariations();
        this.attempts = 0;
    }

    @Override
    public void doIteration() {
        Variation usedVariation = variationChooser.chooseVariation(leftVariations);
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
        return variationResult.getBulls() == GameSpec.getLength();
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
        System.out.println("Active - ------------------------------------");
        System.out.println("Active - Left variations: " + leftVariations.size());
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
