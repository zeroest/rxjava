package me.zeroest.rxjava.operators.filtering;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Skip {
    public static void main(String[] args) {
        /**
         * 유형1
         * */
        Observable.range(1, 15)
            .skip(3)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        /**
         * 유형2
         * */
        Observable.interval(300L, TimeUnit.MILLISECONDS)
            .skip(1000L, TimeUnit.MILLISECONDS)
//            .take(2000L, TimeUnit.MILLISECONDS)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
