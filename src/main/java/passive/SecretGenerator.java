package passive;

import org.apache.commons.lang3.RandomStringUtils;

import common.GameSpec;

public class SecretGenerator {

    public static String create() {
        return RandomStringUtils.randomNumeric(GameSpec.LENGTH);
    }
}
