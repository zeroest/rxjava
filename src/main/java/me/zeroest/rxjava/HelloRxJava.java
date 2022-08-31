package me.zeroest.rxjava;

import io.reactivex.Observable;

public class HelloRxJava {

    public static void main(String[] args) {
        Observable<String> obserable = Observable.just("Hello", "RxJava");

        obserable
            .subscribe(System.out::println);
    }

}
