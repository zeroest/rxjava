package me.zeroest.rxjava.subject;

import io.reactivex.subjects.BehaviorSubject;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class Behavior {
    public static void main(String[] args) {
        BehaviorSubject<Integer> subject = BehaviorSubject.createDefault(2000);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 1 : %d", price)));
        subject.onNext(3500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 2 : %d", price)));
        subject.onNext(4500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, String.format("# 소비자 3 : %d", price)));
        subject.onNext(5500);
    }
}

/*
onNext() | main | 01:30:01.856 | # 소비자 1 : 2000
onNext() | main | 01:30:01.886 | # 소비자 1 : 3500
onNext() | main | 01:30:01.887 | # 소비자 2 : 3500
onNext() | main | 01:30:01.887 | # 소비자 1 : 4500
onNext() | main | 01:30:01.887 | # 소비자 2 : 4500
onNext() | main | 01:30:01.887 | # 소비자 3 : 4500
onNext() | main | 01:30:01.887 | # 소비자 1 : 5500
onNext() | main | 01:30:01.887 | # 소비자 2 : 5500
onNext() | main | 01:30:01.888 | # 소비자 3 : 5500
*/
