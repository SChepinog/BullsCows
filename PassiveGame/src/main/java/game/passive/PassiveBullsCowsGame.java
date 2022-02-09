package game.passive;

import org.jetbrains.annotations.NotNull;

import connector.FullConnector;
import connector.ResultConsumer;
import connector.VariationSupplier;
import game.common.Game;
import game.common.GameSpec;
import game.common.Variation;
import game.common.VariationResult;
import game.common.VariationsUtils;

public class PassiveBullsCowsGame implements Game {
    private int attempts = 0;
    private boolean isFinished = false;
    private final Variation secret;
    private final VariationSupplier variationSupplier;
    private final ResultConsumer resultConsumer;

    public PassiveBullsCowsGame(FullConnector connector) {
        this(connector, connector);
    }

    public PassiveBullsCowsGame(FullConnector connector, Variation secret) {
        this(connector, connector, secret);
    }

    public PassiveBullsCowsGame(VariationSupplier variationSupplier, ResultConsumer resultConsumer) {
        this(variationSupplier, resultConsumer, SecretGenerator.create());
    }

    public PassiveBullsCowsGame(VariationSupplier variationSupplier, ResultConsumer resultConsumer, Variation secret) {
        this.variationSupplier = variationSupplier;
        this.resultConsumer = resultConsumer;
        this.secret = secret;
    }

    @Override
    public void begin() {
        resultConsumer.sendMessage("I am ready!");
        resultConsumer.sendMessage(String.format("My secret is %s", secret.getValue()));
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void doIteration() {
        Variation variation = variationSupplier.getVariation();
        attempts++;
        VariationResult result = testVariation(variation);
        resultConsumer.sendResult(result);
        if (result.getBulls() == GameSpec.LENGTH) {
            this.isFinished = true;
        }
    }

    @NotNull
    private VariationResult testVariation(Variation variation) {
        return VariationsUtils.countBullsAndCows(variation, secret);
    }

    @Override
    public void doCongratulations() {
        resultConsumer.sendMessage(String.format("Hooray! It took %d attempts", attempts));
    }
}
