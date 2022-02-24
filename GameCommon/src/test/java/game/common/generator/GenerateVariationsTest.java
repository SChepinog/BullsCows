package game.common.generator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import game.common.GameSpec;
import game.common.Variation;

public class GenerateVariationsTest {

    private VariationsGenerator generator;

    @Test
    public void generateDifferentVariations() {
        generator = new NoSimilarDigitsGenerator(); //TODO for different game length
        List<Variation> variations = generator.generateAllVariations();
        Assertions.assertEquals(5040, variations.size());
        Assertions.assertEquals(Variation.of("0123"), variations.get(0));
        Assertions.assertEquals(Variation.of("9876"), variations.get(5039));
    }

    @Test
    public void generateAllPossibleVariations() {
        generator = new AllPossibleGenerator();
        List<Variation> variations = generator.generateAllVariations();
        Assertions.assertEquals(GameSpec.getVariationsTotal(), variations.size());
        Assertions.assertEquals(Variation.of(StringUtils.leftPad("", GameSpec.getLength(), "0")), variations.get(0));
        Assertions.assertEquals(Variation.of(StringUtils.leftPad("", GameSpec.getLength(), "9")), variations.get(GameSpec.getVariationsTotal() - 1));
    }
}
