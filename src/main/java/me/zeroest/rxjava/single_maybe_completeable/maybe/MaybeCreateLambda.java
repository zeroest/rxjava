package me.zeroest.rxjava.single_maybe_completeable.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.DateUtil;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class MaybeCreateLambda {
    public static void main(String[] args) {
        Maybe<String> maybe = Maybe.create(emitter -> {
            emitter.onSuccess(DateUtil.getNowDate());
//            onSuccess() | RxComputationThreadPool-1 | 18:07:59.030 | # date: 2022-09-03 18:07:59

//                데이터 통지 없이 완료처리
//                emitter.onComplete();
//            onComplete() | RxComputationThreadPool-1 | 18:08:14.817


//                에러 통지
//                emitter.onError(new IllegalStateException());
//            onERROR() | RxComputationThreadPool-1 | 18:08:30.410 | java.lang.IllegalStateException

        });

        maybe
            .observeOn(Schedulers.computation())
            .subscribe(
                data -> Logger.log(LogType.ON_SUCCESS, String.format("# date: %s", data)),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        TimeUtil.sleep(1000L);
    }
}
