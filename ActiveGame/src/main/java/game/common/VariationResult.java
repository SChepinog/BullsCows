package game.common;

import java.util.Objects;

public class VariationResult {
    private final int bulls;
    private final int cows;

    public VariationResult(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    public static VariationResult of(int bulls, int cows) {
        return new VariationResult(bulls, cows);
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VariationResult that = (VariationResult) o;
        return bulls == that.bulls && cows == that.cows;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bulls, cows);
    }

    @Override
    public String toString() {
        return "VariationResult{" +
            "bulls=" + bulls +
            ", cows=" + cows +
            '}';
    }
}
