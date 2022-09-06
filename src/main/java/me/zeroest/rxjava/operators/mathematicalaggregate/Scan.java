package me.zeroest.rxjava.operators.mathematicalaggregate;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

/**
 * scan()
 * - 집계 중간 결과를 계속해서 출력한다.
 * */
public class Scan {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .scan((x, y) -> x + y)
            .subscribe(result -> Logger.log(LogType.ON_NEXT, "# 1부터 10까지의 누적 합계: " + result));
/*
doOnNext() | main | 01:33:29.384 | 1
onNext() | main | 01:33:29.421 | # 1부터 10까지의 누적 합계: 1
doOnNext() | main | 01:33:29.422 | 2
onNext() | main | 01:33:29.422 | # 1부터 10까지의 누적 합계: 3
doOnNext() | main | 01:33:29.422 | 3
onNext() | main | 01:33:29.422 | # 1부터 10까지의 누적 합계: 6
doOnNext() | main | 01:33:29.422 | 4
onNext() | main | 01:33:29.422 | # 1부터 10까지의 누적 합계: 10
doOnNext() | main | 01:33:29.422 | 5
onNext() | main | 01:33:29.422 | # 1부터 10까지의 누적 합계: 15
doOnNext() | main | 01:33:29.422 | 6
onNext() | main | 01:33:29.422 | # 1부터 10까지의 누적 합계: 21
doOnNext() | main | 01:33:29.422 | 7
onNext() | main | 01:33:29.422 | # 1부터 10까지의 누적 합계: 28
doOnNext() | main | 01:33:29.423 | 8
onNext() | main | 01:33:29.423 | # 1부터 10까지의 누적 합계: 36
doOnNext() | main | 01:33:29.423 | 9
onNext() | main | 01:33:29.423 | # 1부터 10까지의 누적 합계: 45
doOnNext() | main | 01:33:29.423 | 10
onNext() | main | 01:33:29.423 | # 1부터 10까지의 누적 합계: 55
*/

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .scan(0, (x, y) -> x + y)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 01:34:21.869 | 0
onNext() | main | 01:34:21.869 | 1
onNext() | main | 01:34:21.869 | 3
onNext() | main | 01:34:21.869 | 6
onNext() | main | 01:34:21.869 | 10
onNext() | main | 01:34:21.869 | 15
onNext() | main | 01:34:21.869 | 21
onNext() | main | 01:34:21.870 | 28
onNext() | main | 01:34:21.870 | 36
onNext() | main | 01:34:21.870 | 45
onNext() | main | 01:34:21.870 | 55
*/

        Observable.just("a", "b", "c", "d", "e")
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .scan((x, y) -> "(" + x + ", " + y + ")")
            .subscribe(result -> Logger.log(LogType.ON_NEXT, "# 출력 결과: " + result));
/*
onNext() | main | 01:34:59.944 | # 출력 결과: a
onNext() | main | 01:34:59.953 | # 출력 결과: (a, b)
onNext() | main | 01:34:59.953 | # 출력 결과: ((a, b), c)
onNext() | main | 01:34:59.953 | # 출력 결과: (((a, b), c), d)
onNext() | main | 01:34:59.953 | # 출력 결과: ((((a, b), c), d), e)
*/
    }
}
