package me.zeroest.rxjava.operators.conditionalboolean;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.Car;
import me.zeroest.rxjava.util.data.car.CarMaker;

public class All {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
            .doOnNext(car ->
                Logger.log(LogType.DO_ON_NEXT,
                    String.format("Car Maker: %s,\tCar Name: %s",
                        car.getCarMaker().name(),
                        car.getCarName()))
            )
            .map(Car::getCarMaker)
            .all(CarMaker.CHEVROLET::equals)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
doOnNext() | main | 00:48:44.928 | Car Maker: CHEVROLET, 	Car Name: 말리부
doOnNext() | main | 00:48:44.966 | Car Maker: HYUNDAE, 	Car Name: 쏘렌토
onNext() | main | 00:48:44.966 | false
*/
    }
}
