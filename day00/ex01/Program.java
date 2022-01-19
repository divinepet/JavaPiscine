import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void printError() {
        System.out.println("IllegalArgument");
        System.exit(-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        try {
            n = sc.nextInt();
            if (n < 2)
                printError();
        } catch (InputMismatchException e) {
            printError();
        }
        int count = 1;
        int i = 2;

        while (i * i <= n) {
            if (n % i == 0) {
                System.out.println(false + " " + count);
                System.exit(0);
            }
            count++;
            i++;
        }
        System.out.println(true + " " + count);
    }
}
