package me.zeroest.rxjava.subject;

import io.reactivex.subjects.PublishSubject;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class Publish {
    public static void main(String[] args) {
        PublishSubject<Integer> subject = PublishSubject.create();

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 1 : %d", price)));
        subject.onNext(3500);
        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 2 : %d", price)));
        subject.onNext(3000);
        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 3 : %d", price)));
        subject.onNext(2500);

        subject.subscribe(
            price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 4 : %d", price)),
            error -> Logger.log(LogType.ON_ERROR, error),
            () -> Logger.log(LogType.ON_COMPLETE)
        );

        subject.onComplete();
    }
}

/*
onNext() | main | 01:07:33.647 | # 소비자 1 : 3500
onNext() | main | 01:07:33.703 | # 소비자 1 : 3000
onNext() | main | 01:07:33.703 | # 소비자 2 : 3000
onNext() | main | 01:07:33.704 | # 소비자 1 : 2500
onNext() | main | 01:07:33.704 | # 소비자 2 : 2500
onNext() | main | 01:07:33.704 | # 소비자 3 : 2500
onComplete() | main | 01:08:24.652
*/
