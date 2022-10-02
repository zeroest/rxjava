package me.zeroest.rxjava.test.assertxxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertResult를 사용하여 통지 완료 후, 통지된 데이터와 파라미터로 입력된 데이터의 값과 순서가 일치하는지 검증하는 예제
 * */
public class AssertResultTest {

    /**
     * 값과 순서에는 문제가 없으나 완료 통지가 없으므로 Exception 발생
     * */
    @Test
    public void assertResultFailTest() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
            .doOnNext(value -> Logger.log(LogType.ON_NEXT, value))
            .filter(value -> value > 3)
            .test()
            .awaitDone(1100L, TimeUnit.MILLISECONDS)
            .assertResult(4L);
    }
    /*
    java.lang.AssertionError: Not completed (latch = 1, values = 1, errors = 0, completions = 0, timeout!, disposed!)
    */

    @Test
    public void assertResultTest() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
            .doOnNext(value -> Logger.log(LogType.ON_NEXT, value))
            .take(5)
            .filter(value -> value > 3)
            .test()
            .awaitDone(1100L, TimeUnit.MILLISECONDS)
            .assertResult(4L);
    }

}
