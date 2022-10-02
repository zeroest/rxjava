package me.zeroest.rxjava.test.assertxxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class AssertNoValuesTest {

    @Test
    public void assertNoValuesTest() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
            .doOnNext(value -> Logger.log(LogType.DO_ON_NEXT, String.format("# Init doOnNext : %d", value)))
            .filter(value -> value > 5)
            .test()
            .awaitDone(1000L, TimeUnit.MILLISECONDS)
            .assertNoValues();
    }
    /*
    doOnNext() | RxComputationThreadPool-1 | 15:41:59.836 | # Init doOnNext : 0
    doOnNext() | RxComputationThreadPool-1 | 15:42:00.024 | # Init doOnNext : 1
    doOnNext() | RxComputationThreadPool-1 | 15:42:00.225 | # Init doOnNext : 2
    doOnNext() | RxComputationThreadPool-1 | 15:42:00.425 | # Init doOnNext : 3
    doOnNext() | RxComputationThreadPool-1 | 15:42:00.625 | # Init doOnNext : 4
    */

}
