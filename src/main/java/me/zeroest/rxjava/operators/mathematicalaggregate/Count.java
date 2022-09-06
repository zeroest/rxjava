package me.zeroest.rxjava.operators.mathematicalaggregate;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.Arrays;

public class Count {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
            .count()
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 01:20:14.218 | 9
*/

        Observable.concat(
            Arrays.asList(
                Observable.fromIterable(SampleData.seoulPM10List), //.observeOn(Schedulers.computation()),
                Observable.fromIterable(SampleData.busanPM10List), //.observeOn(Schedulers.newThread()),
                Observable.fromIterable(SampleData.incheonPM10List) //.observeOn(Schedulers.io())
            )
        )
            .doOnNext(data-> Logger.log(LogType.DO_ON_NEXT, data))
            .count()
            .subscribe(data-> Logger.log(LogType.ON_NEXT, data));

//        TimeUtil.sleep(3000L);
/*
onNext() | main | 01:20:50.522 | 72
*/
    }
}
