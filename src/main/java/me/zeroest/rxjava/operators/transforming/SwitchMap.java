package me.zeroest.rxjava.operators.transforming;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 실시간 검색어 반영과 같은경우
 * 기존 검색어는 중단시키고 추가 입력된 문구로 반영한다.
 * */
public class SwitchMap {
    public static void main(String[] args) {
        TimeUtil.start();

        Observable
            .interval(100L, TimeUnit.MILLISECONDS)
            .take(4)
            .skip(2)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .switchMap(num ->
                Observable.interval(300L, TimeUnit.MILLISECONDS)
                    .take(10)
                    .skip(1)
                    .map(row -> String.format("%d * %d = %d", num, row, (num * row)))
            )
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
            );

        TimeUtil.sleep(4000L);
/*
doOnNext() | RxComputationThreadPool-1 | 01:11:51.438 | 2
doOnNext() | RxComputationThreadPool-1 | 01:11:51.528 | 3
onNext() | RxComputationThreadPool-3 | 01:11:52.143 | 3 * 1 = 3
onNext() | RxComputationThreadPool-3 | 01:11:52.429 | 3 * 2 = 6
onNext() | RxComputationThreadPool-3 | 01:11:52.729 | 3 * 3 = 9
onNext() | RxComputationThreadPool-3 | 01:11:53.029 | 3 * 4 = 12
onNext() | RxComputationThreadPool-3 | 01:11:53.329 | 3 * 5 = 15
onNext() | RxComputationThreadPool-3 | 01:11:53.629 | 3 * 6 = 18
onNext() | RxComputationThreadPool-3 | 01:11:53.929 | 3 * 7 = 21
onNext() | RxComputationThreadPool-3 | 01:11:54.229 | 3 * 8 = 24
onNext() | RxComputationThreadPool-3 | 01:11:54.530 | 3 * 9 = 27

# 실행시간: 3546 ms
*/

        // =============================================================================

        TimeUtil.start();

        Observable
            .interval(1000L, TimeUnit.MILLISECONDS)
            .take(4)
            .skip(2)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .switchMap(num ->
                Observable.interval(300L, TimeUnit.MILLISECONDS)
                    .take(10)
                    .skip(1)
                    .map(row -> String.format("%d * %d = %d", num, row, (num * row)))
            )
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
            );

        TimeUtil.sleep(8000L);
/*
doOnNext() | RxComputationThreadPool-4 | 01:20:48.584 | 2
onNext() | RxComputationThreadPool-5 | 01:20:49.186 | 2 * 1 = 2
onNext() | RxComputationThreadPool-5 | 01:20:49.486 | 2 * 2 = 4
doOnNext() | RxComputationThreadPool-4 | 01:20:49.584 | 3
onNext() | RxComputationThreadPool-6 | 01:20:50.185 | 3 * 1 = 3
onNext() | RxComputationThreadPool-6 | 01:20:50.485 | 3 * 2 = 6
onNext() | RxComputationThreadPool-6 | 01:20:50.785 | 3 * 3 = 9
onNext() | RxComputationThreadPool-6 | 01:20:51.085 | 3 * 4 = 12
onNext() | RxComputationThreadPool-6 | 01:20:51.385 | 3 * 5 = 15
onNext() | RxComputationThreadPool-6 | 01:20:51.685 | 3 * 6 = 18
onNext() | RxComputationThreadPool-6 | 01:20:51.985 | 3 * 7 = 21
onNext() | RxComputationThreadPool-6 | 01:20:52.285 | 3 * 8 = 24
onNext() | RxComputationThreadPool-6 | 01:20:52.585 | 3 * 9 = 27

# 실행시간: 7007 ms
*/

        // =============================================================================

        TimeUtil.start();

        Observable
            .interval(100L, TimeUnit.MILLISECONDS)
            .take(4)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .concatMap(data -> { // concatMap을 사용했기 때문에 매번 모든 키워드 검색 결과를 다 가져온다.
                String keyword = Searcher.KEYWORDS.get(data.intValue()); // 데이터베이스 조회 가정

                System.out.println("========================================");

                return Observable.fromIterable(Searcher.search(keyword))
//                    .doOnNext(notUse -> System.out.println("========================================"))
                    .delay(1000L, TimeUnit.MILLISECONDS);
            })
//            .flatMap(resultList -> Observable.fromIterable(resultList))
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
            );

        TimeUtil.sleep(6000L);
/*
doOnNext() | RxComputationThreadPool-7 | 01:45:12.667 | 0
========================================
doOnNext() | RxComputationThreadPool-7 | 01:45:12.767 | 1
doOnNext() | RxComputationThreadPool-7 | 01:45:12.867 | 2
doOnNext() | RxComputationThreadPool-7 | 01:45:12.967 | 3
onNext() | RxComputationThreadPool-8 | 01:45:13.679 | Macau
onNext() | RxComputationThreadPool-8 | 01:45:13.680 | Malaysia
onNext() | RxComputationThreadPool-8 | 01:45:13.680 | Maldives
onNext() | RxComputationThreadPool-8 | 01:45:13.681 | Mexico
onNext() | RxComputationThreadPool-8 | 01:45:13.681 | Myanmar
onNext() | RxComputationThreadPool-8 | 01:45:13.681 | Macedonia
========================================
onNext() | RxComputationThreadPool-9 | 01:45:14.683 | Macau
onNext() | RxComputationThreadPool-9 | 01:45:14.684 | Malaysia
onNext() | RxComputationThreadPool-9 | 01:45:14.685 | Maldives
onNext() | RxComputationThreadPool-9 | 01:45:14.685 | Macedonia
========================================
onNext() | RxComputationThreadPool-10 | 01:45:15.686 | Malaysia
onNext() | RxComputationThreadPool-10 | 01:45:15.687 | Maldives
========================================
onNext() | RxComputationThreadPool-11 | 01:45:16.688 | Malaysia
# 실행시간: 4133 ms
*/

        // =============================================================================

        TimeUtil.start();

        Observable
            .interval(100L, TimeUnit.MILLISECONDS)
            .take(4)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
            .switchMap(data -> { // switchMap을 사용했기 때문에 마지막 키워드를 사용한 최신 검색 결과만 가져온다
                String keyword = Searcher.KEYWORDS.get(data.intValue()); // 데이터베이스 조회 가정

                System.out.println("========================================");

                return Observable.fromIterable(Searcher.search(keyword))
//                    .doOnNext(notUse -> System.out.println("========================================"))
                    .delay(1000L, TimeUnit.MILLISECONDS);
            })
//            .flatMap(resultList -> Observable.fromIterable(resultList))
            .subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
            );

        TimeUtil.sleep(6000L);
/*
doOnNext() | RxComputationThreadPool-12 | 01:44:16.328 | 0
========================================
doOnNext() | RxComputationThreadPool-12 | 01:44:16.428 | 1
========================================
doOnNext() | RxComputationThreadPool-12 | 01:44:16.528 | 2
========================================
doOnNext() | RxComputationThreadPool-12 | 01:44:16.628 | 3
========================================
onNext() | RxComputationThreadPool-4 | 01:44:17.630 | Malaysia

# 실행시간: 1408 ms
*/
    }

    private static class Searcher {
        // 사용자가 입력할 값
        public static final List<String> KEYWORDS = Arrays.asList("M", "Ma", "Mal", "Malay");

        public static java.util.Map<String, List<String>> map = new HashMap<>();

        static {
            map.put("M", Arrays.asList("Macau", "Malaysia", "Maldives", "Mexico", "Myanmar", "Macedonia"));
            map.put("Ma", Arrays.asList("Macau", "Malaysia", "Maldives", "Macedonia"));
            map.put("Mal", Arrays.asList("Malaysia", "Maldives"));
            map.put("Malay", Arrays.asList("Malaysia"));
        }

        public static List<String> search(String keyword){
            List<String> results = map.get(keyword);
            if(results == null){
                results = new ArrayList<>();
            }
            return results;
        }
    }
}
