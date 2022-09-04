package me.zeroest.rxjava.operators.creating;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Timer {
    public static void main(String[] args) {
        Logger.log(LogType.PRINT, "# Start");

        Observable.timer(2000, TimeUnit.MILLISECONDS)
            .map(count -> String.format("Do work! - timer data: %d", count))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}

/*
print() | main | 18:01:25.450 | # Start
onNext() | RxComputationThreadPool-1 | 18:01:27.569 | Do work! - timer data: 0
*/
