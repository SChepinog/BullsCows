package game.active;

public enum Animal {
    BULLS("bulls"),
    COWS("cows")
    ;

    private final String readableName;

    Animal(String readableName) {
        this.readableName = readableName;
    }

    public String getReadableName() {
        return readableName;
    }
}
