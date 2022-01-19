import java.util.Scanner;

public class Program {
    public static boolean isPrime(int n) {
        if (n < 2)
            return false;
        int i = 2;
        while (i * i <= n) {
            if (n % i == 0)
                return false;
            i++;
        }
        return true;
    }

    public static int sumOfSixNumbers(int num)  {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int coffeeRequestCounter = 0;
        while (sc.hasNextInt()) {
            int request = sc.nextInt();
            if (request == 42)
                break;
            if (isPrime(sumOfSixNumbers(request)))
                coffeeRequestCounter++;
        }
        System.out.println("Count of coffee-request – " + coffeeRequestCounter);
    }
}
