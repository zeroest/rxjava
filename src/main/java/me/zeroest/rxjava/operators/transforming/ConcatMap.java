package me.zeroest.rxjava.operators.transforming;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * 순서를 보장해주는 concatMap 예제
 * 순차적으로 실행되기 때문에 flatMap보다 느리다.
 */
public class ConcatMap {
    public static void main(String[] args) {
        TimeUtil.start();

        Observable
            .interval(100L, TimeUnit.MILLISECONDS)
            .take(4)
            .skip(2)
            .flatMap(num ->
                Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .take(10)
                    .skip(1)
                    .map(row -> String.format("%d * %d = %d", num, row, (num * row)))
            )
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
            );

        TimeUtil.sleep(3000L);
/*
onNext() | RxComputationThreadPool-2 | 01:01:50.528 | 2 * 1 = 2
onNext() | RxComputationThreadPool-2 | 01:01:50.638 | 3 * 1 = 3
onNext() | RxComputationThreadPool-2 | 01:01:50.682 | 2 * 2 = 4
onNext() | RxComputationThreadPool-3 | 01:01:50.781 | 3 * 2 = 6
onNext() | RxComputationThreadPool-2 | 01:01:50.882 | 2 * 3 = 6
onNext() | RxComputationThreadPool-3 | 01:01:50.981 | 3 * 3 = 9
onNext() | RxComputationThreadPool-2 | 01:01:51.082 | 2 * 4 = 8
onNext() | RxComputationThreadPool-3 | 01:01:51.181 | 3 * 4 = 12
onNext() | RxComputationThreadPool-2 | 01:01:51.282 | 2 * 5 = 10
onNext() | RxComputationThreadPool-3 | 01:01:51.381 | 3 * 5 = 15
onNext() | RxComputationThreadPool-2 | 01:01:51.482 | 2 * 6 = 12
onNext() | RxComputationThreadPool-3 | 01:01:51.581 | 3 * 6 = 18
onNext() | RxComputationThreadPool-2 | 01:01:51.682 | 2 * 7 = 14
onNext() | RxComputationThreadPool-3 | 01:01:51.781 | 3 * 7 = 21
onNext() | RxComputationThreadPool-2 | 01:01:51.882 | 2 * 8 = 16
onNext() | RxComputationThreadPool-3 | 01:01:51.981 | 3 * 8 = 24
onNext() | RxComputationThreadPool-2 | 01:01:52.082 | 2 * 9 = 18
onNext() | RxComputationThreadPool-3 | 01:01:52.181 | 3 * 9 = 27

# 실행시간: 2554 ms
*/

        // =============================================================================

        TimeUtil.start();

        Observable
            .interval(100L, TimeUnit.MILLISECONDS)
            .take(4)
            .skip(2)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .concatMap(num ->
                Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .take(10)
                    .skip(1)
                    .map(row -> String.format("%d * %d = %d", num, row, (num * row)))
            )
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
            );

        TimeUtil.sleep(5000L);
/*
onNext() | RxComputationThreadPool-2 | 00:53:07.056 | 2 * 1 = 2
onNext() | RxComputationThreadPool-2 | 00:53:07.239 | 2 * 2 = 4
onNext() | RxComputationThreadPool-2 | 00:53:07.439 | 2 * 3 = 6
onNext() | RxComputationThreadPool-2 | 00:53:07.639 | 2 * 4 = 8
onNext() | RxComputationThreadPool-2 | 00:53:07.839 | 2 * 5 = 10
onNext() | RxComputationThreadPool-2 | 00:53:08.039 | 2 * 6 = 12
onNext() | RxComputationThreadPool-2 | 00:53:08.239 | 2 * 7 = 14
onNext() | RxComputationThreadPool-2 | 00:53:08.439 | 2 * 8 = 16
onNext() | RxComputationThreadPool-2 | 00:53:08.639 | 2 * 9 = 18

onNext() | RxComputationThreadPool-3 | 00:53:09.041 | 3 * 1 = 3
onNext() | RxComputationThreadPool-3 | 00:53:09.240 | 3 * 2 = 6
onNext() | RxComputationThreadPool-3 | 00:53:09.440 | 3 * 3 = 9
onNext() | RxComputationThreadPool-3 | 00:53:09.640 | 3 * 4 = 12
onNext() | RxComputationThreadPool-3 | 00:53:09.840 | 3 * 5 = 15
onNext() | RxComputationThreadPool-3 | 00:53:10.040 | 3 * 6 = 18
onNext() | RxComputationThreadPool-3 | 00:53:10.241 | 3 * 7 = 21
onNext() | RxComputationThreadPool-3 | 00:53:10.440 | 3 * 8 = 24
onNext() | RxComputationThreadPool-3 | 00:53:10.640 | 3 * 9 = 27

# 실행시간: 4381 ms
*/

        // =============================================================================

        TimeUtil.start();

        Observable
            .interval(1000L, TimeUnit.MILLISECONDS)
            .take(4)
            .skip(2)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .concatMap(num ->
                Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .take(10)
                    .skip(1)
                    .map(row -> String.format("%d * %d = %d", num, row, (num * row)))
            )
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
            );

        TimeUtil.sleep(10000L);
/*
doOnNext() | RxComputationThreadPool-7 | 01:16:04.055 | 2
onNext() | RxComputationThreadPool-8 | 01:16:04.458 | 2 * 1 = 2
onNext() | RxComputationThreadPool-8 | 01:16:04.658 | 2 * 2 = 4
onNext() | RxComputationThreadPool-8 | 01:16:04.858 | 2 * 3 = 6
doOnNext() | RxComputationThreadPool-7 | 01:16:05.055 | 3
onNext() | RxComputationThreadPool-8 | 01:16:05.058 | 2 * 4 = 8
onNext() | RxComputationThreadPool-8 | 01:16:05.258 | 2 * 5 = 10
onNext() | RxComputationThreadPool-8 | 01:16:05.458 | 2 * 6 = 12
onNext() | RxComputationThreadPool-8 | 01:16:05.658 | 2 * 7 = 14
onNext() | RxComputationThreadPool-8 | 01:16:05.858 | 2 * 8 = 16
onNext() | RxComputationThreadPool-8 | 01:16:06.058 | 2 * 9 = 18

onNext() | RxComputationThreadPool-9 | 01:16:06.460 | 3 * 1 = 3
onNext() | RxComputationThreadPool-9 | 01:16:06.659 | 3 * 2 = 6
onNext() | RxComputationThreadPool-9 | 01:16:06.859 | 3 * 3 = 9
onNext() | RxComputationThreadPool-9 | 01:16:07.059 | 3 * 4 = 12
onNext() | RxComputationThreadPool-9 | 01:16:07.259 | 3 * 5 = 15
onNext() | RxComputationThreadPool-9 | 01:16:07.459 | 3 * 6 = 18
onNext() | RxComputationThreadPool-9 | 01:16:07.659 | 3 * 7 = 21
onNext() | RxComputationThreadPool-9 | 01:16:07.859 | 3 * 8 = 24
onNext() | RxComputationThreadPool-9 | 01:16:08.059 | 3 * 9 = 27

# 실행시간: 7010 ms
*/
    }
}
