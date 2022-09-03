package me.zeroest.rxjava.backpressure;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

import java.util.concurrent.TimeUnit;

/*
- ERROR 전략

통지된 데이터가 버퍼의 크기를 초과하면 MissingBackpressureException 에러를 통지한다.
즉, 소비자가 생산자의 통지 속도를 따라 잡지 못할 때 발생한다.
*/
public class BackpressureError {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .onBackpressureBuffer(
                5,
                () -> Logger.log("overflow!"),
                BackpressureOverflowStrategy.ERROR
            )
            .observeOn(
                Schedulers.computation(),
                false,
                1
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
doOnNext() | RxComputationThreadPool-2 | 03:32:41.942 | 0
doOnNext() | RxComputationThreadPool-2 | 03:32:41.970 | 1
doOnNext() | RxComputationThreadPool-2 | 03:32:41.970 | 2
doOnNext() | RxComputationThreadPool-2 | 03:32:41.970 | 3
doOnNext() | RxComputationThreadPool-2 | 03:32:41.970 | 4
doOnNext() | RxComputationThreadPool-2 | 03:32:41.970 | 5
doOnNext() | RxComputationThreadPool-2 | 03:32:41.970 | 6
RxComputationThreadPool-1 | 03:32:41.970 | # 소비자 처리 대기중...
onNext() | RxComputationThreadPool-1 | 03:32:42.988 | 0
onError() | RxComputationThreadPool-1 | 03:32:42.989 | io.reactivex.exceptions.MissingBackpressureException
*/
