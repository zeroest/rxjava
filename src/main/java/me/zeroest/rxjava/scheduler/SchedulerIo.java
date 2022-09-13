package me.zeroest.rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.io.File;

public class SchedulerIo {
    public static void main(String[] args) {
        File[] files = new File("src/main/java/me/zeroest/rxjava").listFiles();

        /**
         * subscribeOn() 만 지정하면 데이터 통지 및 처리를 모두 RxCachedThreadScheduler 쓰레드에서 실행
         * */
        Observable.fromArray(files)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data.getName()))
            .filter(data -> data.isDirectory())
            .map(dir -> dir.getName())
            .subscribeOn(Schedulers.io())
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
        /*
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.788 | subject
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.831 | subject
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.831 | HelloRxJava.java
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.831 | util
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.831 | util
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | backpressure
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | backpressure
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | scheduler
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | scheduler
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | operators
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | operators
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | reactiveexample
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | reactiveexample
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | observ_flow
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | observ_flow
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | coldhot
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | coldhot
        doOnNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | single_maybe_completable
        onNext() | RxCachedThreadScheduler-1 | 02:17:15.832 | single_maybe_completable
        */

        /**
         * subscribeOn(), observeOn()을 모두 지정하면 데이터 통지와 데이터 처리를 개별 쓰레드에서 진행
         * */
        Observable.fromArray(files)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data.getName()))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .filter(data -> data.isDirectory())
            .map(dir -> dir.getName())
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
        /*
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.224 | subject
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.333 | HelloRxJava.java
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.333 | util
        onNext() | RxComputationThreadPool-1 | 02:23:28.333 | subject
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.333 | backpressure
        onNext() | RxComputationThreadPool-1 | 02:23:28.334 | util
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.334 | scheduler
        onNext() | RxComputationThreadPool-1 | 02:23:28.334 | backpressure
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.334 | operators
        onNext() | RxComputationThreadPool-1 | 02:23:28.334 | scheduler
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.334 | reactiveexample
        onNext() | RxComputationThreadPool-1 | 02:23:28.335 | operators
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.335 | observ_flow
        onNext() | RxComputationThreadPool-1 | 02:23:28.335 | reactiveexample
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.335 | coldhot
        onNext() | RxComputationThreadPool-1 | 02:23:28.335 | observ_flow
        doOnNext() | RxCachedThreadScheduler-1 | 02:23:28.335 | single_maybe_completable
        onNext() | RxComputationThreadPool-1 | 02:23:28.335 | coldhot
        onNext() | RxComputationThreadPool-1 | 02:23:28.336 | single_maybe_completable
        */

        /**
         * observeOn()을 여러개 지정하면 지정한 다음의 데이터 처리를 각각 개별 쓰레드에서 진행
         * */
        Observable.fromArray(files)
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data.getName()))
            .subscribeOn(Schedulers.io())

            .observeOn(Schedulers.computation())
            .filter(data -> data.isDirectory())
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# filter()"))

            .observeOn(Schedulers.computation())
            .map(dir -> dir.getName())
            .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# map()"))

            .observeOn(Schedulers.computation())
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
        /*
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.254 | subject
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.380 | HelloRxJava.java
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.380 | util
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.380 | backpressure
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.380 | # filter()
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.381 | scheduler
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.381 | operators
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.381 | reactiveexample
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.382 | observ_flow
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.382 | # filter()
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.382 | coldhot
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.382 | # map()
        doOnNext() | RxCachedThreadScheduler-1 | 02:27:39.382 | single_maybe_completable
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.382 | # filter()
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.383 | # filter()
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.384 | # map()
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.384 | # filter()
        onNext() | RxComputationThreadPool-3 | 02:27:39.384 | subject
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.384 | # map()
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.385 | # filter()
        onNext() | RxComputationThreadPool-3 | 02:27:39.385 | util
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.385 | # map()
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.385 | # filter()
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.386 | # map()
        onNext() | RxComputationThreadPool-3 | 02:27:39.385 | backpressure
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.386 | # filter()
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.386 | # map()
        onNext() | RxComputationThreadPool-3 | 02:27:39.386 | scheduler
        doOnNext() | RxComputationThreadPool-1 | 02:27:39.386 | # filter()
        onNext() | RxComputationThreadPool-3 | 02:27:39.387 | operators
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.387 | # map()
        onNext() | RxComputationThreadPool-3 | 02:27:39.387 | reactiveexample
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.388 | # map()
        onNext() | RxComputationThreadPool-3 | 02:27:39.388 | observ_flow
        doOnNext() | RxComputationThreadPool-2 | 02:27:39.389 | # map()
        onNext() | RxComputationThreadPool-3 | 02:27:39.389 | coldhot
        onNext() | RxComputationThreadPool-3 | 02:27:39.390 | single_maybe_completable
        */
    }
}


