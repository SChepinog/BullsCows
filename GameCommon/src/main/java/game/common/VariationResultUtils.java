package game.common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VariationResultUtils {
    private static final List<VariationResult> ALL_POSSIBLE_RESULTS = generateAllPossibleResults(true);
    private static final List<VariationResult> ALL_RESULTS_FOR_MIN_CHECK = generateAllPossibleResults(false);

    public static List<VariationResult> getResultsToMinCheck() {
        return ALL_RESULTS_FOR_MIN_CHECK;
    }

    public static List<VariationResult> getAllPossibleResults() {
        return ALL_POSSIBLE_RESULTS;
    }

    private static List<VariationResult> generateAllPossibleResults(boolean includeWinResult) {
        return IntStream.range(0, GameSpec.getLength() + (includeWinResult ? 1 : 0))
            .boxed().flatMap(
                bulls ->
                    IntStream.range(0, GameSpec.getLength() + 1 - bulls)
                        .mapToObj(cows -> VariationResult.of(bulls, cows))
            ).collect(Collectors.toList());
    }
}
