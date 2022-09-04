package me.zeroest.rxjava.operators.transforming;

import io.reactivex.Observable;
import io.reactivex.Single;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

import java.util.Map;

public class ToMap {
    public static void main(String[] args) {
        Observable<String> observable =
            Observable
                .just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo");

        Single<Map<String, String>> singleMapSetKey =
            observable
                .toMap(data -> data.split("-")[0]);

        singleMapSetKey
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 02:24:58.933 | {a=a-Alpha, b=b-Bravo, c=c-Charlie, e=e-Echo}
*/

        // =============================================================================

        Single<Map<String, String>> singleMapSetKeyValue =
            observable
                .toMap(
                    data -> data.split("-")[0],
                    data -> data.split("-")[1]
                );

        singleMapSetKeyValue
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 02:28:35.102 | {a=Alpha, b=Bravo, c=Charlie, e=Echo}
*/
    }
}
