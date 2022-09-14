package me.zeroest.rxjava.do_xxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;

public class DoOnNext {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7, 9, 11, 13, 15)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, String.format("# 원본 통지 데이터: %d", data)))
            .filter(data -> data < 10)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, String.format("# After filter: %d", data)))
            .map(data -> String.format("### %d ###", data))
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, String.format("# After map: %s", data)))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, String.format("# 최종 데이터: %s", data)));
    }
}

/*
doOnNext() | main | 01:45:16.064 | # 원본 통지 데이터: 1
doOnNext() | main | 01:45:16.096 | # After filter: 1
doOnNext() | main | 01:45:16.096 | # After map: ### 1 ###
onNext() | main | 01:45:16.097 | # 최종 데이터: ### 1 ###
doOnNext() | main | 01:45:16.097 | # 원본 통지 데이터: 3
doOnNext() | main | 01:45:16.097 | # After filter: 3
doOnNext() | main | 01:45:16.097 | # After map: ### 3 ###
onNext() | main | 01:45:16.097 | # 최종 데이터: ### 3 ###
doOnNext() | main | 01:45:16.097 | # 원본 통지 데이터: 5
doOnNext() | main | 01:45:16.097 | # After filter: 5
doOnNext() | main | 01:45:16.097 | # After map: ### 5 ###
onNext() | main | 01:45:16.098 | # 최종 데이터: ### 5 ###
doOnNext() | main | 01:45:16.098 | # 원본 통지 데이터: 7
doOnNext() | main | 01:45:16.098 | # After filter: 7
doOnNext() | main | 01:45:16.098 | # After map: ### 7 ###
onNext() | main | 01:45:16.098 | # 최종 데이터: ### 7 ###
doOnNext() | main | 01:45:16.098 | # 원본 통지 데이터: 9
doOnNext() | main | 01:45:16.098 | # After filter: 9
doOnNext() | main | 01:45:16.098 | # After map: ### 9 ###
onNext() | main | 01:45:16.098 | # 최종 데이터: ### 9 ###
doOnNext() | main | 01:45:16.099 | # 원본 통지 데이터: 11
doOnNext() | main | 01:45:16.099 | # 원본 통지 데이터: 13
doOnNext() | main | 01:45:16.099 | # 원본 통지 데이터: 15
*/
