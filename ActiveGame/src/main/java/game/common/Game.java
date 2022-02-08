package game.common;

public interface Game {
    void begin();

    boolean isFinished();

    void doIteration();

    void doCongratulations();

    default void doFullGame() {
        this.begin();
        while (!this.isFinished()) {
            this.doIteration();
        }
        this.doCongratulations();
    }
}
