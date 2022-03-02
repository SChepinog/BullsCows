package game.common.generator;

import java.util.List;

import game.common.Variation;

public interface VariationsGenerator {

    List<Variation> generateAllVariations();

    List<Variation> generateAllVariations(int length);
}
