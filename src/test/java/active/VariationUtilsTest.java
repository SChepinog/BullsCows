package active;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VariationUtilsTest {

    @Test
    public void countBullsTest() {
        Assertions.assertAll(
            () -> Assertions.assertEquals(2, VariationsUtils.countBulls(Variation.of("1111"), Variation.of("3119"))),
            () -> Assertions.assertEquals(4, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("1234"))),
            () -> Assertions.assertEquals(3, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("1233"))),
            () -> Assertions.assertEquals(2, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("3233"))),
            () -> Assertions.assertEquals(1, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("3243"))),
            () -> Assertions.assertEquals(0, VariationsUtils.countBulls(Variation.of("1234"), Variation.of("4321")))
        );
    }

    @Test
    public void countCowsTest() {
        Assertions.assertAll(
            () -> Assertions.assertEquals(2, VariationsUtils.countCows(Variation.of("1111"), Variation.of("3119"))),
            () -> Assertions.assertEquals(0, VariationsUtils.countCows(Variation.of("1234"), Variation.of("1234"))),
            () -> Assertions.assertEquals(1, VariationsUtils.countCows(Variation.of("1233"), Variation.of("1234"))),
            () -> Assertions.assertEquals(2, VariationsUtils.countCows(Variation.of("3233"), Variation.of("1234"))),
            () -> Assertions.assertEquals(3, VariationsUtils.countCows(Variation.of("3243"), Variation.of("1234"))),
            () -> Assertions.assertEquals(4, VariationsUtils.countCows(Variation.of("4321"), Variation.of("1234")))
        );
    }

    @Test
    public void countBullsAndCowsTest() {
        Assertions.assertEquals(VariationResult.of(2, 2), VariationsUtils.countBullsAndCows(Variation.of("1111"), Variation.of("3119")));
        Assertions.assertEquals(VariationResult.of(2, 1), VariationsUtils.countBullsAndCows(Variation.of("1112"), Variation.of("3119")));
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
}
