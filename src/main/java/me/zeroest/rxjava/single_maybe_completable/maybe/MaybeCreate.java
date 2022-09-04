package me.zeroest.rxjava.single_maybe_completable.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.DateUtil;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

public class MaybeCreate {
    public static void main(String[] args) {
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess(DateUtil.getNowDate());

//                데이터 통지 없이 완료처리
//                emitter.onComplete();

//                에러 통지
//                emitter.onError(new IllegalStateException());
            }
        });

        maybe
            .observeOn(Schedulers.computation())
            .subscribe(new MaybeObserver<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    Logger.log(LogType.ON_SUBSCRIBE);
                }

                @Override
                public void onSuccess(@NonNull String s) {
                    Logger.log(LogType.ON_SUCCESS, String.format("# date: %s", s));
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

        TimeUtil.sleep(1000L);
    }
}
