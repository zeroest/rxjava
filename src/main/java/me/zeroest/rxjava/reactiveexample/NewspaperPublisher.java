package me.zeroest.rxjava.reactiveexample;

import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class NewspaperPublisher<T> implements Publisher<T> {

    Iterable<T> its;

    public NewspaperPublisher fromIterable(Iterable<T> its) {
        this.its = its;
        return this;
    }

    @Override
    public void subscribe(Subscriber<? super T> s) {
        Logger.log(LogType.PRINT, "1. 신문사에 구독 요청");
        Logger.log(LogType.PRINT, "2. 구독정보 만드는 중");
        Subscription subscription = new NewspaperSubscription<>((Subscriber<T>) s, its);
        Logger.log(LogType.PRINT, "3. 구독정보 생성 완료");
        s.onSubscribe(subscription);
    }
}
