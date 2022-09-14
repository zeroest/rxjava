package me.zeroest.rxjava.do_xxx;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;
import me.zeroest.rxjava.util.data.car.CarMaker;

import java.util.concurrent.TimeUnit;

public class DoOnDispose {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakers)
            .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (carMaker, num) -> carMaker)
            .doOnDispose(() -> Logger.log(LogType.DO_ON_DISPOSE, "# 생산자: 구독 해지 완료"))
            .subscribe(new Observer<>() {
                private Disposable disposable;
                private long startTime;

                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    this.disposable = d;
                    this.startTime = TimeUtil.start();
                }

                @Override
                public void onNext(@NonNull CarMaker carMaker) {
                    Logger.log(LogType.ON_NEXT, carMaker);

                    if (TimeUtil.getCurrentTime() - startTime > 1000L) {
                        Logger.log(LogType.PRINT, "# 소비자: 구독해지, 1000L 초과");
                        disposable.dispose();
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Logger.log(LogType.ON_ERROR, e);
                }

                @Override
                public void onComplete() {
                    Logger.log(LogType.ON_COMPLETE);
                }
            });

        TimeUtil.sleep(2000L);
    }
}

/*
onNext() | RxComputationThreadPool-1 | 02:28:18.045 | CHEVROLET
onNext() | RxComputationThreadPool-1 | 02:28:18.335 | HYUNDAE
onNext() | RxComputationThreadPool-1 | 02:28:18.635 | SAMSUNG
onNext() | RxComputationThreadPool-1 | 02:28:18.935 | SSANGYOUNG
print() | RxComputationThreadPool-1 | 02:28:18.936 | # 소비자: 구독해지, 1000L 초과
doOnDispose() | RxComputationThreadPool-1 | 02:28:18.937 | # 생산자: 구독 해지 완료
*/
