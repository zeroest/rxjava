package me.zeroest.rxjava.operators.conditionalboolean;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.CarMaker;

public class Contains {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .contains(CarMaker.SAMSUNG)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
doOnNext() | main | 01:00:57.008 | CHEVROLET
doOnNext() | main | 01:00:57.051 | HYUNDAE
doOnNext() | main | 01:00:57.052 | SAMSUNG
onNext() | main | 01:00:57.052 | true
*/
    }
}
