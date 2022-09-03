package me.zeroest.rxjava.backpressure;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/*
- BUFFER 전략 : DROP_OLDEST

버퍼가 가득 찬 시점에 버퍼내에서 가장 오래전에(먼저) 버퍼로 들어온 데이터를 DROP
DROP 된 빈 자리에는 버퍼 밖에서 대기하던 데이터를 채운다.
*/
public class BackpressureBufferDropOldest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " + TimeUtil.getCurrentTimeFormatted());

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log("#interval doOnNext()", data))
            .onBackpressureBuffer(
                2, // 버퍼 사이즈
                () -> Logger.log("overflow!"),
                BackpressureOverflowStrategy.DROP_OLDEST
            )
            .doOnNext(data -> Logger.log("#onBackpressureBuffer doOnNext()", data))
            .observeOn(
                Schedulers.computation(),
                false,
                1 // 데이터 요청 사이즈
            )
            .subscribe(
                data -> {
                    Thread.sleep(1000L);
                    Logger.log(LogType.ON_NEXT, data);
                },
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        Thread.sleep(2800L);
    }
}

/*
# start : 03:20:07.112
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:07.504 | 0                 // 0 통지 [0,-]
#onBackpressureBuffer doOnNext() | RxComputationThreadPool-2 | 03:20:07.531 | 0     // 소비자가 0의 데이터 처리 시작 [-,-]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:07.804 | 1                 // 1 통지 [1,-]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:08.104 | 2                 // 2 통지 [1,2]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:08.404 | 3                 // 3 통지 [1,2] <- 3
overflow! | RxComputationThreadPool-2 | 03:20:08.406                                // 버퍼 사이즈가 2이기 때문에 [1,2] 두 데이터가 차있어서 DROP_OLDEST 정책에 맞게 1의 데이터가 Drop되고 3이 들어온다. [2,3]
onNext() | RxComputationThreadPool-1 | 03:20:08.532 | 0                             // 데이터 처리완료 시점 [2,3]
#onBackpressureBuffer doOnNext() | RxComputationThreadPool-1 | 03:20:08.533 | 2     // 소비자가 2의 데이터 처리 시작 [3,-]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:08.704 | 4                 // 4 통지 [3,4]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:09.004 | 5                 // 5 통지 [3,4] <- 5
overflow! | RxComputationThreadPool-2 | 03:20:09.004                                // 버퍼 사이즈가 2이기 때문에 [3,4] 두 데이터가 차있어서 DROP_OLDEST 정책에 맞게 3의 데이터가 Drop되고 5이 들어온다. [4,5]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:09.304 | 6                 // 6 통지 [4,5] <- 6
overflow! | RxComputationThreadPool-2 | 03:20:09.304                                // 버퍼 사이즈가 2이기 때문에 [4,5] 두 데이터가 차있어서 DROP_OLDEST 정책에 맞게 4의 데이터가 Drop되고 6이 들어온다. [5,6]
onNext() | RxComputationThreadPool-1 | 03:20:09.533 | 2                             // 데이터 처리완료 시점 [5,6]
#onBackpressureBuffer doOnNext() | RxComputationThreadPool-1 | 03:20:09.534 | 5     // 소비자가 5의 데이터 처리 시작 [6,-]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:09.604 | 7                 // 7 통지 [6,7]
#interval doOnNext() | RxComputationThreadPool-2 | 03:20:09.904 | 8                 // 8 통지 [6,7] <- 8
overflow! | RxComputationThreadPool-2 | 03:20:09.904                                // 버퍼 사이즈가 2이기 때문에 [6,7] 두 데이터가 차있어서 DROP_OLDEST 정책에 맞게 6의 데이터가 Drop되고 8이 들어온다. [7,8]
*/
