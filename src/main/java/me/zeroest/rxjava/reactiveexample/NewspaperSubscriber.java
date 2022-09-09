package me.zeroest.rxjava.reactiveexample;

import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class NewspaperSubscriber<T> implements Subscriber<T> {

    private Subscription s;

    // BackPressure - 소비자가 한번에 처리할 수 있는 개수를 요청
    private static final long BACKPRESSURE_COUNT = 3;
    private long bufferSize = BACKPRESSURE_COUNT;

    @Override
    public void onSubscribe(Subscription s) {
        Logger.log(LogType.PRINT, "4. 구독 정보 구독완료");
        this.s = s;

        Logger.log(LogType.PRINT, String.format("5. 신문 %d개씩 매일 요청", BACKPRESSURE_COUNT));
        s.request(BACKPRESSURE_COUNT);
    }

    @Override
    public void onNext(T data) {
        Logger.log(LogType.ON_NEXT, "구독 데이터 통지 - data: " + data);

        bufferSize--;
        if (bufferSize == 0) {
            Logger.log(LogType.ON_NEXT, "하루 지남");
            bufferSize = BACKPRESSURE_COUNT;
            s.request(BACKPRESSURE_COUNT);
        }
    }

    @Override
    public void onError(Throwable t) {
        Logger.log(LogType.ON_ERROR, t);
    }

    @Override
    public void onComplete() {
        Logger.log(LogType.ON_COMPLETE, "구독 완료");
    }
}
