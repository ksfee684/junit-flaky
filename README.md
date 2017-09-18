# junit-flaky

## Usage

- `maxRuns`
  - Describe the maximum number of test runs

- `minRequiredPasses`
  - Describe the minimum number of test passed

```java
@Flaky(maxRuns = 10, minRequiredPasses = 3)
@Test
public void testSomething() {
    Random random = new Random();
    int value = random.nextInt(10);

    assertTrue(value < 4);
}
```
