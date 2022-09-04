package me.zeroest.rxjava.operators.creating;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class FromFuture {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Future<Double> future = longTimeWork();

        shortTimeWork();

        Observable
            .fromFuture(future)
            .subscribe(data -> Logger.log(LogType.ON_NEXT, "# 긴 처리 시간 걸리는 작업 완료!"));

        long endTime = System.currentTimeMillis();

        double executeTime = (endTime - startTime) / 1000.0;
        System.out.println();
        Logger.log(LogType.PRINT, String.format("처리 시간: %f 초", executeTime));
    }

    private static Future<Double> longTimeWork() {
        Logger.log(LogType.PRINT, "# 긴 처리 시간 걸리는 작업...");
        return CompletableFuture.supplyAsync(() -> calculate(6000L));
    }

    private static Double shortTimeWork() {
        Logger.log(LogType.PRINT, "# 짧은 처리 시간 걸리는 작업...");
        Double result = calculate(3000L);
        Logger.log(LogType.PRINT, "# 짧은 처리 시간 걸리는 작업 완료!");
        return result;
    }

    private static Double calculate(long milliseconds) {
        Logger.log(LogType.PRINT, String.format("*** calculate : %d ms work ***", milliseconds));
        TimeUtil.sleep(milliseconds);
        return 100.0;
    }
}

/*
print() | main | 19:18:11.859 | # 긴 처리 시간 걸리는 작업...
print() | main | 19:18:11.940 | # 짧은 처리 시간 걸리는 작업...
print() | main | 19:18:11.943 | *** calculate : 3000 ms work ***
print() | ForkJoinPool.commonPool-worker-19 | 19:18:11.943 | *** calculate : 6000 ms work ***
print() | main | 19:18:14.944 | # 짧은 처리 시간 걸리는 작업 완료!
onNext() | main | 19:18:17.944 | # 긴 처리 시간 걸리는 작업 완료!

print() | main | 19:18:17.951 | 처리 시간: 6.139000 초
*/