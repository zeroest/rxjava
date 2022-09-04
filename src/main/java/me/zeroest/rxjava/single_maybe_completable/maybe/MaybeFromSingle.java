package me.zeroest.rxjava.single_maybe_completable.maybe;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.DateUtil;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class MaybeFromSingle {
    public static void main(String[] args) {
        Single<String> single = Single.just(DateUtil.getNowDate());

        Maybe.fromSingle(single)
            .observeOn(Schedulers.computation())
            .subscribe(
                data -> Logger.log(LogType.ON_SUCCESS, String.format("# date: %s", data)),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );
//        onSuccess() | RxComputationThreadPool-1 | 18:10:41.029 | # date: 2022-09-03 18:10:40


        TimeUtil.sleep(1000L);
    }
}
