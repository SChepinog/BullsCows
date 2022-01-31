package util;

import java.time.Duration;

public class Timer {

    private final String name;
    private long startMillis;

    public Timer(String name) {
        this.name = name;
    }

    public void start() {
        startMillis = System.currentTimeMillis();
    }

    public String stopAndGetElapsedTimeAsString() {
        Duration duration = Duration.ofMillis(System.currentTimeMillis() - startMillis);
        return getDurationAsString(duration);
    }

    private String getDurationAsString(Duration duration) {
        long minutes = duration.getSeconds() / 60;
        long seconds = duration.getSeconds() % 60;
        long millis = duration.getNano() / 1_000_000;
        return String.format("Timer for %s worked for %d minutes, %d seconds, %d millis", name, minutes, seconds, millis);
    }
}
