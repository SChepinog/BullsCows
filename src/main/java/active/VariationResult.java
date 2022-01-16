package active;

import java.util.Arrays;
import java.util.List;
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

    public static List<VariationResult> getAllPossibleResults() {
        return Arrays.asList(
            VariationResult.of(0, 0),
            VariationResult.of(0, 1),
            VariationResult.of(0, 2),
            VariationResult.of(0, 3),
            VariationResult.of(0, 4),
            VariationResult.of(1, 0),
            VariationResult.of(1, 1),
            VariationResult.of(1, 2),
            VariationResult.of(1, 3),
            VariationResult.of(2, 0),
            VariationResult.of(2, 1),
            VariationResult.of(2, 2),
            VariationResult.of(3, 0),
            VariationResult.of(4, 0)
        );
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

    @Override
    public String toString() {
        return "VariationResult{" +
            "bulls=" + bulls +
            ", cows=" + cows +
            '}';
    }
}
