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
        long min = 0;
        int weekDayNum = 1;
        int local;

        for (; weekDayNum <= 18; weekDayNum++) {
            String weekDay = sc.next();
            if (weekDay.equals("42"))
                break;
            int tmpWeekDay = sc.nextInt();
            if (!(weekDay + " " + tmpWeekDay).equals("Week " + weekDayNum)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            local = 10;
            for (int k = 0; k < 5; k++) {
                int tmp = sc.nextInt();
                if (tmp < local)
                    local = tmp;
            }
            min = (min * 10) + local;
        }

        for (int currentWeek = 1; currentWeek < weekDayNum; currentWeek++) {
            long power = pow(10, weekDayNum - currentWeek - 1);
            long num = min / power;
            System.out.print("Week " + currentWeek + " ");
            while (num > 0) {
                System.out.print("=");
                num--;
            }
            min %= power;
            System.out.println(">");
        }
    }
}
