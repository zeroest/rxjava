package me.zeroest.rxjava.operators.creating;

import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) {
        // Sync job
        SyncLogic syncLogic = new SyncLogic();
        syncLogic.run();

        // Async job
        AsyncLogic asyncLogic = new AsyncLogic();
        asyncLogic.run();
    }

    public static class SyncLogic {
/*
print() | main | 19:03:55.291 | # 차량 수리비 계산중.......
print() | main | 19:03:58.337 | # (1) 차량 수리비 계산이 완료되었습니다.
print() | main | 19:03:58.348 |   차량 수리비 - 20000 원
print() | main | 19:03:59.350 | # (2) 병가 신청이 완료되었습니다.
print() | main | 19:04:00.352 | # (3) 보헙 접수가 완료되었습니다.
print() | main | 19:04:00.353 | 처리 시간: 5.135000 초
*/
        public void run() {
            long startTime = System.currentTimeMillis();

            // 수리비 계산
            int carRepairCost = CarRepairShop.getCarRepairCostSync(10);
            Logger.log(LogType.PRINT, String.format("  차량 수리비 - %d 원", carRepairCost));

            // 병가 신청
            Company.requestSickLeave("20220101-01");

            // 보험 접수
            InsuranceService.requestInsurance("44나4444");

            long endTime = System.currentTimeMillis();

            double executeTime = (endTime - startTime) / 1000.0;
            Logger.log(LogType.PRINT, String.format("처리 시간: %f 초", executeTime));
        }
    }

    public static class AsyncLogic {
/*
print() | ForkJoinPool.commonPool-worker-19 | 19:02:36.975 | # 차량 수리비 계산중.......
print() | main | 19:02:37.960 | # (2) 병가 신청이 완료되었습니다.
print() | main | 19:02:38.962 | # (3) 보헙 접수가 완료되었습니다.
print() | ForkJoinPool.commonPool-worker-19 | 19:02:40.010 | # (1) 차량 수리비 계산이 완료되었습니다.
print() | main | 19:02:40.024 |   차량 수리비 - 20000 원
print() | main | 19:02:40.025 | 처리 시간: 3.145000 초
*/
        public void run() {
            long startTime = System.currentTimeMillis();

            // 수리비 계산 (시간이 더 오래 걸리는 미래에 끝날 일)
            Future<Integer> carRepairCostFuture = CarRepairShop.getCarRepairCostAsync(10);

            // 병가 신청 (짧은 처리 시간)
            Company.requestSickLeave("20220101-01");

            // 보험 접수 (짧은 처리 시간)
            InsuranceService.requestInsurance("44나4444");

            try {
                Integer carRepairCost = carRepairCostFuture.get();
                Logger.log(LogType.PRINT, String.format("  차량 수리비 - %d 원", carRepairCost));
            } catch (Exception e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();

            double executeTime = (endTime - startTime) / 1000.0;
            Logger.log(LogType.PRINT, String.format("처리 시간: %f 초", executeTime));
        }
    }

    private static class CarRepairShop {
        public static int getCarRepairCostSync(int brokens) {
            return calculateCarRepairCost(brokens);
        }

        public static Future<Integer> getCarRepairCostAsync(int brokens) {
            return CompletableFuture.supplyAsync(() -> calculateCarRepairCost(brokens));
        }

        private static int calculateCarRepairCost(int brokens) {
            Logger.log(LogType.PRINT, "# 차량 수리비 계산중.......");
            TimeUtil.sleep(3000L);
            int repairCost = brokens * 2000;
            Logger.log(LogType.PRINT, "# (1) 차량 수리비 계산이 완료되었습니다.");
            return repairCost;
        }
    }

    private static class Company {
        public static void requestSickLeave(String empNumber) {
            TimeUtil.sleep(1000);
            Logger.log(LogType.PRINT, "# (2) 병가 신청이 완료되었습니다.");
        }
    }

    public static class InsuranceService {
        public static void requestInsurance(String carNumber) {
            TimeUtil.sleep(1000);
            Logger.log(LogType.PRINT, "# (3) 보헙 접수가 완료되었습니다.");
        }
    }
}
