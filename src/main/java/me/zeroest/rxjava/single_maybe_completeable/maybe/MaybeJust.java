package me.zeroest.rxjava.single_maybe_completeable.maybe;

import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.DateUtil;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class MaybeJust {
    public static void main(String[] args) {
        Maybe.just(DateUtil.getNowDate())
            .observeOn(Schedulers.computation())
            .subscribe(
                data -> Logger.log(LogType.ON_SUCCESS, String.format("# date: %s", data)),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );
//        onSuccess() | RxComputationThreadPool-1 | 18:06:13.464 | # date: 2022-09-03 18:06:13


        Maybe.empty()
            .observeOn(Schedulers.computation())
            .subscribe(
                data -> Logger.log(LogType.ON_SUCCESS, String.format("# date: %s", data)),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );
//        onComplete() | RxComputationThreadPool-2 | 18:06:42.698

        TimeUtil.sleep(1000L);
    }
}
