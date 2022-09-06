package me.zeroest.rxjava.operators.errorhandling;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * onErrorReturn() 사용해 예외 발생 시, 우리가 원하는 값을 전달할 수 있다.
 * - 예외가 발생될 가능성이 있는 부분에 대해서 사전에 처리를 선언할 수 있다.
 * - 소비자가 예상되는 예외를 모두 사전에 알리고 처리하긴 힘들기 때문에
 *   생산자쪽에서 예외 처리를 사전에 해두고
 *   소비자는 선언된 예외 상황을 보고 그에 맞는 적절한 처리를 할 수 있다.
 * */
public class OnErrorReturn {
    public static void main(String[] args) {
        Observable
            .just(5)
            .flatMap(num ->
                Observable
                    .interval(200L, TimeUnit.MILLISECONDS)
                    .take(5)
                    .map(i -> num / i)
                    .onErrorReturn(exception -> {
                        if (exception instanceof ArithmeticException) {
                            Logger.log(LogType.PRINT, String.format("계산 처리 에러 발생: %s", exception.getMessage()));
                        }

                        return -1L;
                    })
            )
            .subscribe(
                data -> {
                    if (data < 0) {
                        Logger.log(LogType.PRINT, String.format("# 예외를 알리는 데이터: %d", data));
                    } else {
                        Logger.log(LogType.ON_NEXT, data);
                    }
                },
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );

        TimeUtil.sleep(1000L);
    }
}
