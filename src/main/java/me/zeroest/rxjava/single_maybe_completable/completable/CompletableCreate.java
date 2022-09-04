package me.zeroest.rxjava.single_maybe_completable.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.stream.IntStream;

public class CompletableCreate {
    public static void main(String[] args) {
        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
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
            }
        });

        completable
            .observeOn(Schedulers.computation())
            .subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    Logger.log(LogType.ON_SUBSCRIBE);
                }

                @Override
                public void onComplete() {
                    Logger.log(LogType.ON_COMPLETE);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Logger.log(LogType.ON_ERROR, e);
                }
            });

        TimeUtil.sleep(1000L);
    }
}
