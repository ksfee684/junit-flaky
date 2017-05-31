package org.ksfee.flaky;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FlakyRule implements TestRule {

    private static class FlakyStatement extends Statement {

        private final int maxRuns;

        private final int minRequiredPasses;

        private final Statement statement;

        private FlakyStatement(int maxRuns, int minRequiredPasses, Statement statement) {
            this.maxRuns = maxRuns;
            this.minRequiredPasses = minRequiredPasses;
            this.statement = statement;
        }

        @Override
        public void evaluate() throws Throwable {
            int passedCount = 0;
            int i;

            for (i = 0; i < maxRuns && passedCount < minRequiredPasses; i++) {
                try {
                    statement.evaluate();
                } catch (Error e) {
                    continue;
                }
                passedCount++;
            }

            if (passedCount < minRequiredPasses) {
                throw new SucceedCountException(minRequiredPasses, passedCount);
            }
        }
    }

    @Override
    public Statement apply(Statement base, Description description) {
        Flaky flaky = description.getAnnotation(Flaky.class);

        if (flaky != null) {
            int maxTrialCount = flaky.maxRuns();
            int minSuccessCount = flaky.minRequiredPasses();
            return new FlakyStatement(maxTrialCount, minSuccessCount, base);
        }

        return base;
    }
}
