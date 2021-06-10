import java.util.Scanner;

public class Program {
    public static int pow(int num, int power) {
        if (power == 0)
            return 1;
        int total = num;
        while (power > 1) {
            total = total * num;
            power--;
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int weekIterator = 1;
        int i = 0;
        long min = 0;
        int local = 0;
        while (i++ < 18)
        {
            String week = sc.next();
            if (week.equals("42")) {
                i -= 1;
                break;
            }
            int weekDay = sc.nextInt();
            if (weekDay != i) {
                System.out.println("IllegalArgument");
                System.exit(-1);
            }
            local = 10;
            for (int k = 0; k < 5; k++)
            {
                int tmp = sc.nextInt();
                if (tmp < local)
                    local = tmp;
            }
            min = (min * 10) + local;
        }
        for (int k = 1; k <= i; k++)
        {
            long power = (long)(pow(10, i - k));
            long num = min / power;
            System.out.print("Week " + weekIterator + " ");
            while (num > 0) {
                System.out.print("=");
                num--;
            }
            min %= power;
            System.out.println(">");
            weekIterator++;
        }
    }
}
