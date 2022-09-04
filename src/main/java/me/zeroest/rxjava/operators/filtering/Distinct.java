package me.zeroest.rxjava.operators.filtering;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.data.SampleData;

public class Distinct {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
            .distinct()
            .subscribe(carMaker -> Logger.log(LogType.ON_NEXT,
                String.format("CarMaker : %s", carMaker.name())
            ));

        Observable.fromIterable(SampleData.carList)
            .distinct(car -> car.getCarMaker())
            .subscribe(car -> Logger.log(LogType.ON_NEXT,
                String.format("%s : %s : %d", car.getCarMaker().name(), car.getCarName(), car.getCarPrice())
            ));
/*
onNext() | main | 23:29:30.694 | CHEVROLET : 말리부 : 23000000
onNext() | main | 23:29:30.694 | HYUNDAE : 쏘렌토 : 33000000
onNext() | main | 23:29:30.694 | SSANGYOUNG : 티볼리 : 23000000
onNext() | main | 23:29:30.695 | SAMSUNG : SM6 : 40000000
*/
    }
}
