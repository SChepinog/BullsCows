package active;

import java.util.HashMap;
import java.util.Map;

public class ActiveGame {

    private final Map<Variation, VariationResult> moves = new HashMap<>();

    public ActiveGame add(Variation variation, VariationResult variationResult) {
        moves.put(variation, variationResult);
        return this;
    }

    public Map<Variation, VariationResult> getMoves() {
        return moves;
    }
}
