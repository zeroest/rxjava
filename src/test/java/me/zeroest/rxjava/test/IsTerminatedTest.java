package me.zeroest.rxjava.test;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsTerminatedTest {

    /**
     * 완료 통지 이벤트가 발생해서 종료 되었는지를 검증하는 예제
     * */
    @Test
    public void isTerminatedEventTest() {
        boolean result = Observable.interval(200L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .take(5)
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
            .test()
            // .take(5)에 맞춰 .awaitCount(5)로 설정시 isTerminated() 시점에 onComplete 이벤트를 받지 못하여 false 반환된다.
            .awaitCount(6)
            // 완료 또는 에러 이벤트가 발생했을 경우에 true 반환
            .isTerminated();

        assertTrue(result);
    }

    /**
     * 에러 통지 이벤트가 발생해서 종료 되었는지를 검증하는 예제
     * */
    @Test
    public void isErrorEventTest() {
        boolean result = Observable.interval(200L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .map(data -> {
                if (data == 2) {
                    throw new RuntimeException("Error happened");
                }
                return data;
            })
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
            .test()
            .awaitCount(5)
            .isTerminated();

        assertTrue(result);
    }

}
