package me.zeroest.rxjava.test.blockingxxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.test.SampleObservable;
import me.zeroest.rxjava.util.data.car.Car;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BlockingLastTest {

    @Test
    public void getCarStreamLastTest() {
        /*
        new Car(CarMaker.CHEVROLET, "말리부", CarType.SEDAN, 23_000_000),
        new Car(CarMaker.HYUNDAE, "쏘렌토", CarType.SUV, 33_000_000),
        new Car(CarMaker.CHEVROLET, "트래버스", CarType.SUV, 50_000_000),
        new Car(CarMaker.HYUNDAE, "팰리세이드", CarType.SEDAN, 28_000_000),
        new Car(CarMaker.CHEVROLET, "트랙스", CarType.SUV, 18_000_000),
        new Car(CarMaker.SSANGYOUNG, "티볼리", CarType.SUV, 23_000_000),
        new Car(CarMaker.SAMSUNG, "SM6", CarType.SUV, 40_000_000),
        new Car(CarMaker.SSANGYOUNG, "G4렉스턴", CarType.SUV, 43_000_000),
        new Car(CarMaker.SAMSUNG, "SM5", CarType.SEDAN, 35_000_000)
        */

        // given
        Observable<Car> observable = SampleObservable.getCarStream();

        // when
        Car car = observable.blockingLast();
        String carName = car.getCarName();

        // then
        assertEquals(carName, "SM5");
    }

    @Test
    public void getSalesOfBranchALastTest() {
        /*
        15_000_000, 25_000_000, 10_000_000, 35_000_000, 23_000_000, 40_000_000, 50_000_000, 45_000_000,
        35_000_000, 23_000_000, 15_000_000, 10_000_000
        */

        // given
        Observable<Integer> observable = SampleObservable.getSalesOfBranchA();

        // when
        Integer sales = observable
            .take(6)
            .blockingLast();

        // then
        assertEquals(sales, 40_000_000);
    }

    @Test
    public void getEmptyLastTest() {
        // given
        Observable<Integer> observable = SampleObservable.getEmptyStream();

        // when
        assertThrows(NoSuchElementException.class, () ->
            observable
                .blockingLast()
        );
    }

}