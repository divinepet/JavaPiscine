import java.util.Scanner;

public class Program {

    public static void printError() {
        System.err.println("Some error");
        System.exit(-1);
    }

    public static void fixedPrint(String s) {
        int spaceCounts = 10 - s.length();
        if (spaceCounts < 0) {
            System.out.println("some error with length.");
            return;
        }
        while (spaceCounts-- > 0)
            System.out.print(" ");
        System.out.print(s);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] arr = new String[256];
        String[] students = new String[10];
        String[] days = {"", "MO", "TU", "WE", "TH", "FR", "SA", "SU",
                            "MO", "TU", "WE", "TH", "FR", "SA", "SU",
                            "MO", "TU", "WE", "TH", "FR", "SA", "SU",
                            "MO", "TU", "WE", "TH", "FR", "SA", "SU",
                            "MO", "TU"};
        String[] classesTime = new String[31];
        int studentsLength = 0;

        while (studentsLength < 10) {
            String input = sc.next();
            students[studentsLength++] = input;
            if (input.equals("."))
                break;
        }

        while (true) {
            String time = sc.next();
            if (time.equals("."))
                break;

            String weekDay = sc.next();

            if (!(time.equals("1") || time.equals("2") || time.equals("3") || time.equals("4") || time.equals("5") || time.equals("6"))) {
                printError();
            }

            for (int i = 0; i < days.length; i++) {
                if (days[i].equals(weekDay)) {
                    classesTime[i] = time;
                }
            }
        }

        int i = 0;

        while (true) {
            String str1 = sc.next();
            if (str1.equals("."))
                break;
            String str2 = sc.next();
            String str3 = sc.next();
            String str4 = sc.next();
            arr[i] = str1 + " " + str2 + " " + str3 + " " + str4;
            i++;
        }



        for (int row = 0; row < studentsLength; row++) {
            if (row == 0)
                System.out.print("           ");
            else {
                fixedPrint(students[row - 1]);
                System.out.print("|");
            }

            for (int e = 0; e < classesTime.length; e++) {
                if (classesTime[e] == null)
                    continue;
                if (row == 0) {
                    fixedPrint(classesTime[e] + ":00 " + days[e] + " " + e);
                    System.out.print("|");
                } else {
                    for (int k = 0; arr[k] != null; k++) {
                        String[] attends = arr[k].split("\\s+");
                        if (attends[0].equals(students[row - 1]) && Integer.parseInt(attends[2]) == e) {
                            if (attends[3].equals("HERE"))
                                System.out.print("         1" + "|");
                            else
                                System.out.print("        -1" + "|");
                        } else
                            System.out.print("          " + "|");
                    }
                }
            }

            System.out.println();
        }


    }
}
