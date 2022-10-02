package me.zeroest.rxjava.test.assertxxx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.test.SampleObservable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.data.car.CarMaker;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class AssertValueTest {

    @Test
    public void assertValueTest() {
        Observable.just("a")
            .doOnNext(value -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %s", value)))
            .subscribeOn(Schedulers.newThread())
            .test()
            .awaitDone(100L, TimeUnit.MILLISECONDS)
            .assertValue("a");
    }

    @Test
    public void getCarMakerAssertValueTest() {
        SampleObservable.getCarMakerStream()
            .filter(CarMaker.SAMSUNG::equals)
            .test()
            .awaitDone(100L, TimeUnit.MILLISECONDS)
            .assertValue(CarMaker.SAMSUNG);
    }

}
