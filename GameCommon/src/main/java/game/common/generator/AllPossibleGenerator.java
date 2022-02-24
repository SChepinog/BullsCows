package game.common.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import game.common.GameSpec;
import game.common.Variation;

public class AllPossibleGenerator implements VariationsGenerator {

    @Override
    public List<Variation> generateAllVariations() {
        return generateAllVariations(0);
    }

    public List<Variation> generateAllVariations(int startInclusive) {
        return IntStream.range(startInclusive, GameSpec.getVariationsTotal())
            .mapToObj(String::valueOf)
            .map(s -> StringUtils.leftPad(s, GameSpec.getLength(), "0"))
            .map(Variation::of)
            .collect(Collectors.toList());
    }
}
