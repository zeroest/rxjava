package me.zeroest.rxjava.operators.filtering;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Take {
    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d")
            .take(2)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        Observable.interval(1000L, TimeUnit.MILLISECONDS)
            .take(3500L, TimeUnit.MILLISECONDS)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(5000L);
    }
}
