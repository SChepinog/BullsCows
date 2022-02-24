package game.active.chooser;

import java.util.List;

import game.common.Variation;

public class GetFirstChooser implements VariationChooser {

    /**
     * "Stupid" variation chooser, just get first element of left variations
     *
     * @param variations list of left variations
     * @return first element
     */
    @Override
    public Variation chooseVariation(List<Variation> variations) {
        return variations.iterator().next();
    }
}
