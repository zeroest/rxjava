package me.zeroest.rxjava.do_xxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class DoOnComplete {
    public static void main(String[] args) {
        Observable.range(1, 5)
            .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# 생산자: 데이터 통지 완료"))
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );
    }
}

/*
onNext() | main | 01:53:15.465 | 1
onNext() | main | 01:53:15.517 | 2
onNext() | main | 01:53:15.517 | 3
onNext() | main | 01:53:15.517 | 4
onNext() | main | 01:53:15.517 | 5
doOnComplete() | main | 01:53:15.518 | # 생산자: 데이터 통지 완료
onComplete() | main | 01:53:15.518
*/
