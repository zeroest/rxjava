package me.zeroest.rxjava.operators.transforming;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

import java.util.Arrays;
import java.util.List;

public class Map {
    public static void main(String[] args) {
        List<Integer> oddList = Arrays.asList(1, 3, 5, 7);
        Observable.fromIterable(oddList)
            .map(num -> String.format("1을 더한 결과: %d", (num + 1)))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        Observable.just("korea", "america", "canada", "paris", "japan", "china")
            .filter(country -> country.length() == 5)
            .map(country -> country.toUpperCase().charAt(0) + country.substring(1))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
