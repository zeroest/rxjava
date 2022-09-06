package me.zeroest.rxjava.operators.conditionalboolean;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Amb {
    public static void main(String[] args) {
        List<Observable<Integer>> observables = Arrays.asList(
            Observable.fromIterable(SampleData.salesOfBranchA)
                .delay(500L, TimeUnit.MILLISECONDS)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch A's sales")),
            Observable.fromIterable(SampleData.salesOfBranchB)
                .delay(300L, TimeUnit.MILLISECONDS)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch B's sales")),
            Observable.fromIterable(SampleData.salesOfBranchC)
                .delay(200L, TimeUnit.MILLISECONDS)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# branch C's sales"))
        );

        Observable.amb(observables)
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# 완료"))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
/*
onNext() | RxComputationThreadPool-3 | 00:55:32.690 | 12000000
onNext() | RxComputationThreadPool-3 | 00:55:32.727 | 21000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 19000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 33000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 33000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 41000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 52000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 48000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 32000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 21000000
onNext() | RxComputationThreadPool-3 | 00:55:32.728 | 18000000
onNext() | RxComputationThreadPool-3 | 00:55:32.729 | 14000000
doOnComplete() | RxComputationThreadPool-3 | 00:55:32.729 | # branch C's sales
doOnComplete() | RxComputationThreadPool-3 | 00:55:32.729 | # 완료
*/
    }
}
