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
        return generateAllVariations(GameSpec.getLength());
    }

    @SuppressWarnings("SameParameterValue")
    @Override
    public List<Variation> generateAllVariations(int length) {
        return generateAllVariationsFrom(length, 0);
    }

    @SuppressWarnings("SameParameterValue")
    public List<Variation> generateAllVariationsFrom(int length, int startInclusive) {
        return IntStream.range(startInclusive, getVariationsTotal(length))
            .mapToObj(String::valueOf)
            .map(s -> StringUtils.leftPad(s, length, "0"))
            .map(Variation::of)
            .collect(Collectors.toList());
    }

    private int getVariationsTotal(int length) {
        return (int) Math.pow(10, length);
    }
}
