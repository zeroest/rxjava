package me.zeroest.rxjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void don(Object data) {
        String now = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String threadName = Thread.currentThread().getName();
        System.out.println("doOnNext() | " + threadName + " | " + now + " | " + data);
    }

    public static void on(Object data) {
        String now = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String threadName = Thread.currentThread().getName();
        System.out.println("onNext() | " + threadName + " | " + now + " | " + data);
    }

    public static void print(Object data) {
        String now = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " | " + now + " | " + data);
    }

    public static void oe(Throwable exception) {
        String now = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String threadName = Thread.currentThread().getName();
        System.out.println("onError() | " + threadName + " | " + now + " | " + exception);
    }

    public static void oc() {
        String now = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String threadName = Thread.currentThread().getName();
        System.out.println("onComplete() | " + threadName + " | " + now);
    }
}
