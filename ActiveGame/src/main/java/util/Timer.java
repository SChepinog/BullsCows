package util;

import java.time.Duration;

public class Timer {

    private final String name;
    private Long startNanos;

    public Timer(String name) {
        this.name = name;
    }

    public Timer start() {
        startNanos = System.nanoTime();
        return this;
    }

    private boolean isStarted() {
        return startNanos != null;
    }

    public String getElapsedTimeAsString() {
        if (isStarted()) {
            Duration duration = Duration.ofNanos(System.nanoTime() - startNanos);
            return getDurationAsString(duration);
        } else {
            return String.format("Timer for %s was never started", name);
        }
    }

    public void printElapsedTime() {
        System.out.println(getElapsedTimeAsString());
    }

    private String getDurationAsString(Duration duration) {
        long minutes = duration.getSeconds() / 60;
        long seconds = duration.getSeconds() % 60;
        long millis = duration.getNano() / 1_000_000;
        return String.format("Timer for %s worked for %d minutes, %d seconds, %d millis", name, minutes, seconds, millis);
    }
}
