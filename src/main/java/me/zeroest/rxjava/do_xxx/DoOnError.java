package me.zeroest.rxjava.do_xxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class DoOnError {
    public static void main(String[] args) {
        Observable.just(3, 6, 9, 12, 15, 18)
            .zipWith(Observable.just(1, 2, 3, 4, 0, 6), (a, b) -> a / b)
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, String.format("# 생산자: 에러 발생 %s", error.getMessage())))
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );
    }
}

/*
onNext() | main | 01:59:19.957 | 3
onNext() | main | 01:59:19.995 | 3
onNext() | main | 01:59:19.995 | 3
onNext() | main | 01:59:19.995 | 3
doOnError() | main | 01:59:20.000 | # 생산자: 에러 발생 / by zero
onERROR() | main | 01:59:20.000 | java.lang.ArithmeticException: / by zero
*/
