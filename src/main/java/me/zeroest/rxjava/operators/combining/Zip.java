package me.zeroest.rxjava.operators.combining;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Zip {
    public static void main(String[] args) {
        Observable<Long> observable1 =
            Observable
                .interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data1 -> Logger.log(LogType.DO_ON_NEXT, String.format("observable1: %d", data1)))
                .take(4);

        Observable<Long> observable2 =
            Observable
                .interval(400L, TimeUnit.MILLISECONDS)
                .doOnNext(data2 -> Logger.log(LogType.DO_ON_NEXT, String.format("observable2: %d", data2)))
                .take(6);

        Observable
            .zip(observable1, observable2,
                (data1, data2) -> data1 + data2)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
/*
doOnNext() | RxComputationThreadPool-1 | 23:37:43.911 | observable1: 0
doOnNext() | RxComputationThreadPool-1 | 23:37:44.093 | observable1: 1
doOnNext() | RxComputationThreadPool-2 | 23:37:44.093 | observable2: 0
onNext() | RxComputationThreadPool-2 | 23:37:44.094 | 0
doOnNext() | RxComputationThreadPool-1 | 23:37:44.293 | observable1: 2
doOnNext() | RxComputationThreadPool-2 | 23:37:44.494 | observable2: 1
doOnNext() | RxComputationThreadPool-1 | 23:37:44.494 | observable1: 3
onNext() | RxComputationThreadPool-2 | 23:37:44.494 | 2
doOnNext() | RxComputationThreadPool-2 | 23:37:44.894 | observable2: 2
onNext() | RxComputationThreadPool-2 | 23:37:44.894 | 4
doOnNext() | RxComputationThreadPool-2 | 23:37:45.294 | observable2: 3
onNext() | RxComputationThreadPool-2 | 23:37:45.294 | 6
*/

        Observable<Integer> seoulOb = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> busanOb = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> incheonOb = Observable.fromIterable(SampleData.incheonPM10List);

        Observable<Integer> hourRange =
            Observable
                .range(1, 24);

        Observable
            .zip(seoulOb, busanOb, incheonOb, hourRange,
                (seoul, busan, incheon, hour) ->
                    String.format("%d???: %s", hour, Collections.max(Arrays.asList(seoul, busan, incheon))))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 23:49:10.481 | 1???: 55
onNext() | main | 23:49:10.482 | 2???: 40
onNext() | main | 23:49:10.483 | 3???: 73
onNext() | main | 23:49:10.484 | 4???: 70
onNext() | main | 23:49:10.484 | 5???: 100
onNext() | main | 23:49:10.485 | 6???: 110
onNext() | main | 23:49:10.486 | 7???: 120
onNext() | main | 23:49:10.486 | 8???: 90
onNext() | main | 23:49:10.487 | 9???: 80
onNext() | main | 23:49:10.488 | 10???: 73
onNext() | main | 23:49:10.488 | 11???: 80
onNext() | main | 23:49:10.489 | 12???: 70
onNext() | main | 23:49:10.489 | 13???: 95
onNext() | main | 23:49:10.490 | 14???: 95
onNext() | main | 23:49:10.491 | 15???: 100
onNext() | main | 23:49:10.491 | 16???: 150
onNext() | main | 23:49:10.492 | 17???: 140
onNext() | main | 23:49:10.493 | 18???: 130
onNext() | main | 23:49:10.493 | 19???: 170
onNext() | main | 23:49:10.494 | 20???: 130
onNext() | main | 23:49:10.494 | 21???: 100
onNext() | main | 23:49:10.495 | 22???: 125
onNext() | main | 23:49:10.496 | 23???: 135
onNext() | main | 23:49:10.496 | 24???: 125
*/
    }
}
