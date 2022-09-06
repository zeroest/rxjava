package me.zeroest.rxjava.operators.conditionalboolean;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.CarMaker;

public class SequenceEqual {
    public static void main(String[] args) {
        Observable<CarMaker> observable1 =
            Observable
                .fromArray(SampleData.carMakers)
                .subscribeOn(Schedulers.computation())
                .delay(carMaker -> {
                    TimeUtil.sleep(500L);
                    return Observable.just(carMaker);
                })
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# observable1 : " + data));

        Observable<CarMaker> observable2 =
            Observable
                .fromArray(SampleData.carMakersDuplicated)
                .delay(carMaker -> {
                    TimeUtil.sleep(1000L);
                    return Observable.just(carMaker);
                })
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# observable2 : " + data));


        Observable.sequenceEqual(observable1, observable2)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
doOnNext() | RxComputationThreadPool-1 | 01:09:14.996 | # observable1 : CHEVROLET
doOnNext() | main | 01:09:15.404 | # observable2 : CHEVROLET
doOnNext() | RxComputationThreadPool-1 | 01:09:15.604 | # observable1 : HYUNDAE
doOnNext() | RxComputationThreadPool-1 | 01:09:16.105 | # observable1 : SAMSUNG
doOnNext() | main | 01:09:16.405 | # observable2 : HYUNDAE
doOnNext() | RxComputationThreadPool-1 | 01:09:16.606 | # observable1 : SSANGYOUNG
doOnNext() | RxComputationThreadPool-1 | 01:09:17.107 | # observable1 : KIA
doOnNext() | main | 01:09:17.405 | # observable2 : SAMSUNG
doOnNext() | main | 01:09:18.406 | # observable2 : SSANGYOUNG
doOnNext() | main | 01:09:19.407 | # observable2 : CHEVROLET
onNext() | main | 01:09:19.407 | false
doOnNext() | main | 01:09:20.408 | # observable2 : HYUNDAE
doOnNext() | main | 01:09:21.409 | # observable2 : KIA
doOnNext() | main | 01:09:22.410 | # observable2 : SSANGYOUNG
*/
    }
}
