package me.zeroest.rxjava.single_maybe_completable.completable;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.stream.IntStream;

public class CompletableCreateLambda {
    public static void main(String[] args) {
        Completable completable = Completable.create(emitter -> {
            // 데이터를 통지하는것이 아니라 특정 작업을 수행한 후, 완료를 통지한다.
            int sum = IntStream.range(0, 100)
                .reduce(Integer::sum)
                .orElse(0);
            Logger.log(LogType.PRINT, String.format("# sum: %d", sum));

            emitter.onComplete();
/*
onSubscribe() | main | 18:25:08.700
print() | main | 18:25:08.736 | # sum: 4950
onComplete() | RxComputationThreadPool-1 | 18:25:08.765
*/

//                emitter.onError(new IllegalStateException());
/*
onSubscribe() | main | 18:26:06.299
print() | main | 18:26:06.333 | # sum: 4950
onERROR() | RxComputationThreadPool-1 | 18:26:06.365 | java.lang.IllegalStateException
*/
        });

        completable
            .subscribeOn(Schedulers.computation())
            .subscribe(
                () -> Logger.log(LogType.ON_COMPLETE),
                error -> Logger.log(LogType.ON_ERROR, error)
            );

        TimeUtil.sleep(1000L);
    }
}
