package me.zeroest.rxjava.operators.utility;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.NumberUtil;
import me.zeroest.rxjava.util.TimeUtil;

public class TimeInterval {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7, 9)
            .delay(item -> {
                TimeUtil.sleep(NumberUtil.randomRange(100, 1000));
                return Observable.just(item);
            })
            .timeInterval()
            .subscribe(timed -> // 구독되는 데이터가 timeInterval()로 인해 Timed<Integer>타입으로 변경됨
                Logger.log(LogType.ON_NEXT, String.format("# 통지하는데 걸린 시간: %d" +
                    "\t# 통지된 데이터: %d", timed.time(), timed.value()))
            );
/*
onNext() | main | 00:12:02.127 | # 통지하는데 걸린 시간: 994	# 통지된 데이터: 1
onNext() | main | 00:12:02.746 | # 통지하는데 걸린 시간: 651	# 통지된 데이터: 3
onNext() | main | 00:12:03.242 | # 통지하는데 걸린 시간: 495	# 통지된 데이터: 5
onNext() | main | 00:12:03.798 | # 통지하는데 걸린 시간: 556	# 통지된 데이터: 7
onNext() | main | 00:12:04.151 | # 통지하는데 걸린 시간: 354	# 통지된 데이터: 9
*/
    }
}
