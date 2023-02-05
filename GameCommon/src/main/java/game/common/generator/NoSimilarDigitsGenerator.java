package game.common.generator;

import game.common.GameSpec;
import game.common.Variation;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoSimilarDigitsGenerator implements VariationsGenerator {
    @Override
    public List<Variation> generateAllVariations() {
        return generateAllVariations(GameSpec.getLength());
    }

    @Override
    public List<Variation> generateAllVariations(int length) {
        List<Variation> result = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        iterate(used, length, result);
        return result;
    }

    private void iterate(Set<Integer> used, int length, List<Variation> result) {
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

            used.remove(temp);
        }
    }
}
