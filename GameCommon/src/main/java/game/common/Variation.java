package game.common;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Variation variation = (Variation) o;

        return Objects.equals(value, variation.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }


    @Override
    public String toString() {
        return getValue();
    }
}
