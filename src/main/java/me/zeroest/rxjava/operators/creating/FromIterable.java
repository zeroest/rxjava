package me.zeroest.rxjava.operators.creating;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

import java.util.Arrays;
import java.util.List;

public class FromIterable {
    public static void main(String[] args) {
        List<String> countries = Arrays.asList("Korea", "Canada", "USA", "Italy");

        Observable
            .fromIterable(countries)
            .subscribe(country -> Logger.log(LogType.ON_NEXT, country));
    }
}
