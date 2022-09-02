package me.zeroest.rxjava.backpressure;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

import java.util.concurrent.TimeUnit;

public class BackpressureMissing {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .observeOn(
                Schedulers.computation(),
                false,
                10
            )
            .subscribe(
                data -> {
                    Logger.log("# 소비자 처리 대기중...");
                    Thread.sleep(1000L);
                    Logger.log(LogType.ON_NEXT, data);
                },
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        Thread.sleep(2000L);
    }
}

/*
doOnNext() | RxComputationThreadPool-2 | 03:45:59.485 | 0
doOnNext() | RxComputationThreadPool-2 | 03:45:59.514 | 1
doOnNext() | RxComputationThreadPool-2 | 03:45:59.515 | 2
doOnNext() | RxComputationThreadPool-2 | 03:45:59.515 | 3
doOnNext() | RxComputationThreadPool-2 | 03:45:59.515 | 4
doOnNext() | RxComputationThreadPool-2 | 03:45:59.515 | 5
doOnNext() | RxComputationThreadPool-2 | 03:45:59.515 | 6
doOnNext() | RxComputationThreadPool-2 | 03:45:59.515 | 7
doOnNext() | RxComputationThreadPool-2 | 03:45:59.515 | 8
doOnNext() | RxComputationThreadPool-2 | 03:45:59.516 | 9
# 소비자 처리 대기중... | RxComputationThreadPool-1 | 03:45:59.514
onNext() | RxComputationThreadPool-1 | 03:46:00.530 | 0
onERROR() | RxComputationThreadPool-1 | 03:46:00.531 | io.reactivex.exceptions.MissingBackpressureException: Can't deliver value 10 due to lack of requests
*/
