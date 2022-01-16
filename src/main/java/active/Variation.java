package active;

public class Variation {

    private String value;
    private boolean isOk = true;

    private Variation(String value) {
        this.value = value;
    }

    public static Variation of(String value) {
        return new Variation(value);
    }

    public String getValue() {
        return value;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
