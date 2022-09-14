package me.zeroest.rxjava.do_xxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class DoOnSubscribe {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
            .doOnSubscribe(disposable -> Logger.log(LogType.DO_ON_SUBSCRIBE, "# 생산자: 구독 처리 준비 완료"))
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE),
                disposable -> Logger.log(LogType.ON_SUBSCRIBE, "# 소비자: 구독 처리 준비 완료 알림 받음")
            );
    }
}

/*
doOnSubscribe() | main | 01:19:31.734 | # 생산자: 구독 처리 준비 완료
onSubscribe() | main | 01:19:31.787 | # 소비자: 구독 처리 준비 완료 알림 받음
onNext() | main | 01:19:31.787 | 1
onNext() | main | 01:19:31.787 | 2
onNext() | main | 01:19:31.787 | 3
onNext() | main | 01:19:31.788 | 4
onNext() | main | 01:19:31.788 | 5
onNext() | main | 01:19:31.788 | 6
onNext() | main | 01:19:31.788 | 7
onComplete() | main | 01:19:31.788
*/
