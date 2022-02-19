import game.common.Game;
import game.common.Variation;
import game.passive.PassiveBullsCowsGameRMI;
import game.passive.SecretGenerator;

public class PassiveGameLauncher implements Runnable {

    private Variation variation;

    public PassiveGameLauncher() {
        this(SecretGenerator.create());
    }

    public PassiveGameLauncher(Variation variation) {
        this.variation = variation;
    }

    @Override
    public void run() {
        Game passiveGame = new PassiveBullsCowsGameRMI(variation);
        passiveGame.doFullGame();
    }
}
