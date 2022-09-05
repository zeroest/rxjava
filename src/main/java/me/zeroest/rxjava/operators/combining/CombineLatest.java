package me.zeroest.rxjava.operators.combining;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.NumberUtil;
import me.zeroest.rxjava.util.TimeUtil;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.concurrent.TimeUnit;

public class CombineLatest {
    public static void main(String[] args) {
        Observable<Long> observable1 =
            Observable
                .interval(500L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, String.format("# observable 1: %d", data)))
                .take(4);

        Observable<Long> observable2 =
            Observable
                .interval(700L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, String.format("# observable 2: %d", data)))
                .take(4);

        Observable
            .combineLatest(observable1, observable2,
                (data1, data2) -> String.format("data1: %d\tdata2: %d", data1, data2))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
/*
doOnNext() | RxComputationThreadPool-1 | 00:02:56.597 | # observable 1: 0
doOnNext() | RxComputationThreadPool-2 | 00:02:56.765 | # observable 2: 0
onNext() | RxComputationThreadPool-2 | 00:02:56.767 | data1: 0	data2: 0
doOnNext() | RxComputationThreadPool-1 | 00:02:57.065 | # observable 1: 1
onNext() | RxComputationThreadPool-1 | 00:02:57.065 | data1: 1	data2: 0
doOnNext() | RxComputationThreadPool-2 | 00:02:57.465 | # observable 2: 1
onNext() | RxComputationThreadPool-2 | 00:02:57.466 | data1: 1	data2: 1
doOnNext() | RxComputationThreadPool-1 | 00:02:57.565 | # observable 1: 2
onNext() | RxComputationThreadPool-1 | 00:02:57.565 | data1: 2	data2: 1
doOnNext() | RxComputationThreadPool-1 | 00:02:58.065 | # observable 1: 3
onNext() | RxComputationThreadPool-1 | 00:02:58.066 | data1: 3	data2: 1
doOnNext() | RxComputationThreadPool-2 | 00:02:58.165 | # observable 2: 2
onNext() | RxComputationThreadPool-2 | 00:02:58.166 | data1: 3	data2: 2
doOnNext() | RxComputationThreadPool-2 | 00:02:58.865 | # observable 2: 3
onNext() | RxComputationThreadPool-2 | 00:02:58.866 | data1: 3	data2: 3
*/

        // 랜덤 온도 데이터
        Observable<Integer> temperature =
            Observable
                .interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.temperatureOfSeoul[NumberUtil.randomRange(0, 5)]);

        // 랜덤 습도 데이터
        Observable<Integer> humidity =
            Observable
                .interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.humidityOfSeoul[NumberUtil.randomRange(0, 5)]);

        Observable.combineLatest(temperature, humidity,
            (temp, hum) -> String.format("최신 온습도 데이터 - 온도: %d도\t습도: %d", temp, hum))
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
/*
onNext() | RxComputationThreadPool-3 | 00:10:43.652 | 최신 온습도 데이터 - 온도: 10도	습도: 62
onNext() | RxComputationThreadPool-4 | 00:10:43.752 | 최신 온습도 데이터 - 온도: 10도	습도: 32
onNext() | RxComputationThreadPool-4 | 00:10:43.931 | 최신 온습도 데이터 - 온도: 10도	습도: 43
onNext() | RxComputationThreadPool-3 | 00:10:44.088 | 최신 온습도 데이터 - 온도: 12도	습도: 43
onNext() | RxComputationThreadPool-4 | 00:10:44.110 | 최신 온습도 데이터 - 온도: 12도	습도: 32
onNext() | RxComputationThreadPool-4 | 00:10:44.289 | 최신 온습도 데이터 - 온도: 12도	습도: 33
onNext() | RxComputationThreadPool-4 | 00:10:44.468 | 최신 온습도 데이터 - 온도: 12도	습도: 32
onNext() | RxComputationThreadPool-3 | 00:10:44.525 | 최신 온습도 데이터 - 온도: 13도	습도: 32
onNext() | RxComputationThreadPool-4 | 00:10:44.647 | 최신 온습도 데이터 - 온도: 13도	습도: 45
onNext() | RxComputationThreadPool-4 | 00:10:44.826 | 최신 온습도 데이터 - 온도: 13도	습도: 43
onNext() | RxComputationThreadPool-3 | 00:10:44.962 | 최신 온습도 데이터 - 온도: 13도	습도: 43
onNext() | RxComputationThreadPool-4 | 00:10:45.005 | 최신 온습도 데이터 - 온도: 13도	습도: 62
onNext() | RxComputationThreadPool-3 | 00:10:45.399 | 최신 온습도 데이터 - 온도: 11도	습도: 62
onNext() | RxComputationThreadPool-3 | 00:10:45.836 | 최신 온습도 데이터 - 온도: 12도	습도: 62
*/
    }
}
