package game.passive;

import org.apache.commons.lang3.RandomStringUtils;

import game.common.GameSpec;
import game.common.Variation;

public class SecretGenerator {

    public static Variation create() {
        return Variation.of(RandomStringUtils.randomNumeric(GameSpec.getLength()));
    }
}
