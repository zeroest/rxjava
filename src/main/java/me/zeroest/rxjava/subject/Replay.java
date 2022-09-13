package me.zeroest.rxjava.subject;

import io.reactivex.subjects.ReplaySubject;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class Replay {
    public static void main(String[] args) {
        ReplaySubject<Integer> subject = ReplaySubject.createWithSize(2);

        subject.onNext(2000);
        subject.onNext(3000);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 1 : %d", price)));
        subject.onNext(4500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 2 : %d", price)));
        subject.onNext(5500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 3 : %d", price)));
        subject.onNext(6500);

        subject.onComplete();

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 4 : %d", price)));
    }
}

/*
onNext() | main | 01:38:34.240 | # 소비자 1 : 2000
onNext() | main | 01:38:34.280 | # 소비자 1 : 3000
onNext() | main | 01:38:34.280 | # 소비자 1 : 4500
onNext() | main | 01:38:34.281 | # 소비자 2 : 3000
onNext() | main | 01:38:34.281 | # 소비자 2 : 4500
onNext() | main | 01:38:34.281 | # 소비자 1 : 5500
onNext() | main | 01:38:34.281 | # 소비자 2 : 5500
onNext() | main | 01:38:34.282 | # 소비자 3 : 4500
onNext() | main | 01:38:34.282 | # 소비자 3 : 5500
onNext() | main | 01:38:34.282 | # 소비자 1 : 6500
onNext() | main | 01:38:34.282 | # 소비자 2 : 6500
onNext() | main | 01:38:34.282 | # 소비자 3 : 6500
onNext() | main | 01:38:34.283 | # 소비자 4 : 5500
onNext() | main | 01:38:34.284 | # 소비자 4 : 6500
*/
