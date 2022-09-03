package me.zeroest.rxjava.observ_flow;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableCreate {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Exception {
                String[] datas = {"Hello", "world"};
                for (String data : datas) {
                    // 구독이 해지되면 처리 중단
                    if (emitter.isCancelled()) {
                        return;
                    }

                    // 데이터 통지
                    emitter.onNext(data);
                }

                // 데이터 통지 완료를 알린다.
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        flowable.observeOn(Schedulers.computation())
            .subscribe(new Subscriber<>() {
                // 데이터 개수 요청 및 구독을 취소하기 위한 Subscription 객체
                private Subscription subscription;

                @Override
                public void onSubscribe(Subscription s) {
                    this.subscription = s;
                    this.subscription.request(Long.MAX_VALUE);
                }

                @Override
                public void onNext(String s) {
                    Logger.log(LogType.ON_NEXT, s);
                }

                @Override
                public void onError(Throwable t) {
                    Logger.log(LogType.ON_ERROR, t);
                }

                @Override
                public void onComplete() {
                    Logger.log(LogType.ON_COMPLETE);
                }
            });

        TimeUtil.sleep(500L);
    }
}
