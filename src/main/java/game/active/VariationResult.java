package game.active;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VariationResult {
    private final int bulls;
    private final int cows;
    private static final List<VariationResult> ALL_POSSIBLE_RESULTS = Arrays.asList(
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

    public VariationResult(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    public static VariationResult of(int bulls, int cows) {
        return new VariationResult(bulls, cows);
    }

    public static List<VariationResult> getAllPossibleResults() {
        return ALL_POSSIBLE_RESULTS;
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
