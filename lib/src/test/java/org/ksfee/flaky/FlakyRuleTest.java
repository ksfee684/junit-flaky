package org.ksfee.flaky;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class FlakyRuleTest {

    @Rule
    public FlakyRule flakyRule = new FlakyRule();

    @Flaky(maxRuns = 10, minRequiredPasses = 3)
    @Test
    public void test() {
        Random random = new Random();
        int value = random.nextInt(10);

        System.out.println("value = " + value);
        assertTrue(value < 5);
    }
}
