package me.zeroest.rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.Arrays;
import java.util.Collections;

public class SchedulerComputation {
    public static void main(String[] args) {
        Observable<Integer> seoulOb = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> busanOb = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> incheonOb = Observable.fromIterable(SampleData.incheonPM10List);

        Observable<Integer> hourRange = Observable.range(1, 24);

        Observable<String> source = Observable.zip(seoulOb, busanOb, incheonOb, hourRange,
            (seoul, busan, incheon, hour) -> String.format("%d시: %s", hour, Collections.max(Arrays.asList(seoul, busan, incheon))));

        source.subscribeOn(Schedulers.computation())
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        source.subscribeOn(Schedulers.computation())
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(500L);
    }
}

/*
onNext() | RxComputationThreadPool-2 | 02:37:50.829 | 1시: 55
onNext() | RxComputationThreadPool-1 | 02:37:50.829 | 1시: 55
onNext() | RxComputationThreadPool-2 | 02:37:50.880 | 2시: 40
onNext() | RxComputationThreadPool-1 | 02:37:50.880 | 2시: 40
onNext() | RxComputationThreadPool-2 | 02:37:50.880 | 3시: 73
onNext() | RxComputationThreadPool-1 | 02:37:50.881 | 3시: 73
onNext() | RxComputationThreadPool-2 | 02:37:50.881 | 4시: 70
onNext() | RxComputationThreadPool-1 | 02:37:50.881 | 4시: 70
onNext() | RxComputationThreadPool-2 | 02:37:50.881 | 5시: 100
onNext() | RxComputationThreadPool-1 | 02:37:50.881 | 5시: 100
onNext() | RxComputationThreadPool-2 | 02:37:50.881 | 6시: 110
onNext() | RxComputationThreadPool-1 | 02:37:50.882 | 6시: 110
onNext() | RxComputationThreadPool-2 | 02:37:50.882 | 7시: 120
onNext() | RxComputationThreadPool-1 | 02:37:50.882 | 7시: 120
onNext() | RxComputationThreadPool-2 | 02:37:50.882 | 8시: 90
onNext() | RxComputationThreadPool-1 | 02:37:50.882 | 8시: 90
onNext() | RxComputationThreadPool-2 | 02:37:50.882 | 9시: 80
onNext() | RxComputationThreadPool-1 | 02:37:50.883 | 9시: 80
onNext() | RxComputationThreadPool-2 | 02:37:50.883 | 10시: 73
onNext() | RxComputationThreadPool-1 | 02:37:50.883 | 10시: 73
onNext() | RxComputationThreadPool-2 | 02:37:50.883 | 11시: 80
onNext() | RxComputationThreadPool-1 | 02:37:50.883 | 11시: 80
onNext() | RxComputationThreadPool-2 | 02:37:50.884 | 12시: 70
onNext() | RxComputationThreadPool-1 | 02:37:50.884 | 12시: 70
onNext() | RxComputationThreadPool-2 | 02:37:50.884 | 13시: 95
onNext() | RxComputationThreadPool-1 | 02:37:50.884 | 13시: 95
onNext() | RxComputationThreadPool-2 | 02:37:50.884 | 14시: 95
onNext() | RxComputationThreadPool-1 | 02:37:50.884 | 14시: 95
onNext() | RxComputationThreadPool-2 | 02:37:50.885 | 15시: 100
onNext() | RxComputationThreadPool-1 | 02:37:50.885 | 15시: 100
onNext() | RxComputationThreadPool-2 | 02:37:50.885 | 16시: 150
onNext() | RxComputationThreadPool-1 | 02:37:50.885 | 16시: 150
onNext() | RxComputationThreadPool-2 | 02:37:50.885 | 17시: 140
onNext() | RxComputationThreadPool-1 | 02:37:50.885 | 17시: 140
onNext() | RxComputationThreadPool-2 | 02:37:50.885 | 18시: 130
onNext() | RxComputationThreadPool-1 | 02:37:50.886 | 18시: 130
onNext() | RxComputationThreadPool-1 | 02:37:50.886 | 19시: 170
onNext() | RxComputationThreadPool-2 | 02:37:50.886 | 19시: 170
onNext() | RxComputationThreadPool-1 | 02:37:50.886 | 20시: 130
onNext() | RxComputationThreadPool-2 | 02:37:50.886 | 20시: 130
onNext() | RxComputationThreadPool-1 | 02:37:50.886 | 21시: 100
onNext() | RxComputationThreadPool-2 | 02:37:50.886 | 21시: 100
onNext() | RxComputationThreadPool-1 | 02:37:50.886 | 22시: 125
onNext() | RxComputationThreadPool-2 | 02:37:50.887 | 22시: 125
onNext() | RxComputationThreadPool-1 | 02:37:50.887 | 23시: 135
onNext() | RxComputationThreadPool-2 | 02:37:50.887 | 23시: 135
onNext() | RxComputationThreadPool-1 | 02:37:50.887 | 24시: 125
onNext() | RxComputationThreadPool-2 | 02:37:50.887 | 24시: 125
*/
