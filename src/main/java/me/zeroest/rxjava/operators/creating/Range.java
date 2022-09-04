package me.zeroest.rxjava.operators.creating;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class Range {
    public static void main(String[] args) {
        Observable
            .range(0, 5)
            .subscribeOn(Schedulers.computation())
            .subscribe(num -> {
                TimeUtil.sleep(300L);
                Logger.log(LogType.ON_NEXT, num);
            });

        TimeUtil.sleep(2000L);
    }
}
