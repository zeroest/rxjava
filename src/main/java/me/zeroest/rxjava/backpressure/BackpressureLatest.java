package me.zeroest.rxjava.backpressure;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/*
- LATEST 전략

버퍼에 데이터가 모두 채워진 상태가 되면 버퍼가 비워지리 때까지 통지된 데이터는 버퍼 밖에서 대기하며
버퍼가 비워지는 시점에 가장 나중에(최근에) 통지된 데이터부터 버퍼에 담는다.
*/
public class BackpressureLatest {
    public static void main(String[] args) {
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .onBackpressureLatest()
            .observeOn(
                Schedulers.computation(),
                false,
                1
            )
            .subscribe(
                data -> {
                    TimeUtil.sleep(1000L);
                    Logger.log(LogType.ON_NEXT, data);
                },
                error -> Logger.log(LogType.ON_ERROR, error)
            );

        TimeUtil.sleep(5500L);
    }
}

/*
doOnNext() | RxComputationThreadPool-2 | 14:24:41.934 | 0
doOnNext() | RxComputationThreadPool-2 | 14:24:42.207 | 1
doOnNext() | RxComputationThreadPool-2 | 14:24:42.507 | 2
doOnNext() | RxComputationThreadPool-2 | 14:24:42.807 | 3
onNext() | RxComputationThreadPool-1 | 14:24:43.000 | 0
doOnNext() | RxComputationThreadPool-2 | 14:24:43.107 | 4
doOnNext() | RxComputationThreadPool-2 | 14:24:43.407 | 5
doOnNext() | RxComputationThreadPool-2 | 14:24:43.707 | 6
onNext() | RxComputationThreadPool-1 | 14:24:44.000 | 3
doOnNext() | RxComputationThreadPool-2 | 14:24:44.007 | 7
doOnNext() | RxComputationThreadPool-2 | 14:24:44.307 | 8
doOnNext() | RxComputationThreadPool-2 | 14:24:44.607 | 9
doOnNext() | RxComputationThreadPool-2 | 14:24:44.907 | 10
onNext() | RxComputationThreadPool-1 | 14:24:45.001 | 6
doOnNext() | RxComputationThreadPool-2 | 14:24:45.207 | 11
doOnNext() | RxComputationThreadPool-2 | 14:24:45.507 | 12
doOnNext() | RxComputationThreadPool-2 | 14:24:45.807 | 13
onNext() | RxComputationThreadPool-1 | 14:24:46.002 | 10
doOnNext() | RxComputationThreadPool-2 | 14:24:46.107 | 14
doOnNext() | RxComputationThreadPool-2 | 14:24:46.407 | 15
doOnNext() | RxComputationThreadPool-2 | 14:24:46.707 | 16
onNext() | RxComputationThreadPool-1 | 14:24:47.002 | 13
doOnNext() | RxComputationThreadPool-2 | 14:24:47.007 | 17
*/
