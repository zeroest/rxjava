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

/*
print() | main | 01:36:36.621 | 1. 신문사에 구독 요청
print() | main | 01:36:36.672 | 2. 구독정보 만드는 중
print() | main | 01:36:36.672 | 3. 구독정보 생성 완료
print() | main | 01:36:36.672 | 4. 구독 정보 구독완료
print() | main | 01:36:36.677 | 5. 신문 3개씩 매일 요청
onNext() | main | 01:36:36.683 | 구독 데이터 통지 - data: 1
onNext() | main | 01:36:36.683 | 구독 데이터 통지 - data: 2
onNext() | main | 01:36:36.683 | 구독 데이터 통지 - data: 3
onNext() | main | 01:36:36.684 | 하루 지남
onNext() | main | 01:36:36.684 | 구독 데이터 통지 - data: 4
onNext() | main | 01:36:36.684 | 구독 데이터 통지 - data: 5
onNext() | main | 01:36:36.684 | 구독 데이터 통지 - data: 6
onNext() | main | 01:36:36.684 | 하루 지남
onNext() | main | 01:36:36.684 | 구독 데이터 통지 - data: 7
onNext() | main | 01:36:36.684 | 구독 데이터 통지 - data: 8
onNext() | main | 01:36:36.684 | 구독 데이터 통지 - data: 9
onNext() | main | 01:36:36.684 | 하루 지남
onNext() | main | 01:36:36.685 | 구독 데이터 통지 - data: 10
onComplete() | main | 01:36:36.685 | 구독 완료
*/