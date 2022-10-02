package me.zeroest.rxjava.test.assertxxx;

import me.zeroest.rxjava.test.SampleObservable;
import me.zeroest.rxjava.util.TimeUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class AssertNotCompleteTest {

    @Test
    public void assertNotCompleteTest() {
        SampleObservable.getSalesOfBranchA()
            .zipWith(
                SampleObservable.getSalesOfBranchB(),
                (a, b) -> {
                    TimeUtil.sleep(1000L);
                    return a + b;
                }
            )
            .test()
            .awaitDone(3000L, TimeUnit.MILLISECONDS)
            .assertNotComplete();
    }

}
