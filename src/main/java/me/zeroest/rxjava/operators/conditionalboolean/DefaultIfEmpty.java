package me.zeroest.rxjava.operators.conditionalboolean;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class DefaultIfEmpty {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
            .filter(num -> num > 10)
            .defaultIfEmpty(10)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 01:04:52.424 | 10
*/
    }
}
