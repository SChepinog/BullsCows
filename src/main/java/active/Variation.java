package active;

public class Variation {

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
