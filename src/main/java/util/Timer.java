package util;

import java.time.Duration;

public class Timer {

    private String name;
    private long startMillis;
    private long stopMillis;

    public Timer() {}

    public Timer(String name) {
        this.name = name;
    }

    public void start() {
        startMillis = System.currentTimeMillis();
    }

    public Timer stop() {
        stopMillis = System.currentTimeMillis();
        return this;
    }

    public String getElapsedTimeAsString() {
        Duration duration = Duration.ofMillis(stopMillis - startMillis);
        return getDurationAsString(duration);
    }

    private String getDurationAsString(Duration duration) {
        long minutes = duration.getSeconds() / 60;
        long seconds = duration.getSeconds() % 60;
        long millis = duration.getNano() / 1_000_000;
        return "Timer" + (name == null ? "" : (" for " + name)) + " worked for "
            + minutes + " minutes, "
            + seconds + " seconds, "
            + millis + " millis";
    }
}
