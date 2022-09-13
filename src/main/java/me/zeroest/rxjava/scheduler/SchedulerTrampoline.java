package me.zeroest.rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

/**
 * 현재 실행되고 있는 쓰레드의 대기큐에 처리 작업을 등록
 * 대기 큐에 등록되는 순서대로 작업을 처리
 * */
public class SchedulerTrampoline {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6");

        observable
            .subscribeOn(Schedulers.trampoline())
            .map(data -> String.format("## %s ##", data))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable
            .subscribeOn(Schedulers.trampoline())
            .map(data -> String.format("$$ %s $$", data))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
    }
}

/*
onNext() | main | 02:41:27.268 | ## 1 ##
onNext() | main | 02:41:27.300 | ## 2 ##
onNext() | main | 02:41:27.300 | ## 3 ##
onNext() | main | 02:41:27.300 | ## 4 ##
onNext() | main | 02:41:27.300 | ## 5 ##
onNext() | main | 02:41:27.300 | ## 6 ##
onNext() | main | 02:41:27.301 | $$ 1 $$
onNext() | main | 02:41:27.301 | $$ 2 $$
onNext() | main | 02:41:27.302 | $$ 3 $$
onNext() | main | 02:41:27.302 | $$ 4 $$
onNext() | main | 02:41:27.302 | $$ 5 $$
onNext() | main | 02:41:27.302 | $$ 6 $$
*/
