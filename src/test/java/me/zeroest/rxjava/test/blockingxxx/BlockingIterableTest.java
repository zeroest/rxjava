package me.zeroest.rxjava.test.blockingxxx;

import io.reactivex.Observable;
import me.zeroest.rxjava.test.SampleObservable;
import me.zeroest.rxjava.util.data.car.CarMaker;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BlockingIterableTest {

    @Test
    public void getCarMakerIterableTest() {
        /*
        CarMaker.CHEVROLET,
        CarMaker.HYUNDAE,
        CarMaker.SAMSUNG,
        CarMaker.SSANGYOUNG,
        CarMaker.KIA
        */

        // given
        Observable<CarMaker> observable = SampleObservable.getCarMakerStream();

        // when
        Iterable<CarMaker> iterable = observable.blockingIterable();
        Iterator<CarMaker> iterator = iterable.iterator();

        // then
        assertTrue(iterator.hasNext());

        assertEquals(iterator.next(), CarMaker.CHEVROLET);
        assertEquals(iterator.next(), CarMaker.HYUNDAE);
        assertEquals(iterator.next(), CarMaker.SAMSUNG);
        assertEquals(iterator.next(), CarMaker.SSANGYOUNG);
        assertEquals(iterator.next(), CarMaker.KIA);

        assertFalse(iterator.hasNext());
    }

}