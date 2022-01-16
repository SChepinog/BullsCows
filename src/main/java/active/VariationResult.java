package active;

import java.util.Objects;

public class VariationResult {
    private int bulls;
    private int cows;

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

    public VariationResult setBulls(int bulls) {
        this.bulls = bulls;
        return this;
    }

    public int getCows() {
        return cows;
    }

    public VariationResult setCows(int cows) {
        this.cows = cows;
        return this;
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
}
