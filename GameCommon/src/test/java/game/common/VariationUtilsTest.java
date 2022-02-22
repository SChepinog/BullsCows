package game.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VariationUtilsTest {

    @Test
    public void countBullsTest() {
        Assertions.assertEquals(2, VariationsUtils.countBulls(Variation.of("1111"), Variation.of("3119")));
        Assertions.assertEquals(4, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("1234")));
        Assertions.assertEquals(3, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("1233")));
        Assertions.assertEquals(2, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("3233")));
        Assertions.assertEquals(1, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("3243")));
        Assertions.assertEquals(0, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("4321")));
    }

    @Test
    public void countCowsTest() {
        Assertions.assertEquals(2, VariationsUtils.countCows(Variation.of("1111"), Variation.of("3119")));
        Assertions.assertEquals(0, VariationsUtils.countCows(Variation.of("1234"), Variation.of("1234")));
        Assertions.assertEquals(1, VariationsUtils.countCows(Variation.of("1233"), Variation.of("1234")));
        Assertions.assertEquals(2, VariationsUtils.countCows(Variation.of("3233"), Variation.of("1234")));
        Assertions.assertEquals(3, VariationsUtils.countCows(Variation.of("3243"), Variation.of("1234")));
        Assertions.assertEquals(4, VariationsUtils.countCows(Variation.of("4321"), Variation.of("1234")));
    }

    @Test
    public void countBullsAndCowsTest() {
        Assertions.assertEquals(VariationResult.of(0, 0), VariationsUtils.countBullsAndCows(Variation.of("1112"), Variation.of("3344")));
        Assertions.assertEquals(VariationResult.of(0, 1), VariationsUtils.countBullsAndCows(Variation.of("1112"), Variation.of("2344")));
        Assertions.assertEquals(VariationResult.of(0, 2), VariationsUtils.countBullsAndCows(Variation.of("1122"), Variation.of("2344")));
        Assertions.assertEquals(VariationResult.of(0, 3), VariationsUtils.countBullsAndCows(Variation.of("1322"), Variation.of("2216")));
        Assertions.assertEquals(VariationResult.of(0, 4), VariationsUtils.countBullsAndCows(Variation.of("1113"), Variation.of("3441")));
        Assertions.assertEquals(VariationResult.of(1, 0), VariationsUtils.countBullsAndCows(Variation.of("1112"), Variation.of("5432")));
        Assertions.assertEquals(VariationResult.of(1, 1), VariationsUtils.countBullsAndCows(Variation.of("1312"), Variation.of("5432")));
        Assertions.assertEquals(VariationResult.of(1, 2), VariationsUtils.countBullsAndCows(Variation.of("1312"), Variation.of("5182")));
        Assertions.assertEquals(VariationResult.of(1, 3), VariationsUtils.countBullsAndCows(Variation.of("1234"), Variation.of("1423")));
        Assertions.assertEquals(VariationResult.of(2, 0), VariationsUtils.countBullsAndCows(Variation.of("5112"), Variation.of("3119")));
        Assertions.assertEquals(VariationResult.of(2, 1), VariationsUtils.countBullsAndCows(Variation.of("1112"), Variation.of("3119")));
        Assertions.assertEquals(VariationResult.of(2, 2), VariationsUtils.countBullsAndCows(Variation.of("1111"), Variation.of("3119")));
        Assertions.assertEquals(VariationResult.of(3, 0), VariationsUtils.countBullsAndCows(Variation.of("1118"), Variation.of("1119")));
        Assertions.assertEquals(VariationResult.of(3, 1), VariationsUtils.countBullsAndCows(Variation.of("1118"), Variation.of("5118")));
        Assertions.assertEquals(VariationResult.of(4, 0), VariationsUtils.countBullsAndCows(Variation.of("1118"), Variation.of("1118")));
    }

    @Test
    public void filterVariation4BullsTest() {
        Variation testVariation = Variation.of("1234");
        VariationResult variationResult = VariationResult.of(4, 0);

        List<Variation> result = VariationsUtils.filterVariations(
            testVariation,
            variationResult,
            Collections.singletonList(testVariation));
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(testVariation, result.get(0));

        result = VariationsUtils.filterVariations(
            testVariation,
            variationResult,
            Arrays.asList(testVariation, Variation.of("2345")));
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(testVariation, result.get(0));
    }

    @Test
    public void filterVariation3BullsTest() {
        Variation testVariation = Variation.of("1234");
        VariationResult variationResult = VariationResult.of(3, 0);

        List<Variation> result = VariationsUtils.filterVariations(
            testVariation,
            variationResult,
            Collections.singletonList(testVariation));
        Assertions.assertEquals(0, result.size());

        Variation okVariation = Variation.of("1238");
        result = VariationsUtils.filterVariations(
            testVariation,
            variationResult,
            Arrays.asList(testVariation, Variation.of("2345"), okVariation));
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(okVariation, result.get(0));
    }

    @Test
    public void generateDifferentVariations() {
        List<Variation> variations = VariationsUtils.generateAllVariationsDiff();
        Assertions.assertEquals(5040, variations.size());
        Assertions.assertEquals(Variation.of("0123"), variations.get(0));
        Assertions.assertEquals(Variation.of("9876"), variations.get(5039));
    }
}
