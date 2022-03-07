package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberWorkerTest {
    public NumberWorker numberworker;

    @BeforeAll
    public void setUp() throws Exception {
        numberworker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 3, 5, 7 })
    void isPrimeForPrimesTest(int a) {
        assertEquals(true, numberworker.isPrime(a));
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 8, 16, 32 })
    void isPrimeForNotPrimes(int a) {
        assertEquals(false, numberworker.isPrime(a));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1 })
    void isPrimeForIncorrectNumbers(int a) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberworker.isPrime(a);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "./../../../data.csv")
    void withCsvSourceTest(int first, int second) {
        assertEquals(second, numberworker.digitsSum(first));
    }
}
