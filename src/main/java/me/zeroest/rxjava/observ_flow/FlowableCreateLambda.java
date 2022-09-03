package me.zeroest.rxjava.observ_flow;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class FlowableCreateLambda {
    public static void main(String[] args) {
        Flowable<Object> flowable = Flowable.create(emitter -> {
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
        }, BackpressureStrategy.BUFFER);

        flowable.observeOn(Schedulers.computation())
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE),
                subscription -> subscription.request(Long.MAX_VALUE));

        TimeUtil.sleep(500L);
    }
}
