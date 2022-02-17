import game.active.ActiveBullsCowsGameRMI;
import game.common.Game;
import game.common.Variation;
import game.passive.PassiveBullsCowsGameRMI;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Game passiveGame = new PassiveBullsCowsGameRMI(Variation.of("0000"));
        Game activeGame = new ActiveBullsCowsGameRMI();
        passiveGame.doFullGame();
        activeGame.doFullGame();
    }
}
