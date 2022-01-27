package active;

import active.input.ConsoleResultSupplier;
import active.input.VariationResultSupplier;

public class Main {

    public static void main(String[] args) {
        ActiveGame activeGame = new ActiveGame();
        VariationResultSupplier resultSupplier = new ConsoleResultSupplier();

        while (!activeGame.isFinished()) {
            activeGame.doIteration(activeGame::getBestMaxMinVariation, resultSupplier);
        }
        activeGame.doCongratulations();
    }
}
