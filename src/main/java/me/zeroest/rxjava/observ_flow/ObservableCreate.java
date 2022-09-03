package me.zeroest.rxjava.observ_flow;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class ObservableCreate {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                String[] datas = {"Hello", "RxJava!"};
                for (String data : datas) {
                    // 구독이 해지되면 처리 중단
                    if (emitter.isDisposed()) {
                        return;
                    }

                    // 데이터 통지
                    emitter.onNext(data);
                }

                // 데이터 통지 완료를 알린다.
                emitter.onComplete();
            }
        });

        observable.observeOn(Schedulers.computation())
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE),
                disposable -> {});

        TimeUtil.sleep(500L);
    }
}
