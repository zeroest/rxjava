package me.zeroest.rxjava.reactiveexample;

import org.reactivestreams.Subscriber;

import java.util.Arrays;
import java.util.List;

public class ReactiveExampleApp {
    public static void main(String[] args) {
        NewspaperPublisher<Integer> pub = new NewspaperPublisher<>(); // 신문사
        Subscriber<Integer> sub = new NewspaperSubscriber<>(); // 구독자

        List<Integer> its = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        pub
            .fromIterable(its)
            .subscribe(sub);

    }
}
