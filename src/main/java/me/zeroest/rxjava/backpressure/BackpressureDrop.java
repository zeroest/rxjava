package me.zeroest.rxjava.backpressure;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/*
- DROP 전략

버퍼에 데이터가 모두 채워진 상태가 되면 이후에 생성되는 데이터를 버리고(DROP),
버퍼가 비워지는 시점에 DROP되지 않은 데이터부터 다시 버퍼에 담는다
*/
public class BackpressureDrop {
    public static void main(String[] args) {
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .onBackpressureDrop(dropData -> Logger.log(LogType.PRINT, dropData + " drop!"))
            .observeOn(
                Schedulers.computation(),
                false,
                1
            )
            .subscribe(
                data -> {
                    Logger.log("subscribe : " + data + " in processing");
                    TimeUtil.sleep(1000L);
                    Logger.log(LogType.ON_NEXT,data);
                },
                error -> Logger.log(LogType.ON_ERROR, error)
            );

        TimeUtil.sleep(5500L);
    }
}

/*
doOnNext() | RxComputationThreadPool-2 | 14:38:27.068 | 0
subscribe : 0 in processing | RxComputationThreadPool-1 | 14:38:27.157
doOnNext() | RxComputationThreadPool-2 | 14:38:27.351 | 1
print() | RxComputationThreadPool-2 | 14:38:27.357 | 1 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:27.651 | 2
print() | RxComputationThreadPool-2 | 14:38:27.651 | 2 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:27.951 | 3
print() | RxComputationThreadPool-2 | 14:38:27.951 | 3 drop!
onNext() | RxComputationThreadPool-1 | 14:38:28.173 | 0
doOnNext() | RxComputationThreadPool-2 | 14:38:28.251 | 4
subscribe : 4 in processing | RxComputationThreadPool-1 | 14:38:28.252
doOnNext() | RxComputationThreadPool-2 | 14:38:28.551 | 5
print() | RxComputationThreadPool-2 | 14:38:28.551 | 5 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:28.851 | 6
print() | RxComputationThreadPool-2 | 14:38:28.851 | 6 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:29.151 | 7
print() | RxComputationThreadPool-2 | 14:38:29.151 | 7 drop!
onNext() | RxComputationThreadPool-1 | 14:38:29.252 | 4
doOnNext() | RxComputationThreadPool-2 | 14:38:29.451 | 8
subscribe : 8 in processing | RxComputationThreadPool-1 | 14:38:29.452
doOnNext() | RxComputationThreadPool-2 | 14:38:29.751 | 9
print() | RxComputationThreadPool-2 | 14:38:29.751 | 9 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:30.051 | 10
print() | RxComputationThreadPool-2 | 14:38:30.051 | 10 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:30.351 | 11
print() | RxComputationThreadPool-2 | 14:38:30.351 | 11 drop!
onNext() | RxComputationThreadPool-1 | 14:38:30.452 | 8
doOnNext() | RxComputationThreadPool-2 | 14:38:30.651 | 12
subscribe : 12 in processing | RxComputationThreadPool-1 | 14:38:30.652
doOnNext() | RxComputationThreadPool-2 | 14:38:30.951 | 13
print() | RxComputationThreadPool-2 | 14:38:30.951 | 13 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:31.251 | 14
print() | RxComputationThreadPool-2 | 14:38:31.251 | 14 drop!
doOnNext() | RxComputationThreadPool-2 | 14:38:31.551 | 15
print() | RxComputationThreadPool-2 | 14:38:31.551 | 15 drop!
onNext() | RxComputationThreadPool-1 | 14:38:31.652 | 12
doOnNext() | RxComputationThreadPool-2 | 14:38:31.851 | 16
subscribe : 16 in processing | RxComputationThreadPool-1 | 14:38:31.852
doOnNext() | RxComputationThreadPool-2 | 14:38:32.151 | 17
print() | RxComputationThreadPool-2 | 14:38:32.151 | 17 drop!
*/
