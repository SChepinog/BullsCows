import game.active.ActiveBullsCowsGameRMI;
import game.common.Game;

public class ActiveGameLauncher implements Runnable {

    @Override
    public void run() {
        Game game = new ActiveBullsCowsGameRMI();
        game.doFullGame();
    }
}
