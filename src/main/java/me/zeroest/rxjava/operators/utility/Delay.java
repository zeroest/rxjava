package me.zeroest.rxjava.operators.utility;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Delay {
    public static void main(String[] args) {
        Logger.log(LogType.PRINT, String.format("# 실행 시작 시간: %s", TimeUtil.getCurrentTimeFormatted()));

        Observable.just(1, 3, 4, 6)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .delay(2000L, TimeUnit.MILLISECONDS)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2500L);
/*
print() | main | 23:39:34.842 | # 실행 시작 시간: 23:39:34.836
doOnNext() | main | 23:39:34.948 | 1
doOnNext() | main | 23:39:34.950 | 3
doOnNext() | main | 23:39:34.950 | 4
doOnNext() | main | 23:39:34.950 | 6
onNext() | RxComputationThreadPool-1 | 23:39:36.950 | 1
onNext() | RxComputationThreadPool-1 | 23:39:36.951 | 3
onNext() | RxComputationThreadPool-1 | 23:39:36.952 | 4
onNext() | RxComputationThreadPool-1 | 23:39:36.952 | 6
*/

        Observable.just(1, 3, 5, 7)
            .delay(item -> {
                long sleepTime = 500L;
                if (item.equals(5)) {
                    sleepTime = 3000L;
                }
                TimeUtil.sleep(sleepTime);

                return Observable.just(item); // 새로운 Observable의 통지 시점에, 원본 데이터를 통지
            })
            .subscribe(data -> Logger.log(LogType.ON_NEXT,data));
/*
onNext() | main | 23:46:27.548 | 1
onNext() | main | 23:46:28.049 | 3
onNext() | main | 23:46:31.049 | 5
onNext() | main | 23:46:31.550 | 7
*/
    }
}
