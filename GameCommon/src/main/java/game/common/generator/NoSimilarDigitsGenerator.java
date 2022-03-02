package game.common.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import game.common.GameSpec;
import game.common.Variation;

public class NoSimilarDigitsGenerator implements VariationsGenerator {
    @Override
    public List<Variation> generateAllVariations() {
        return generateAllVariations(GameSpec.getLength());
    }

    @Override
    public List<Variation> generateAllVariations(int length) {
        List<Variation> result = new ArrayList<>();
        List<Integer> used = new ArrayList<>();
        iterate(used, length, result);
        return result;
    }

    private void iterate(List<Integer> used, int length, List<Variation> result) {
        for (int temp = 0; temp < 10; temp++) {
            if (used.contains(temp)) {
                continue;
            } else {
                used.add(temp);
            }

            if (used.size() == length) {
                result.add(Variation.of(StringUtils.join(used, "")));
            } else {
                iterate(used, length, result);
            }

            used.remove((Integer) temp);
        }
    }
}
