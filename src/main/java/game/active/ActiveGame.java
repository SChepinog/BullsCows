package game.active;

public interface ActiveGame {
    void begin();

    boolean isFinished();

    void doIteration();

    void doCongratulations();
}
