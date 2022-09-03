package me.zeroest.rxjava.single_maybe_completeable.single;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.DateUtil;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class SingleJust {
    public static void main(String[] args) {
        Single.just(DateUtil.getNowDate())
            .observeOn(Schedulers.computation())
            .subscribe(
                data -> Logger.log(LogType.ON_SUCCESS, String.format("# date: %s", data)),
                error -> Logger.log(LogType.ON_ERROR, error)
            );

        TimeUtil.sleep(500L);
    }
}
