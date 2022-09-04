package me.zeroest.rxjava.operators.transforming;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.Car;
import me.zeroest.rxjava.util.data.car.CarMaker;

public class GroupBy {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
            Observable
                .fromIterable(SampleData.carList)
                .groupBy(Car::getCarMaker);

        observable
//            .observeOn(Schedulers.computation())
            .subscribe(groupedObservable ->
                groupedObservable.subscribe(
                    car -> Logger.log(LogType.ON_NEXT, String.format(
                        "Group: %s\t\tCarName: %s", groupedObservable.getKey(), car.getCarName()
                    ))
                )
            );
/*
onNext() | main | 02:08:54.796 | Group: CHEVROLET		CarName: 말리부
onNext() | main | 02:08:54.854 | Group: HYUNDAE		CarName: 쏘렌토
onNext() | main | 02:08:54.854 | Group: CHEVROLET		CarName: 트래버스
onNext() | main | 02:08:54.855 | Group: HYUNDAE		CarName: 팰리세이드
onNext() | main | 02:08:54.855 | Group: CHEVROLET		CarName: 트랙스
onNext() | main | 02:08:54.855 | Group: SSANGYOUNG		CarName: 티볼리
onNext() | main | 02:08:54.855 | Group: SAMSUNG		CarName: SM6
onNext() | main | 02:08:54.856 | Group: SSANGYOUNG		CarName: G4렉스턴
onNext() | main | 02:08:54.856 | Group: SAMSUNG		CarName: SM5
*/

        // =============================================================================

        observable
            .observeOn(Schedulers.computation())
            .subscribe(groupedObservable ->
                groupedObservable.subscribe(
                    car -> Logger.log(LogType.ON_NEXT, String.format(
                        "Group: %s\t\tCarName: %s", groupedObservable.getKey(), car.getCarName()
                    ))
                )
            );

        TimeUtil.sleep(2000L);
/*
onNext() | RxComputationThreadPool-1 | 02:01:42.242 | Group: CHEVROLET		CarName: 말리부
onNext() | RxComputationThreadPool-1 | 02:01:42.274 | Group: CHEVROLET		CarName: 트래버스
onNext() | RxComputationThreadPool-1 | 02:01:42.274 | Group: CHEVROLET		CarName: 트랙스
onNext() | RxComputationThreadPool-1 | 02:01:42.274 | Group: HYUNDAE		CarName: 쏘렌토
onNext() | RxComputationThreadPool-1 | 02:01:42.274 | Group: HYUNDAE		CarName: 팰리세이드
onNext() | RxComputationThreadPool-1 | 02:01:42.274 | Group: SSANGYOUNG		CarName: 티볼리
onNext() | RxComputationThreadPool-1 | 02:01:42.275 | Group: SSANGYOUNG		CarName: G4렉스턴
onNext() | RxComputationThreadPool-1 | 02:01:42.275 | Group: SAMSUNG		CarName: SM6
onNext() | RxComputationThreadPool-1 | 02:01:42.275 | Group: SAMSUNG		CarName: SM5
*/
    }
}
