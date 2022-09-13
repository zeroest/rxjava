package me.zeroest.rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class SchedulerSingle {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6");

        observable
            .subscribeOn(Schedulers.single())
            .map(data -> String.format("## %s ##", data))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable
            .subscribeOn(Schedulers.single())
            .map(data -> String.format("$$ %s $$", data))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
    }
}

/*
onNext() | RxSingleScheduler-1 | 02:45:13.978 | ## 1 ##
onNext() | RxSingleScheduler-1 | 02:45:14.017 | ## 2 ##
onNext() | RxSingleScheduler-1 | 02:45:14.017 | ## 3 ##
onNext() | RxSingleScheduler-1 | 02:45:14.017 | ## 4 ##
onNext() | RxSingleScheduler-1 | 02:45:14.017 | ## 5 ##
onNext() | RxSingleScheduler-1 | 02:45:14.017 | ## 6 ##
onNext() | RxSingleScheduler-1 | 02:45:14.018 | $$ 1 $$
onNext() | RxSingleScheduler-1 | 02:45:14.018 | $$ 2 $$
onNext() | RxSingleScheduler-1 | 02:45:14.018 | $$ 3 $$
onNext() | RxSingleScheduler-1 | 02:45:14.018 | $$ 4 $$
onNext() | RxSingleScheduler-1 | 02:45:14.018 | $$ 5 $$
onNext() | RxSingleScheduler-1 | 02:45:14.018 | $$ 6 $$
*/
