package me.zeroest.rxjava.test.assertxxx;

import me.zeroest.rxjava.test.SampleObservable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static me.zeroest.rxjava.util.data.car.CarMaker.CHEVROLET;

public class AssertValuesTest {

    @Test
    public void getCarMakerAssertValuesTest() {
        SampleObservable.getDuplicatedCarMakerStream()
            .filter(CHEVROLET::equals)
            .test()
            .awaitDone(100L, TimeUnit.MILLISECONDS)
            .assertValues(CHEVROLET, CHEVROLET);
    }

}
