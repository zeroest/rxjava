package me.zeroest.rxjava.operators.transforming;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.Car;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlatMap {
    public static void main(String[] args) {
        /**
         * 유형1
         * */
        Observable.just("Hello")
            .flatMap(hello ->
                Observable.just("자바", "파이썬", "안드로이드")
                .map(lang -> String.format("%s, %s", hello, lang))
            )
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        Observable.range(2, 1)
            .flatMap(num ->
                Observable.range(1, 9)
                    .map(row -> String.format("%d * %d = %d", num, row, (num * row)))
            )
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        /**
         * 유형2
         * */
        Observable.range(2, 1)
            .flatMap(num ->
                Observable.range(1, 9),
                (sourceData, transformedData) ->
                    String.format("%d * %d = %d", sourceData, transformedData, (sourceData * transformedData))
            )
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
