package me.zeroest.rxjava.operators.mathematicalaggregate;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class Reduce {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .reduce((x, y) -> x + y)
            .subscribe(result -> Logger.log(LogType.ON_NEXT, "# 1부터 10까지의 누적 합계: " + result));
/*
doOnNext() | main | 01:29:23.891 | 1
doOnNext() | main | 01:29:23.927 | 2
doOnNext() | main | 01:29:23.927 | 3
doOnNext() | main | 01:29:23.927 | 4
doOnNext() | main | 01:29:23.927 | 5
doOnNext() | main | 01:29:23.927 | 6
doOnNext() | main | 01:29:23.927 | 7
doOnNext() | main | 01:29:23.927 | 8
doOnNext() | main | 01:29:23.928 | 9
doOnNext() | main | 01:29:23.928 | 10
onNext() | main | 01:29:23.933 | # 1부터 10까지의 누적 합계: 55
*/

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .reduce(0, (x, y) -> {
                Logger.log(LogType.PRINT, "# reduce 입력 값 : " + x + ", " + y);
                return x + y;
            })
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
print() | main | 01:29:58.475 | # reduce 입력 값 : 0, 1
print() | main | 01:29:58.475 | # reduce 입력 값 : 1, 2
print() | main | 01:29:58.475 | # reduce 입력 값 : 3, 3
print() | main | 01:29:58.475 | # reduce 입력 값 : 6, 4
print() | main | 01:29:58.475 | # reduce 입력 값 : 10, 5
print() | main | 01:29:58.475 | # reduce 입력 값 : 15, 6
print() | main | 01:29:58.475 | # reduce 입력 값 : 21, 7
print() | main | 01:29:58.475 | # reduce 입력 값 : 28, 8
print() | main | 01:29:58.475 | # reduce 입력 값 : 36, 9
print() | main | 01:29:58.476 | # reduce 입력 값 : 45, 10
onNext() | main | 01:29:58.476 | 55
*/

        Observable.just("a", "b", "c", "d", "e")
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .reduce((x, y) -> "(" + x + ", " + y + ")")
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 01:31:41.850 | ((((a, b), c), d), e)
*/
    }
}
