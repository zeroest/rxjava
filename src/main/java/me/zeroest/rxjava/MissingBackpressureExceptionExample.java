package me.zeroest.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.Logger;

import java.util.concurrent.TimeUnit;

public class MissingBackpressureExceptionExample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.don(data))
            .observeOn(Schedulers.computation())
            .subscribe(
                data -> {
                    Logger.print("# 소비자 처리 대기중...");
                    Thread.sleep(1000L);
                    Logger.on("data = " + data);
                },
                error -> Logger.oe(error),
                () -> Logger.oc()
            );

        Thread.sleep(2000L);
    }
}
