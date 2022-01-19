import java.util.Scanner;

public class Program {

    public static int isContains(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c)
                return (i);
        }
        return (-1);
    }

    public static void sort(int[] array, char[] indexes)
    {
        int tmp;
        char tmp2;
        int flag = 1;
        while (flag == 1) {
            flag = 0;
            for (int k = 0; k < array.length - 1; k++) {
                if (array[k] < array[k + 1]) {
                    tmp = array[k + 1];
                    array[k + 1] = array[k];
                    array[k] = tmp;
                    tmp2 = indexes[k + 1];
                    indexes[k + 1] = indexes[k];
                    indexes[k] = tmp2;
                    flag = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] inputString = str.toCharArray();
        char[] topSymbols = new char[65535];
        int[] entryCount = new int[65535];
        int k = 0;
        int index = 0;
        for (char c : inputString) {
            index = isContains(topSymbols, c);
            if (index == -1) {
                topSymbols[k] = c;
                entryCount[k] += 1;
                k++;
            } else
                entryCount[index] += 1;
        }
        sort(entryCount, topSymbols);
        int[] gridCount = new int[10];
        int max_entries = entryCount[0];
        for (int i = 0; i < 10; i++) {
            if (entryCount[i] > max_entries)
                max_entries = entryCount[i];
        }
        for (int i = 0; i < gridCount.length; i++)
            gridCount[i] = (entryCount[i] * 10) / max_entries;
        int row = 11;
        while (row > 0) {
            for (int i = 0; i < 10; i++) {
                if (gridCount[i] + 1 == row)
                    System.out.print(entryCount[i]);
                if (gridCount[i] >= row)
                    System.out.print("#");
                System.out.print("\t");
            }
            System.out.println();
            row--;
        }
        for (int i = 0; i < 10; i++)
            System.out.print(topSymbols[i] + "\t");
    }
}
