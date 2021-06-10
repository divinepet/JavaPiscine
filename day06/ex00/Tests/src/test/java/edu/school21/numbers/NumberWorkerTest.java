package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Без понятия что это
public class NumberWorkerTest {
    public NumberWorker numberworker; // Создаём экземпляр класса с нашими методами isPrime и digitSum

    @BeforeAll // Эта аннтоация запускает этот метод перед стартом всех тестов для инициализации
    public void setUp() throws Exception {
        numberworker = new NumberWorker();
    }


    /*
    * @ParameterizedTest - тест с параметрами
    * @ValueSource - задаёт параметры в виде массива целых чисел, которые по очереди
    *                входят в метод параметром int a
    * @assertEquals - первое значение принимает параметр и сравнивает его со вторым
    *                 если оба равны, то тест считается успешным.
    */
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

    /*
    * Метод Assertions.assertThrows ожидает получения Exception, переданного
    * ей в качестве параметрама от функции isPrime
    */
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1 })
    void isPrimeForIncorrectNumbers(int a) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberworker.isPrime(a);
        });
    }

    /*
    * CsvFileSource берёт значения из файла first и second,
    * пока не дойдёт до конца
    */
    @ParameterizedTest
    @CsvFileSource(resources = "./../../../data.csv")
    void withCsvSourceTest(int first, int second) {
        assertEquals(second, numberworker.digitsSum(first));
    }
}
