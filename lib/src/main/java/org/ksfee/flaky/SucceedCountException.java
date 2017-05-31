package org.ksfee.flaky;

public class SucceedCountException extends Exception {

    private final int minRequiredPasses;

    private final int passedCount;

    SucceedCountException(int minRequiredPasses, int passedCount) {
        super();
        this.minRequiredPasses = minRequiredPasses;
        this.passedCount = passedCount;
    }

    @Override
    public String getMessage() {
        return "This Test is required " +
                passedCount +
                " times succeed, but test is succeeded " +
                minRequiredPasses +
                " times";
    }

    public int getMinRequiredPasses() {
        return minRequiredPasses;
    }

    public int getPassedCount() {
        return passedCount;
    }
}
