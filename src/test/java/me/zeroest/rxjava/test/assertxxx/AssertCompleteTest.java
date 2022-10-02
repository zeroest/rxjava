package me.zeroest.rxjava.test.assertxxx;

import me.zeroest.rxjava.test.SampleObservable;
import me.zeroest.rxjava.util.TimeUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * assertComplete 이용하여 A지점과 B지점의 매출 합계 처리가 지정된 시간안에 끝나는지 검증하는 예제
 * */
public class AssertCompleteTest {

    @Test
    public void assertCompleteTest() {
        SampleObservable.getSalesOfBranchA()
            .zipWith(
                SampleObservable.getSalesOfBranchB(),
                (a, b) -> {
                    TimeUtil.sleep(100L);
                    return a + b;
                }
            )
            .test()
            .awaitDone(3000L, TimeUnit.MILLISECONDS)
            .assertComplete();
    }

}
