package me.zeroest.rxjava.operators.transforming;

import io.reactivex.Observable;
import io.reactivex.Single;
import me.zeroest.rxjava.util.LogType;
import me.zeroest.rxjava.util.Logger;
import me.zeroest.rxjava.util.data.SampleData;

import java.util.List;

public class ToList {
    public static void main(String[] args) {
        Single<List<Integer>> single =
            Observable
                .just(1, 3, 5, 7, 9)
                .toList();

        single
            .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
/*
onNext() | main | 02:22:44.850 | [1, 3, 5, 7, 9]
*/

        // =============================================================================

        Observable.fromIterable(SampleData.carList)
            .toList()
            .subscribe(carList -> Logger.log(LogType.ON_NEXT, carList));
/*
onNext() | main | 02:22:44.889 | [Car{carMaker=CHEVROLET, carType=SEDAN, carName='말리부', carPrice=23000000, isNew=false}, Car{carMaker=HYUNDAE, carType=SUV, carName='쏘렌토', carPrice=33000000, isNew=false}, Car{carMaker=CHEVROLET, carType=SUV, carName='트래버스', carPrice=50000000, isNew=false}, Car{carMaker=HYUNDAE, carType=SEDAN, carName='팰리세이드', carPrice=28000000, isNew=false}, Car{carMaker=CHEVROLET, carType=SUV, carName='트랙스', carPrice=18000000, isNew=false}, Car{carMaker=SSANGYOUNG, carType=SUV, carName='티볼리', carPrice=23000000, isNew=false}, Car{carMaker=SAMSUNG, carType=SUV, carName='SM6', carPrice=40000000, isNew=false}, Car{carMaker=SSANGYOUNG, carType=SUV, carName='G4렉스턴', carPrice=43000000, isNew=false}, Car{carMaker=SAMSUNG, carType=SEDAN, carName='SM5', carPrice=35000000, isNew=false}]
[
    Car{carMaker=CHEVROLET, carType=SEDAN, carName='말리부', carPrice=23000000, isNew=false},
    Car{carMaker=HYUNDAE, carType=SUV, carName='쏘렌토', carPrice=33000000, isNew=false},
    Car{carMaker=CHEVROLET, carType=SUV, carName='트래버스', carPrice=50000000, isNew=false},
    Car{carMaker=HYUNDAE, carType=SEDAN, carName='팰리세이드', carPrice=28000000, isNew=false},
    Car{carMaker=CHEVROLET, carType=SUV, carName='트랙스', carPrice=18000000, isNew=false},
    Car{carMaker=SSANGYOUNG, carType=SUV, carName='티볼리', carPrice=23000000, isNew=false},
    Car{carMaker=SAMSUNG, carType=SUV, carName='SM6', carPrice=40000000, isNew=false},
    Car{carMaker=SSANGYOUNG, carType=SUV, carName='G4렉스턴', carPrice=43000000, isNew=false},
    Car{carMaker=SAMSUNG, carType=SEDAN, carName='SM5', carPrice=35000000, isNew=false}
]
*/
    }
}
