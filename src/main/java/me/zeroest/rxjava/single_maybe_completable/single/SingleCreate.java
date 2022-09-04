package me.zeroest.rxjava.single_maybe_completable.single;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.DateUtil;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class SingleCreate {
    public static void main(String[] args) {
        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess(DateUtil.getNowDate());
            }
        });

        single
            .observeOn(Schedulers.computation())
            .subscribe(new SingleObserver<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

                @Override
                public void onSuccess(@NonNull String s) {
                    Logger.log(LogType.ON_SUCCESS, String.format("# date: %s", s));
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Logger.log(LogType.ON_ERROR, e);
                }
            });

        TimeUtil.sleep(500L);
    }
}
