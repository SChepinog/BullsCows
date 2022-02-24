package game.common;

public class GameSpec {
    private static final int LENGTH = 4;

    public static int getLength() {
        return LENGTH;
    }

    public static int getVariationsTotal() { //TODO for different rules
        return (int) Math.pow(10, LENGTH);
    }
}
