package game.common.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import game.common.Variation;

public class NoSimilarDigitsGenerator implements VariationsGenerator {
    @Override
    public List<Variation> generateAllVariations() { //TODO work with different GameSpec.LENGTH
        List<Variation> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < 10; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    for (int l = 0; l < 10; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        result.add(Variation.of(StringUtils.join(i, j, k, l, "")));
                    }
                }
            }
        }
        return result;
    }
}
