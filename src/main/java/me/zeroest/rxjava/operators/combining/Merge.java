package me.zeroest.rxjava.operators.combining;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.concurrent.TimeUnit;

public class Merge {
    public static void main(String[] args) {
        Observable<Long> observable1 =
            Observable
                .interval(200L, TimeUnit.MILLISECONDS)
                .take(5);

        Observable<Long> observable2 =
            Observable
                .interval(400L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(num -> num + 1000);

        Observable
            .merge(observable1, observable2)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
/*
onNext() | RxComputationThreadPool-1 | 13:55:49.520 | 0
onNext() | RxComputationThreadPool-1 | 13:55:49.710 | 1         // 통지 시간이 같을때 merge() 함수에 먼저 지정된 Observable이 먼저 통지된다
onNext() | RxComputationThreadPool-2 | 13:55:49.711 | 1000
onNext() | RxComputationThreadPool-1 | 13:55:49.910 | 2
onNext() | RxComputationThreadPool-1 | 13:55:50.110 | 3
onNext() | RxComputationThreadPool-2 | 13:55:50.111 | 1001
onNext() | RxComputationThreadPool-1 | 13:55:50.310 | 4
onNext() | RxComputationThreadPool-2 | 13:55:50.511 | 1002
onNext() | RxComputationThreadPool-2 | 13:55:50.911 | 1003
onNext() | RxComputationThreadPool-2 | 13:55:51.311 | 1004
*/

        Observable<String> sectionA = SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA);
        Observable<String> sectionB = SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB);
        Observable<String> sectionC = SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC);

        Observable
            .merge(sectionA, sectionB, sectionC)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
/*
onNext() | RxComputationThreadPool-3 | 22:37:39.667 | A 지점의 차량 속도: 100
onNext() | RxComputationThreadPool-4 | 22:37:39.668 | B 지점의 차량 속도: 85
onNext() | RxComputationThreadPool-5 | 22:37:39.673 | C 지점의 차량 속도: 98
onNext() | RxComputationThreadPool-3 | 22:37:39.674 | A 지점의 차량 속도: 110
onNext() | RxComputationThreadPool-5 | 22:37:39.719 | C 지점의 차량 속도: 88
onNext() | RxComputationThreadPool-3 | 22:37:39.729 | A 지점의 차량 속도: 115
onNext() | RxComputationThreadPool-4 | 22:37:39.765 | B 지점의 차량 속도: 90
onNext() | RxComputationThreadPool-3 | 22:37:39.784 | A 지점의 차량 속도: 130
onNext() | RxComputationThreadPool-5 | 22:37:39.796 | C 지점의 차량 속도: 111
onNext() | RxComputationThreadPool-3 | 22:37:39.839 | A 지점의 차량 속도: 160
onNext() | RxComputationThreadPool-4 | 22:37:39.865 | B 지점의 차량 속도: 100
onNext() | RxComputationThreadPool-5 | 22:37:39.873 | C 지점의 차량 속도: 123
onNext() | RxComputationThreadPool-5 | 22:37:39.950 | C 지점의 차량 속도: 155
onNext() | RxComputationThreadPool-4 | 22:37:39.965 | B 지점의 차량 속도: 110
onNext() | RxComputationThreadPool-5 | 22:37:40.027 | C 지점의 차량 속도: 124
onNext() | RxComputationThreadPool-4 | 22:37:40.065 | B 지점의 차량 속도: 105
onNext() | RxComputationThreadPool-5 | 22:37:40.105 | C 지점의 차량 속도: 136
onNext() | RxComputationThreadPool-4 | 22:37:40.165 | B 지점의 차량 속도: 113
onNext() | RxComputationThreadPool-5 | 22:37:40.182 | C 지점의 차량 속도: 143
onNext() | RxComputationThreadPool-4 | 22:37:40.265 | B 지점의 차량 속도: 125
*/
    }
}
