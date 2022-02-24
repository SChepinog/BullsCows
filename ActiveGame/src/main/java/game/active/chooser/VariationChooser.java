package game.active.chooser;

import java.util.List;

import game.common.Variation;

public interface VariationChooser {

    Variation chooseVariation(List<Variation> variations);
}
