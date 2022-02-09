package game.common;

import java.io.Serializable;

public class Variation implements Serializable {

    private final String value;

    private Variation(String value) {
        this.value = value;
    }

    public static Variation of(String value) {
        return new Variation(value);
    }

    public String getValue() {
        return value;
    }
}
