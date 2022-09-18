package me.zeroest.rxjava.blocking_xxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.data.SampleData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockingSubscribeTest {

    @Test
    public void totalSalesOfBranchABlockingSubscribeTest() {
        /*
        15_000_000, 25_000_000, 10_000_000, 35_000_000, 23_000_000, 40_000_000, 50_000_000, 45_000_000,
        35_000_000, 23_000_000, 15_000_000, 10_000_000
        */

        Integer expectSalesTotal = SampleData.salesOfBranchA.stream()
            .reduce(Integer::sum)
            .get();
        Calculator calculator = new Calculator();

        // given
        Observable<Integer> observable = SampleObservable.getSalesOfBranchA();

        // when
        observable.blockingSubscribe(sales -> calculator.sumValue(sales));

        // then
        assertEquals(expectSalesTotal, calculator.getSum());
    }

}