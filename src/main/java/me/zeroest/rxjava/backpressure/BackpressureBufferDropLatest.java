package me.zeroest.rxjava.backpressure;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/*
- BUFFER 전략 : DROP_LATEST

버퍼가 가득 찬 시점에 버퍼내에서 가장 최근에 버퍼로 들어온 데이터를 DROP한다.
DROP 된 빈 자리에 버퍼 밖에서 대기하던 데이터를 채운다.
*/
public class BackpressureBufferDropLatest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " + TimeUtil.getCurrentTimeFormatted());

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log("#interval doOnNext()", data))
            .onBackpressureBuffer(
                2, // 버퍼 사이즈
                () -> Logger.log("overflow!"),
                BackpressureOverflowStrategy.DROP_LATEST
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
# start : 02:29:58.853
#interval doOnNext() | RxComputationThreadPool-2 | 02:29:59.248 | 0                 // 0 통지 [0,-]
#onBackpressureBuffer doOnNext() | RxComputationThreadPool-2 | 02:29:59.272 | 0     // 소비자가 0의 데이터 처리 시작 [-,-]
#interval doOnNext() | RxComputationThreadPool-2 | 02:29:59.548 | 1                 // 1 통지 [1,-]
#interval doOnNext() | RxComputationThreadPool-2 | 02:29:59.848 | 2                 // 2 통지 [1,2]
#interval doOnNext() | RxComputationThreadPool-2 | 02:30:00.148 | 3                 // 3 통지 [1,2] <- 3
overflow! | RxComputationThreadPool-2 | 02:30:00.151                                // 버퍼 사이즈가 2이기 때문에 [1,2] 두 데이터가 차있어서 DROP_LATEST 정책에 맞게 2의 데이터가 Drop되고 3이 들어온다. [1,3]
onNext() | RxComputationThreadPool-1 | 02:30:00.273 | 0                             // 데이터 처리완료 시점 [1,3]
#onBackpressureBuffer doOnNext() | RxComputationThreadPool-1 | 02:30:00.274 | 1     // 소비자가 1의 데이터 처리 시작 [3,-]
#interval doOnNext() | RxComputationThreadPool-2 | 02:30:00.448 | 4                 // 4 통지 [3,4]
#interval doOnNext() | RxComputationThreadPool-2 | 02:30:00.748 | 5                 // 5 통지 [3,4] <- 5
overflow! | RxComputationThreadPool-2 | 02:30:00.748                                // 버퍼 사이즈가 2이기 때문에 [3,4] 두 데이터가 차있어서 DROP_LATEST 정책에 맞게 4의 데이터가 Drop되고 5이 들어온다. [3,5]
#interval doOnNext() | RxComputationThreadPool-2 | 02:30:01.048 | 6                 // 6 통지 [3,5]
overflow! | RxComputationThreadPool-2 | 02:30:01.048                                // 버퍼 사이즈가 2이기 때문에 [3,5] 두 데이터가 차있어서 DROP_LATEST 정책에 맞게 5의 데이터가 Drop되고 6이 들어온다. [3,6]
onNext() | RxComputationThreadPool-1 | 02:30:01.275 | 1                             // 데이터 처리완료 시점 [3,6]
#onBackpressureBuffer doOnNext() | RxComputationThreadPool-1 | 02:30:01.275 | 3     // 소비자가 3의 데이터 처리 시작 [6,-]
#interval doOnNext() | RxComputationThreadPool-2 | 02:30:01.348 | 7                 // 7 통지 [6,7]
#interval doOnNext() | RxComputationThreadPool-2 | 02:30:01.648 | 8                 // 8 통지 [6,7] <- 8
overflow! | RxComputationThreadPool-2 | 02:30:01.648                                // 버퍼 사이즈가 2이기 때문에 [6,7] 두 데이터가 차있어서 DROP_LATEST 정책에 맞게 7의 데이터가 Drop되고 8이 들어온다. [6,8]
*/
