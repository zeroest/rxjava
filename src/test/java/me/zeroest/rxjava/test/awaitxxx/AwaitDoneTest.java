package me.zeroest.rxjava.test.awaitxxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class AwaitDoneTest {

    /**
     * 지정된 시간까지 완료 통지가 없이, 해당 시점까지 전달 받은 데이터의 개수가 맞는지 검증하는 예제
     * */
    @Test
    public void awaitDoneTest01() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .take(5)
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
            .test()
            .awaitDone(500L, TimeUnit.MILLISECONDS)
            .assertNotComplete()
            .assertValueCount(2);
    }

    /**
     * 지정된 시간 전에 완료 통지가 있어, 완료 통지 시점까지만 대기하고 전달 받은 데이터의 개수가 맞는지 검증하는 예제
     * */
    @Test
    public void awaitDoneTest02() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .take(5)
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
            .test()
            .awaitDone(1500L, TimeUnit.MILLISECONDS)
            .assertComplete()
            .assertValueCount(5);
    }

}
