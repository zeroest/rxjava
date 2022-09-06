package me.zeroest.rxjava.operators.utility;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.TimeUtil;

import java.util.Arrays;

public class Materialize {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6)
            .materialize()
            .subscribe(notification -> {
                String notificationType;
                if (notification.isOnNext()) {
                    notificationType = "onNext()";
                } else if (notification.isOnError()) {
                    notificationType = "onError()";
                } else { // notification.isOnComplete()
                    notificationType = "onComplete()";
                }

                Logger.log(LogType.PRINT, String.format("# Notification Type: %s", notificationType));
                Logger.log(LogType.ON_NEXT, String.format("# Notification Value: %s", notification.getValue()));
            });
/*
print() | main | 00:24:20.019 | # Notification Type: onNext()
onNext() | main | 00:24:20.056 | # Notification Value: 1
print() | main | 00:24:20.056 | # Notification Type: onNext()
onNext() | main | 00:24:20.056 | # Notification Value: 2
print() | main | 00:24:20.057 | # Notification Type: onNext()
onNext() | main | 00:24:20.057 | # Notification Value: 3
print() | main | 00:24:20.057 | # Notification Type: onNext()
onNext() | main | 00:24:20.057 | # Notification Value: 4
print() | main | 00:24:20.057 | # Notification Type: onNext()
onNext() | main | 00:24:20.057 | # Notification Value: 5
print() | main | 00:24:20.057 | # Notification Type: onNext()
onNext() | main | 00:24:20.058 | # Notification Value: 6
print() | main | 00:24:20.058 | # Notification Type: onComplete()
onNext() | main | 00:24:20.058 | # Notification Value: null
*/

        /**
         * 특정 Observable 에서 에러가 발생 할 경우 해당 에러에 대해서 구체적으로 처리할 수 있다.
         * */
        Observable.concatEager(
            Observable.just(
                getDBUser().subscribeOn(Schedulers.io()),
                getAPIUser()
                    .subscribeOn(Schedulers.io())
                    .materialize()
                    .map(notification -> {
                        if (notification.isOnError()) {
                            // 관리자에게 에러 발생을 알림
                            Logger.log(LogType.PRINT, "# API user 에러 발생!");
                        }
                        return notification;
                    })
                    .filter(notification -> !notification.isOnError())
                    .dematerialize(notification -> notification)
            )
        ).subscribe(
            data -> Logger.log(LogType.ON_NEXT, data),
            error -> Logger.log(LogType.ON_ERROR, error),
            () -> Logger.log(LogType.ON_COMPLETE)
        );

        TimeUtil.sleep(1000L);
/*
onNext() | main | 00:28:36.395 | DB user1
onNext() | main | 00:28:36.396 | DB user2
onNext() | main | 00:28:36.396 | DB user3
onNext() | main | 00:28:36.396 | DB user4
onNext() | main | 00:28:36.396 | DB user5
print() | RxCachedThreadScheduler-1 | 00:28:36.396 | # API user 에러 발생!
onNext() | main | 00:28:36.396 | API user1
onNext() | main | 00:28:36.396 | API user2
onComplete() | main | 00:28:36.396
*/
    }


    private static Observable<String> getDBUser() {
        return Observable.fromIterable(Arrays.asList("DB user1", "DB user2", "DB user3", "DB user4", "DB user5"));
    }

    private static Observable<String> getAPIUser() {
        return Observable
            .just("API user1", "API user2", "Not User", "API user4", "API user5")
            .map(user -> {
                if(user.equals("Not User"))
                    throw new RuntimeException();
                return user;
            });
    }
}
