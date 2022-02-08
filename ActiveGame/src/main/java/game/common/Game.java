package game.common;

public interface Game {
    void begin();

    boolean isFinished();

    void doIteration();

    void doCongratulations();
}
