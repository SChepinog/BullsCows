package game.passive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import game.common.Variation;

public class BicycleLogger {

    FileWriter fileWriter;
    PrintWriter printWriter;

    public BicycleLogger() {
        try {
            fileWriter = new FileWriter("BicycleLogger.csv", true);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        printWriter = new PrintWriter(fileWriter);
    }

    public void log(Variation secret, int attempts) {
        printWriter.printf("%s,%d%n", secret.getValue(), attempts);
        printWriter.close();
    }
}
