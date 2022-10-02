package me.zeroest.rxjava.test.assertxxx;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import me.zeroest.rxjava.test.SampleObservable;
import me.zeroest.rxjava.util.data.car.Car;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

//import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * assertEmpty 사용하여 해당 시점까지 통지된 데이터가 있는지 검증하는 예제
 * */
class AssertEmptyTest {

    @Test
    public void getCarStreamEmptyFailTest() {
        // given
        Observable<Car> observable = SampleObservable.getCarStream();

        // when
        TestObserver<Car> observer = observable.test();

        // then
//        assertThrows(
//            AssertionError.class,
//            () -> observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty()
//        );
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
        /*
        java.lang.AssertionError: Value counts differ; expected: 0 but was: 9 (latch = 0, values = 9, errors = 0, completions = 1)
        Expected :0
        Actual   :9 (latch = 0, values = 9, errors = 0, completions = 1)
        */
    }

    /**
     * 1초 동안 딜레이가 걸려 있기 때문에
     * await 0.1 초 동안 대기하며 생산자에서 통지되는 데이터가 assertEmpty 보장
     * */
    @Test
    public void getCarStreamEmptyTest() {
        // given
        Observable<Car> observable = SampleObservable.getCarStream();

        // when
        TestObserver<Car> observer = observable.delay(1000L, TimeUnit.MILLISECONDS).test();

        // then
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
    }

}
