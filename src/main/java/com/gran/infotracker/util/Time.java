package com.gran.infotracker.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class Time {

    public static void measureDuration(String name, LocalDateTime start) {
        final Duration between = Duration.between(start, LocalDateTime.now());
        System.out.println(name + " duration: " + between.getSeconds() + "sec");
    }

    public static void sleep(Duration duration) {
        sleep(duration.toMillis());
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
