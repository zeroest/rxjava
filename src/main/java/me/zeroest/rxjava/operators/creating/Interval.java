package me.zeroest.rxjava.operators.creating;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Interval {
    public static void main(String[] args) {
        Observable
            .interval(0L, 1000L, TimeUnit.MILLISECONDS)
            .map(num -> String.format("Observable: %s count", num))
//            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
