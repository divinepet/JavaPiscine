package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2)
            throw new IllegalArgumentException("IllegalArgument");
        int i = 2;
        while (i * i <= number)
        {
            if (number % i == 0)
                return false;
            i++;
        }
        return true;
    }

    public int digitsSum(int number) {
        int sum = 0;

        while (number > 10) {
            sum += number % 10;
            number /= 10;
        }
        return sum += number % 10;
    }

}
