package game.passive;

public class Main {

    public static void main(String[] args) {
        PassiveBullsCowsGame passiveGame = new PassiveBullsCowsGame();
        passiveGame.begin();
        while (!passiveGame.isFinished()) {
            passiveGame.doIteration();
        }
        passiveGame.doCongratulations();
    }
}
