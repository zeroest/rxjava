package me.zeroest.rxjava.operators.utility;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * delay()는 소비자가 구독 시, 생성 및 통지는 즉시 하지만 소비자에게 전달하는 시간을 지연시킨다.
 * delaySubscription()은 데이터 생성 및 통지 자체를 지연시킨다.
 * */
public class DelaySubscription {
    public static void main(String[] args) {
        Logger.log(LogType.PRINT, String.format("# 실행 시작 시간: %s", TimeUtil.getCurrentTimeFormatted()));

        Observable.just(1, 3, 4, 6)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .delaySubscription(2000L, TimeUnit.MILLISECONDS)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2500L);
/*
print() | main | 23:54:14.491 | # 실행 시작 시간: 23:54:14.480
doOnNext() | RxComputationThreadPool-1 | 23:54:16.604 | 1
onNext() | RxComputationThreadPool-1 | 23:54:16.604 | 1
doOnNext() | RxComputationThreadPool-1 | 23:54:16.605 | 3
onNext() | RxComputationThreadPool-1 | 23:54:16.605 | 3
doOnNext() | RxComputationThreadPool-1 | 23:54:16.605 | 4
onNext() | RxComputationThreadPool-1 | 23:54:16.606 | 4
doOnNext() | RxComputationThreadPool-1 | 23:54:16.606 | 6
onNext() | RxComputationThreadPool-1 | 23:54:16.606 | 6
*/
    }
}
