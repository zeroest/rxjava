package me.zeroest.rxjava.blocking_xxx;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockingForEachTest {

    @Test
    public void getSpeedOfSectionAForEachTest() {
        /*
        100, 110, 115, 130, 160
        */

        // given
        Observable<Integer> observable = SampleObservable.getSpeedOfSectionA();

        // when
        Observable<Integer> filtered = observable
            .filter(speed -> speed > 110);

        // then
        filtered.blockingForEach(speed ->
            assertTrue(speed > 110)
        );
    }

}