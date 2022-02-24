package game.common;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class VariationsUtils {

    private static final List<VariationResult> ALL_POSSIBLE_RESULTS = generateAllPossibleResults(true);
    private static final List<VariationResult> ALL_RESULTS_FOR_MIN_CHECK = generateAllPossibleResults(false);

    private static List<VariationResult> generateAllPossibleResults(boolean includeWinResult) {
        return IntStream.range(0, GameSpec.getLength() + (includeWinResult ? 1 : 0))
            .boxed().flatMap(
                bulls ->
                    IntStream.range(0, GameSpec.getLength() + 1 - bulls)
                        .mapToObj(cows -> VariationResult.of(bulls, cows))
            ).collect(Collectors.toList());
    }

    public static List<Variation> generateAllVariations() {
        return generateAllVariations(0);
    }

    public static List<Variation> generateAllVariations(int startInclusive) {
        return IntStream.range(startInclusive, GameSpec.getVariationsTotal())
            .mapToObj(String::valueOf)
            .map(s -> StringUtils.leftPad(s, GameSpec.getLength(), "0"))
            .map(Variation::of)
            .collect(Collectors.toList());
    }

    public static List<Variation> generateAllVariationsDiff() {
        List<Variation> result = new ArrayList<>(5040);
        Set<String> stringVariation = new LinkedHashSet<>(4);
        for (int first = 0; first < 10; first++) {
            String firstString = String.valueOf(first);
            stringVariation.add(firstString);
            for (int second = 0; second < 10; second++) {
                String secondString = String.valueOf(second);
                if (stringVariation.contains(secondString)) {
                    continue;
                } else {
                    stringVariation.add(secondString);
                }
                for (int third = 0; third < 10; third++) {
                    String thirdString = String.valueOf(third);
                    if (stringVariation.contains(thirdString)) {
                        continue;
                    } else {
                        stringVariation.add(thirdString);
                    }
                    for (int fourth = 0; fourth < 10; fourth++) {
                        String fourthString = String.valueOf(fourth);
                        if (stringVariation.contains(fourthString)) {
                            continue;
                        } else {
                            stringVariation.add(fourthString);
                        }
                        result.add(Variation.of(String.join("", stringVariation)));
                        stringVariation.remove(fourthString);
                    }
                    stringVariation.remove(thirdString);
                }
                stringVariation.remove(secondString);
            }
            stringVariation.remove(firstString);
        }
        return result;
    }

    public static List<Variation> filterVariations(Variation usedVariation, VariationResult result, @NotNull List<Variation> leftVariations) {
        return leftVariations.stream()
            .filter(v -> hasSameResult(usedVariation, result, v))
            .collect(Collectors.toList());
    }

    public static boolean hasSameResult(Variation usedVariation, @NotNull VariationResult result, Variation testVariation) {
        VariationResult testResult = countBullsAndCows(usedVariation, testVariation);
        return result.equals(testResult);
    }

    public static @NotNull VariationResult countBullsAndCows(@NotNull Variation usedVariation, @NotNull Variation testVariation) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < usedVariation.getValue().length(); i++) {
            if (testVariation.getValue().contains(usedVariation.getValue().substring(i, i + 1))) {
                if (testVariation.getValue().charAt(i) == usedVariation.getValue().charAt(i)) {
                    bulls++;
                } else {
                    cows++;
                }
            }
        }
        return VariationResult.of(bulls, cows);
    }

    public static List<VariationResult> getAllPossibleResults() {
        return ALL_POSSIBLE_RESULTS;
    }

    public static List<VariationResult> getResultsToMinCheck() {
        return ALL_RESULTS_FOR_MIN_CHECK;
    }
}
