package me.zeroest.rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class SchedulerNewThread {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6");

        observable.subscribeOn(Schedulers.newThread())
            .map(data -> String.format("## %s ##", data))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.newThread())
            .map(data -> String.format("$$ %s $$", data))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(300L);
    }
}

/*
onNext() | RxNewThreadScheduler-1 | 02:37:32.178 | ## 1 ##
onNext() | RxNewThreadScheduler-2 | 02:37:32.179 | $$ 1 $$
onNext() | RxNewThreadScheduler-1 | 02:37:32.234 | ## 2 ##
onNext() | RxNewThreadScheduler-2 | 02:37:32.234 | $$ 2 $$
onNext() | RxNewThreadScheduler-1 | 02:37:32.234 | ## 3 ##
onNext() | RxNewThreadScheduler-2 | 02:37:32.234 | $$ 3 $$
onNext() | RxNewThreadScheduler-1 | 02:37:32.234 | ## 4 ##
onNext() | RxNewThreadScheduler-2 | 02:37:32.234 | $$ 4 $$
onNext() | RxNewThreadScheduler-1 | 02:37:32.234 | ## 5 ##
onNext() | RxNewThreadScheduler-2 | 02:37:32.234 | $$ 5 $$
onNext() | RxNewThreadScheduler-1 | 02:37:32.235 | ## 6 ##
onNext() | RxNewThreadScheduler-2 | 02:37:32.235 | $$ 6 $$
*/
