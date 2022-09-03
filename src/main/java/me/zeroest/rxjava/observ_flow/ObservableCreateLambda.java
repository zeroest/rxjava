package me.zeroest.rxjava.observ_flow;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class ObservableCreateLambda {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(emitter -> {
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
        });

        observable.observeOn(Schedulers.computation())
            .subscribe(new Observer<>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
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
