package me.zeroest.rxjava.blocking_xxx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.Car;
import me.zeroest.rxjava.util.data.car.CarMaker;

import java.util.List;

/**
 * 단위 테스트를 설명하기 위한 Sample Observable 클래스
 */
public class SampleObservable {
    public static Observable<Integer> getEmptyStream() {
        return Observable.fromIterable(List.<Integer>of())
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<CarMaker> getDuplicatedCarMakerStream() {
        return Observable.fromArray(SampleData.carMakersDuplicated)
            .doOnNext(carMaker -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %s", carMaker.name())))
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<CarMaker> getCarMakerStream() {
        return Observable.fromArray(SampleData.carMakers)
            .doOnNext(carMaker -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %s", carMaker.name())))
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<Car> getCarStream() {
        return Observable.fromIterable(SampleData.carList)
            .doOnNext(car -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %s", car.getCarName())))
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSalesOfBranchA() {
        return Observable.fromIterable(SampleData.salesOfBranchA)
            .doOnNext(sales -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %d", sales)))
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSalesOfBranchB() {
        return Observable.fromIterable(SampleData.salesOfBranchB)
            .doOnNext(sales -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %d", sales)))
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSalesOfBranchC() {
        return Observable.fromIterable(SampleData.salesOfBranchC)
            .doOnNext(sales -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %d", sales)))
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getSpeedOfSectionA() {
        return Observable.fromArray(SampleData.speedOfSectionA)
            .doOnNext(speed -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %d", speed)))
            .subscribeOn(Schedulers.computation());
    }

    public static Observable<Integer> getTemperatureOfSeoul() {
        return Observable.fromArray(SampleData.temperatureOfSeoul)
            .doOnNext(temperature -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %d", temperature)))
            .subscribeOn(Schedulers.computation());
    }
}
