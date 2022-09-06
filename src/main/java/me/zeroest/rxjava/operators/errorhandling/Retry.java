package me.zeroest.rxjava.operators.errorhandling;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class Retry {
    private final static int RETRY_MAX = 5;

    public static void main(String[] args) {
        Observable
            .just(5)
            .flatMap(num ->
                Observable
                    .interval(200L, TimeUnit.MILLISECONDS)
                    .map(i -> {
                        long result;

                        try {
                            result = num / i;
                        } catch (ArithmeticException exception) {
                            Logger.log(LogType.PRINT, String.format("# Error: %s", exception.getMessage()));
                            throw exception;
                        }

                        return result;
                    })
                    .retry(5)
                    .onErrorReturn(throwable -> -1L)
            )
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        TimeUtil.sleep(3000L);
/*
print() | RxComputationThreadPool-1 | 23:05:51.315 | # Error: / by zero
print() | RxComputationThreadPool-2 | 23:05:51.544 | # Error: / by zero
print() | RxComputationThreadPool-3 | 23:05:51.745 | # Error: / by zero
print() | RxComputationThreadPool-4 | 23:05:51.945 | # Error: / by zero
print() | RxComputationThreadPool-5 | 23:05:52.146 | # Error: / by zero
print() | RxComputationThreadPool-6 | 23:05:52.347 | # Error: / by zero
onNext() | RxComputationThreadPool-6 | 23:05:52.348 | -1
onComplete() | RxComputationThreadPool-6 | 23:05:52.348
*/

        Observable
            .just(5)
            .flatMap(num ->
                Observable
                    .interval(200L, TimeUnit.MILLISECONDS)
                    .map(i -> {
                        long result;

                        try {
                            result = num / i;
                        } catch (ArithmeticException exception) {
                            Logger.log(LogType.PRINT, String.format("# Error: %s", exception.getMessage()));
                            throw exception;
                        }

                        return result;
                    })
                    .retry((retryCount, ex) -> {
                        Logger.log(LogType.PRINT, String.format("# 재시도 횟수: %d", retryCount));
                        TimeUtil.sleep(500L);
                        return retryCount < RETRY_MAX ? true : false;
                    })
                    .onErrorReturn(throwable -> -1L)
            )
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        TimeUtil.sleep(5000L);
/*
print() | RxComputationThreadPool-7 | 23:15:40.657 | # Error: / by zero
print() | RxComputationThreadPool-7 | 23:15:40.659 | # 재시도 횟수: 1
print() | RxComputationThreadPool-8 | 23:15:41.860 | # Error: / by zero
print() | RxComputationThreadPool-8 | 23:15:41.861 | # 재시도 횟수: 2
print() | RxComputationThreadPool-9 | 23:15:43.063 | # Error: / by zero
print() | RxComputationThreadPool-9 | 23:15:43.063 | # 재시도 횟수: 3
print() | RxComputationThreadPool-10 | 23:15:44.264 | # Error: / by zero
print() | RxComputationThreadPool-10 | 23:15:44.265 | # 재시도 횟수: 4
print() | RxComputationThreadPool-11 | 23:15:45.466 | # Error: / by zero
print() | RxComputationThreadPool-11 | 23:15:45.467 | # 재시도 횟수: 5
onNext() | RxComputationThreadPool-11 | 23:15:46.468 | -1
onComplete() | RxComputationThreadPool-11 | 23:15:46.468
*/

        Observable.just(10, 12, 15, 16)
            .zipWith(Observable.just(1, 2, 0, 4), (a, b) -> {
                int result;

                try {
                    result = a / b;
                } catch (ArithmeticException exception) {
                    Logger.log(LogType.PRINT, String.format("# Error: %s", exception.getMessage()));
                    throw exception;
                }

                return result;
            })
            .retry(3)
            .onErrorReturn(throwable -> -1)
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        TimeUtil.sleep(5000L);
/*
onNext() | main | 23:21:54.673 | 10
onNext() | main | 23:21:54.674 | 6
print() | main | 23:21:54.674 | # Error: / by zero
onNext() | main | 23:21:54.675 | 10
onNext() | main | 23:21:54.675 | 6
print() | main | 23:21:54.676 | # Error: / by zero
onNext() | main | 23:21:54.676 | 10
onNext() | main | 23:21:54.677 | 6
print() | main | 23:21:54.677 | # Error: / by zero
onNext() | main | 23:21:54.678 | 10
onNext() | main | 23:21:54.678 | 6
print() | main | 23:21:54.678 | # Error: / by zero
onNext() | main | 23:21:54.679 | -1
onComplete() | main | 23:21:54.679
*/
    }
}
