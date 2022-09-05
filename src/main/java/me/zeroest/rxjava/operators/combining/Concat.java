package me.zeroest.rxjava.operators.combining;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Concat {
    public static void main(String[] args) {
        Observable<Long> observable1 =
            Observable
            .interval(500L, TimeUnit.MILLISECONDS)
            .take(4);

        Observable<Long> observable2 =
            Observable
                .interval(300L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(num -> num + 1000);

        Observable
            .concat(observable1, observable2)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(4000L);
/*
onNext() | RxComputationThreadPool-1 | 23:08:42.070 | 0
onNext() | RxComputationThreadPool-1 | 23:08:42.552 | 1
onNext() | RxComputationThreadPool-1 | 23:08:43.052 | 2
onNext() | RxComputationThreadPool-1 | 23:08:43.552 | 3
onNext() | RxComputationThreadPool-2 | 23:08:43.857 | 1000
onNext() | RxComputationThreadPool-2 | 23:08:44.157 | 1001
onNext() | RxComputationThreadPool-2 | 23:08:44.457 | 1002
onNext() | RxComputationThreadPool-2 | 23:08:44.757 | 1003
onNext() | RxComputationThreadPool-2 | 23:08:45.057 | 1004
*/

        Observable
            .concat(observable2, observable1)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(4000L);

/*
onNext() | RxComputationThreadPool-3 | 23:16:59.538 | 1000
onNext() | RxComputationThreadPool-3 | 23:16:59.838 | 1001
onNext() | RxComputationThreadPool-3 | 23:17:00.138 | 1002
onNext() | RxComputationThreadPool-3 | 23:17:00.438 | 1003
onNext() | RxComputationThreadPool-3 | 23:17:00.738 | 1004
onNext() | RxComputationThreadPool-4 | 23:17:01.238 | 0
onNext() | RxComputationThreadPool-4 | 23:17:01.739 | 1
onNext() | RxComputationThreadPool-4 | 23:17:02.238 | 2
onNext() | RxComputationThreadPool-4 | 23:17:02.738 | 3
*/

        List<Observable<String>> speedPerSectionList = Arrays.asList(
            SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA),
            SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB),
            SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC)
        );

        Observable
            .concat(speedPerSectionList)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2000L);
/*
onNext() | RxComputationThreadPool-5 | 23:21:15.045 | A 지점의 차량 속도: 100
onNext() | RxComputationThreadPool-5 | 23:21:15.069 | A 지점의 차량 속도: 110
onNext() | RxComputationThreadPool-5 | 23:21:15.124 | A 지점의 차량 속도: 115
onNext() | RxComputationThreadPool-5 | 23:21:15.179 | A 지점의 차량 속도: 130
onNext() | RxComputationThreadPool-5 | 23:21:15.233 | A 지점의 차량 속도: 160
onNext() | RxComputationThreadPool-6 | 23:21:15.334 | B 지점의 차량 속도: 85
onNext() | RxComputationThreadPool-6 | 23:21:15.434 | B 지점의 차량 속도: 90
onNext() | RxComputationThreadPool-6 | 23:21:15.534 | B 지점의 차량 속도: 100
onNext() | RxComputationThreadPool-6 | 23:21:15.634 | B 지점의 차량 속도: 110
onNext() | RxComputationThreadPool-6 | 23:21:15.735 | B 지점의 차량 속도: 105
onNext() | RxComputationThreadPool-6 | 23:21:15.834 | B 지점의 차량 속도: 113
onNext() | RxComputationThreadPool-6 | 23:21:15.935 | B 지점의 차량 속도: 125
onNext() | RxComputationThreadPool-7 | 23:21:16.012 | C 지점의 차량 속도: 98
onNext() | RxComputationThreadPool-7 | 23:21:16.090 | C 지점의 차량 속도: 88
onNext() | RxComputationThreadPool-7 | 23:21:16.166 | C 지점의 차량 속도: 111
onNext() | RxComputationThreadPool-7 | 23:21:16.243 | C 지점의 차량 속도: 123
onNext() | RxComputationThreadPool-7 | 23:21:16.320 | C 지점의 차량 속도: 155
onNext() | RxComputationThreadPool-7 | 23:21:16.398 | C 지점의 차량 속도: 124
onNext() | RxComputationThreadPool-7 | 23:21:16.474 | C 지점의 차량 속도: 136
onNext() | RxComputationThreadPool-7 | 23:21:16.551 | C 지점의 차량 속도: 143
*/
    }
}
