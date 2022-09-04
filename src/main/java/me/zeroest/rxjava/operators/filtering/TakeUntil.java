package me.zeroest.rxjava.operators.filtering;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.Car;

import java.util.concurrent.TimeUnit;

public class TakeUntil {
    public static void main(String[] args) {
        /**
         * 유형1
         * */
        Observable.fromIterable(SampleData.carList)
            .takeUntil((Car car) -> car.getCarName().equals("트랙스"))
            .subscribe(car -> Logger.log(LogType.ON_NEXT,
                String.format("%s : %s : %d", car.getCarMaker().name(), car.getCarName(), car.getCarPrice())
            ));
/*
onNext() | main | 23:41:58.774 | CHEVROLET : 말리부 : 23000000
onNext() | main | 23:41:58.812 | HYUNDAE : 쏘렌토 : 33000000
onNext() | main | 23:41:58.812 | CHEVROLET : 트래버스 : 50000000
onNext() | main | 23:41:58.812 | HYUNDAE : 팰리세이드 : 28000000
onNext() | main | 23:41:58.812 | CHEVROLET : 트랙스 : 18000000
*/

        /**
         * 유형2
         *
         * 파라미터로 받는 Flowable/Observable이 최초로 데이터를 발행할 때까지 계속 데이터를 발행
         * timer와 함께 사용하여 특정 시점이 되기전까지 데이터를 발행하는데 활용하기 용이
         * */
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
            .takeUntil(Observable.timer(5500L, TimeUnit.MILLISECONDS))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | RxComputationThreadPool-2 | 23:44:55.335 | 0
onNext() | RxComputationThreadPool-2 | 23:44:56.334 | 1
onNext() | RxComputationThreadPool-2 | 23:44:57.334 | 2
onNext() | RxComputationThreadPool-2 | 23:44:58.334 | 3
onNext() | RxComputationThreadPool-2 | 23:44:59.334 | 4
*/

        TimeUtil.sleep(6000L);
    }
}
