package me.zeroest.rxjava.operators.utility;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Timeout {
    public static void main(String[] args) {
        Observable.range(1, 5)
            .map(num -> {
                long time = 1000L;
                if (num == 4) {
                    time = 1500L;
                }
                TimeUtil.sleep(time);
                return num;
            })
            .timeout(1200L, TimeUnit.MILLISECONDS)
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        TimeUtil.sleep(3000L);
/*
onNext() | main | 00:01:21.705 | 1
onNext() | main | 00:01:22.861 | 2
onNext() | main | 00:01:23.862 | 3
onERROR() | RxComputationThreadPool-1 | 00:01:25.066 | java.util.concurrent.TimeoutException: The source did not signal an event for 1200 milliseconds and has been terminated.
*/
    }
}
