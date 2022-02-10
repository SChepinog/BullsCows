package util;

import java.time.Duration;

public class Timer {

    private final String name;
    private long startNanos;

    public Timer(String name) {
        this.name = name;
    }

    public Timer start() {
        startNanos = System.nanoTime();
        return this;
    }

    public String stopAndGetElapsedTimeAsString() {
        Duration duration = Duration.ofNanos(System.nanoTime() - startNanos);
        return getDurationAsString(duration);
    }

    private String getDurationAsString(Duration duration) {
        long minutes = duration.getSeconds() / 60;
        long seconds = duration.getSeconds() % 60;
        long millis = duration.getNano() / 1_000_000;
        return String.format("Timer for %s worked for %d minutes, %d seconds, %d millis", name, minutes, seconds, millis);
    }
}
