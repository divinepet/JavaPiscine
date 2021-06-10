import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 2)
        {
            System.out.println("IllegalArgument");
            System.exit(-1);
        }
        int count = 1;
        int i = 2;
        while (i * i <= n)
        {
            if (n % i == 0)
            {
                System.out.println(false + " " + count);
                System.exit(0);
            }
            count++;
            i++;
        }
        System.out.println(true + " " + count);
    }
}
