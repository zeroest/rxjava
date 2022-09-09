package me.zeroest.rxjava.reactiveexample;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

/**
 * 구독 정보(구독자, 어떤 데이터를 구독할지)
 * */
public class NewspaperSubscription<T> implements Subscription {

    private Subscriber<T> s;
    private Iterator<T> it;

    public NewspaperSubscription(Subscriber<T> s, Iterable<T> its) {
        this.s = s;
        this.it = its.iterator();
    }

    @Override
    public void request(long n) {
        while (n > 0) {
            if (it.hasNext()) {
                s.onNext(it.next());
            } else {
                s.onComplete();
                break;
            }
            n--;
        }
    }

    @Override
    public void cancel() {
    }
}
