package me.zeroest.rxjava.do_xxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class DoOnEach {
    public static void main(String[] args) {
        Observable.range(1, 5)
            .doOnEach(notification -> {
                if (notification.isOnNext()) {
                    Logger.log(LogType.DO_ON_NEXT, String.format("# 생산자: 데이터 통지 - %d", notification.getValue()));
                } else if (notification.isOnError()) {
                    Logger.log(LogType.DO_ON_ERROR, String.format("# 생산자: 에러 발생 - %s", notification.getError().getMessage()));
                } else if (notification.isOnComplete()) {
                    Logger.log(LogType.DO_ON_COMPLETE, "# 생산자: 데이터 통지 완료");
                }
            })
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
            );
    }
}

/*
doOnNext() | main | 02:13:57.134 | # 생산자: 데이터 통지 - 1
onNext() | main | 02:13:57.180 | 1
doOnNext() | main | 02:13:57.180 | # 생산자: 데이터 통지 - 2
onNext() | main | 02:13:57.180 | 2
doOnNext() | main | 02:13:57.180 | # 생산자: 데이터 통지 - 3
onNext() | main | 02:13:57.181 | 3
doOnNext() | main | 02:13:57.181 | # 생산자: 데이터 통지 - 4
onNext() | main | 02:13:57.181 | 4
doOnNext() | main | 02:13:57.181 | # 생산자: 데이터 통지 - 5
onNext() | main | 02:13:57.181 | 5
doOnComplete() | main | 02:13:57.181 | # 생산자: 데이터 통지 완료
onComplete() | main | 02:13:57.181
*/
