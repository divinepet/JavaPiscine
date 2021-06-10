import java.util.Scanner;

public class Program {
    public static boolean isPrime(int n)
    {
        boolean result = false;
        if (n < 2)
            return result;
        int count = 1;
        int i = 2;
        while (i * i <= n)
        {
            if (n % i == 0)
                return result;
            count++;
            i++;
        }
        result = true;
        return result;
    }
    public static int sumOfSixNumbers(int num)
    {
        int sum = 0;

        sum += num % 10;
        num /= 10;
        sum += num % 10;
        num /= 10;
        sum += num % 10;
        num /= 10;
        sum += num % 10;
        num /= 10;
        sum += num % 10;
        num /= 10;
        sum += num % 10;
        num /= 10;
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int coffeeRequestCounter = 0;
        while (sc.hasNextInt())
        {
            int a = sc.nextInt();
            if (a == 42)
                break;
            a = sumOfSixNumbers(a);
            if (isPrime(a))
                coffeeRequestCounter += 1;
        }
        System.out.println("Count of coffee-request – " + coffeeRequestCounter);
    }
}
