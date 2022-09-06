package me.zeroest.rxjava.operators.errorhandling;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class OnErrorResumeNext {
    public static void main(String[] args) {
        Observable
            .just(5L)
            .flatMap(num ->
                Observable
                    .interval(200L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(i -> num / i)
                .onErrorResumeNext(throwable -> {
                    Logger.log(LogType.PRINT, String.format("# 운영자에게 이메일 발송: %s", throwable.getMessage()));

                    return Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .take(5)
                        .skip(1)
                        .map(i -> num / i);
                })
            )
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2000L);
    }
}
