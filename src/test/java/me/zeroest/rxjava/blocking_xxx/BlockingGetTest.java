package me.zeroest.rxjava.blocking_xxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.util.data.SampleData;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BlockingGetTest {

    @Test
    public void blockingGetEmptyTest() {
        // given
        Observable<Object> observable = Observable
            .empty();

        // when
        Object object = observable
            .firstElement()
            .blockingGet();

        // then
        assertNull(object);
    }

    @Test
    public void getSalesOfBranchAGetTest() {
        /*
        15_000_000, 25_000_000, 10_000_000, 35_000_000, 23_000_000, 40_000_000, 50_000_000, 45_000_000,
        35_000_000, 23_000_000, 15_000_000, 10_000_000
        */

        Integer expectTotalSales = SampleData.salesOfBranchA.stream()
            .reduce(Integer::sum)
            .get();

        // given
        Observable<Integer> observable = SampleObservable
            .getSalesOfBranchA();

        // when
        Integer actualTotalSales = observable
            .reduce(Integer::sum)
            .blockingGet();

        // then
        assertEquals(expectTotalSales, actualTotalSales);
    }

    @Test
    public void salesAllBranchGetTest() {
        /*
        15_000_000, 25_000_000, 10_000_000, 35_000_000, 23_000_000, 40_000_000, 50_000_000, 45_000_000,
        35_000_000, 23_000_000, 15_000_000, 10_000_000
        */

        Integer expectTotalSales = Stream.of(
            SampleData.salesOfBranchA.stream(),
            SampleData.salesOfBranchB.stream(),
            SampleData.salesOfBranchC.stream()
        )
            .flatMap(Function.identity())
            .reduce(Integer::sum)
            .get();

        // given
        Observable<Integer> observable = Observable.zip(
            SampleObservable.getSalesOfBranchA(),
            SampleObservable.getSalesOfBranchB(),
            SampleObservable.getSalesOfBranchC(),
            (a, b, c) -> a + b + c
        );

        // when
        Integer actualTotalSales = observable
            .reduce(Integer::sum)
            .blockingGet();

        // then
        assertEquals(expectTotalSales, actualTotalSales);
    }

}
