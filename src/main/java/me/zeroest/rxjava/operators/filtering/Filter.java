package me.zeroest.rxjava.operators.filtering;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.CarMaker;

public class Filter {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
            .filter(car -> CarMaker.CHEVROLET.equals(car.getCarMaker()))
            .filter(car -> car.getCarPrice() > 30_000_000)
            .subscribe(car -> Logger.log(LogType.ON_NEXT,
                String.format("%s : %s : %d", car.getCarMaker().name(), car.getCarName(), car.getCarPrice())
            ));
    }
}
