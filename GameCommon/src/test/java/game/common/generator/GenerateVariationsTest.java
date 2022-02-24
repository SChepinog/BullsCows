package game.common.generator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import game.common.GameSpec;
import game.common.Variation;

public class GenerateVariationsTest {

    @Test
    public void generateDifferentVariations4() {
        NoSimilarDigitsGenerator generator4 = new NoSimilarDigitsGenerator();
        List<Variation> variations = generator4.generateAllVariations(4);
        int amount = 10 * 9 * 8 * 7;
        Assertions.assertEquals(amount, variations.size());
        Assertions.assertEquals(Variation.of("0123"), variations.get(0));
        Assertions.assertEquals(Variation.of("9876"), variations.get(amount - 1));
    }

    @Test
    public void generateDifferentVariations3() {
        NoSimilarDigitsGenerator generator3 = new NoSimilarDigitsGenerator();
        List<Variation> variations = generator3.generateAllVariations(3);
        int amount = 10 * 9 * 8;
        Assertions.assertEquals(amount, variations.size());
        Assertions.assertEquals(Variation.of("012"), variations.get(0));
        Assertions.assertEquals(Variation.of("987"), variations.get(amount - 1));
    }

    @Test
    public void generateAllPossibleVariations() {
        VariationsGenerator generator = new AllPossibleGenerator();
        List<Variation> variations = generator.generateAllVariations();
        Assertions.assertEquals(GameSpec.getVariationsTotal(), variations.size());
        Assertions.assertEquals(Variation.of(StringUtils.leftPad("", GameSpec.getLength(), "0")), variations.get(0));
        Assertions.assertEquals(Variation.of(StringUtils.leftPad("", GameSpec.getLength(), "9")), variations.get(GameSpec.getVariationsTotal() - 1));
    }
}
