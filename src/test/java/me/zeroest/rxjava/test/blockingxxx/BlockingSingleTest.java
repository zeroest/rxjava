package me.zeroest.rxjava.test.blockingxxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.test.SampleObservable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlockingSingleTest {

    @Test
    public void getSalesOfBranchASingleTest() {
        /*
        15_000_000, 25_000_000, 10_000_000, 35_000_000, 23_000_000, 40_000_000, 50_000_000, 45_000_000,
        35_000_000, 23_000_000, 15_000_000, 10_000_000
        */

        // given
        Observable<Integer> observable = SampleObservable.getSalesOfBranchA();

        // when
        Integer sales = observable
            .filter(sale -> sale > 30_000_000)
            .take(1)
            .blockingSingle();

        // then
        assertEquals(sales, 35_000_000);
    }

    @Test
    public void getSalesOfBranchASingleThrowTest() {
        /*
        15_000_000, 25_000_000, 10_000_000, 35_000_000, 23_000_000, 40_000_000, 50_000_000, 45_000_000,
        35_000_000, 23_000_000, 15_000_000, 10_000_000
        */

        // given
        Observable<Integer> observable = SampleObservable.getSalesOfBranchA();

        // when
        assertThrows(IllegalArgumentException.class, () ->
            observable
                .filter(sale -> sale > 30_000_000)
                .take(2)
                .blockingSingle()
        );

    }

}
