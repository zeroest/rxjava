package me.zeroest.rxjava.operators.creating;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.DateUtil;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.time.LocalDateTime;

public class Defer {
    public static void main(String[] args) {
        // given
        Observable<LocalDateTime> observable = Observable
            .defer(() -> {
                LocalDateTime now = LocalDateTime.now();
                return Observable.just(now);
            });

        Observable<LocalDateTime> observableJust = Observable.just(LocalDateTime.now());

        // when
        observable.subscribe(now ->
            Logger.log(LogType.PRINT, String.format("# defer() 구독1의 구독시간: %s", DateUtil.getDateFormatted(now)))
        );
        observableJust.subscribe(now ->
            Logger.log(LogType.PRINT, String.format("# just() 구독1의 구독시간: %s", DateUtil.getDateFormatted(now)))
        );

        // then
        /*
        print() | main | 18:15:42.767 | # defer() 구독1의 구독시간: 2022-09-04 18:15:42
        print() | main | 18:15:42.800 | # just() 구독1의 구독시간: 2022-09-04 18:15:42
        */

        TimeUtil.sleep(3000L);

        // when
        observable.subscribe(now ->
            Logger.log(LogType.PRINT, String.format("# defer() 구독2의 구독시간: %s", DateUtil.getDateFormatted(now)))
        );
        observableJust.subscribe(now ->
            Logger.log(LogType.PRINT, String.format("# just() 구독2의 구독시간: %s", DateUtil.getDateFormatted(now)))
        );

        // then
        /*
        print() | main | 18:15:45.802 | # defer() 구독2의 구독시간: 2022-09-04 18:15:45
        print() | main | 18:15:45.804 | # just() 구독2의 구독시간: 2022-09-04 18:15:42
        */
    }
}
