package me.zeroest.rxjava.single_maybe_completable.subscribeon_observeon;

public class MyUtil {
    static void printData(String message) {
        System.out.println(""+Thread.currentThread().getName()+" | "+message+" | ");
    }

    static void printData(String message, Object obj) {
        System.out.println(""+Thread.currentThread().getName()+" | "+message+" | " +obj.toString());
    }
}
