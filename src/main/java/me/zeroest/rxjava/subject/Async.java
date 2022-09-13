package me.zeroest.rxjava.subject;

import io.reactivex.subjects.AsyncSubject;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class Async {
    public static void main(String[] args) {
        AsyncSubject<Integer> subject = AsyncSubject.create();

        subject.onNext(1000);

        subject.doOnNext(price -> Logger.log(LogType.DO_ON_NEXT, String.format("# 소비자 1 : %d", price)))
            .subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 1 : %d", price)));
        subject.onNext(2000);

        subject.doOnNext(price -> Logger.log(LogType.DO_ON_NEXT, String.format("# 소비자 2 : %d", price)))
            .subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 2 : %d", price)));
        subject.onNext(3000);

        subject.doOnNext(price -> Logger.log(LogType.DO_ON_NEXT, String.format("# 소비자 3 : %d", price)))
            .subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 3 : %d", price)));
        subject.onNext(4000);

        subject.onComplete();

        subject.doOnNext(price -> Logger.log(LogType.DO_ON_NEXT, String.format("# 소비자 4 : %d", price)))
            .subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 4 : %d", price)));
    }
}

/*
doOnNext() | main | 01:19:59.635 | # 소비자 1 : 4000
onNext() | main | 01:19:59.678 | # 소비자 1 : 4000
doOnNext() | main | 01:19:59.679 | # 소비자 2 : 4000
onNext() | main | 01:19:59.679 | # 소비자 2 : 4000
doOnNext() | main | 01:19:59.679 | # 소비자 3 : 4000
onNext() | main | 01:19:59.680 | # 소비자 3 : 4000
doOnNext() | main | 01:19:59.681 | # 소비자 4 : 4000
onNext() | main | 01:19:59.681 | # 소비자 4 : 4000
*/
