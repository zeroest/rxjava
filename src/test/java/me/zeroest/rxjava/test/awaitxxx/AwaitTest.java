package me.zeroest.rxjava.test.awaitxxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AwaitTest {

    /**
     * 생산자쪽에서 완료 통지를 보낼때까지 대기한 후, 완료 및 통지된 데이터 개수를 검증하는 예제
     */
    @Test
    public void awaitTest01() throws InterruptedException {
        Observable.interval(100L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .take(5)
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
            .test()
            .await()
            .assertComplete()
            .assertValueCount(5);
    }

    /**
     * 지정한 시간동안 대기하면서 대기 시간내에 완료 통지를 받았는지 여부를 검증하는 예제
     */
    @Test
    public void awaitTest02() throws InterruptedException {
        boolean result = Observable.interval(1000L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .take(5)
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
            .test()
            .await(2000L, TimeUnit.MILLISECONDS);

        assertFalse(result);
    }

}
