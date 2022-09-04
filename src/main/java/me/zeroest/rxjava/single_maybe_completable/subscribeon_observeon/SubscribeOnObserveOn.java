package me.zeroest.rxjava.single_maybe_completable.subscribeon_observeon;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class SubscribeOnObserveOn {
    public static void main(String[] args) {
        List<MyShape> shapes = new ArrayList<>();
        shapes.add(new MyShape("Red","Ball"));
        shapes.add(new MyShape("Green","Ball"));
        shapes.add(new MyShape("Blue","Ball"));

        Observable.fromIterable(shapes)
            .subscribeOn(Schedulers.computation())             // (A)
            .subscribeOn(Schedulers.io())                      // (B)
            // 1. 현재 스레드(main)에서 Observable을 구독
            .doOnSubscribe(disposable -> MyUtil.printData("doOnSubscribe"))
            // 2. (A)에 의해 computation 스케줄러에서 데이터 흐름 발생, (B)는 영향 X
            .doOnNext(data -> MyUtil.printData("doOnNext", data))
            // 3. (C)에 의해 map 연산이 new thread에서 실행
            .observeOn(Schedulers.newThread())                  // (C)
            .map(data -> {data.setShape("Square"); return data;})
            .doOnNext(data -> MyUtil.printData("map(Square)", data))
            // 4. (D)에 의해 map 연산이 new thread에서 실행
            .observeOn(Schedulers.newThread())                  // (D)
            .map(data -> {data.setShape("Triangle"); return data;})
            .doOnNext(data -> MyUtil.printData("map(Triangle)", data))
            // 5. (E)에 의해 new thread에서 데이터 소비(subscribe)
            .observeOn(Schedulers.newThread())                  // (E)
            .subscribe(data -> MyUtil.printData("subscribe", data));

        TimeUtil.sleep(1000L);
    }
}

/*
main | doOnSubscribe |
RxComputationThreadPool-1 | doOnNext | MyShape{color='Red', shape='Ball'}
RxComputationThreadPool-1 | doOnNext | MyShape{color='Green', shape='Ball'}
RxComputationThreadPool-1 | doOnNext | MyShape{color='Blue', shape='Ball'}
RxNewThreadScheduler-1 | map(Square) | MyShape{color='Red', shape='Square'}
RxNewThreadScheduler-1 | map(Square) | MyShape{color='Green', shape='Square'}
RxNewThreadScheduler-1 | map(Square) | MyShape{color='Blue', shape='Square'}
RxNewThreadScheduler-2 | map(Triangle) | MyShape{color='Red', shape='Triangle'}
RxNewThreadScheduler-2 | map(Triangle) | MyShape{color='Green', shape='Triangle'}
RxNewThreadScheduler-3 | subscribe | MyShape{color='Red', shape='Triangle'}
RxNewThreadScheduler-2 | map(Triangle) | MyShape{color='Blue', shape='Triangle'}
RxNewThreadScheduler-3 | subscribe | MyShape{color='Green', shape='Triangle'}
RxNewThreadScheduler-3 | subscribe | MyShape{color='Blue', shape='Triangle'}
*/
