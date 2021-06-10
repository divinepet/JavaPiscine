package ex02;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static int totalSum = 0;
    public static volatile ArrayList<String> threadinfo;
    public static int[] fillArrayRandom(int arraySize) {
        int[] array = new int[arraySize];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 10);
        }
        return array;
    }
    public static void makeThreads(int arraySize, int delimiter) {
        ArrayList<Thread> threads = new ArrayList<>();
        int[] array = fillArrayRandom(arraySize);
        int startIndex = 0;
        int endIndex = 0;

        while (endIndex < (arraySize - delimiter)) {
            endIndex = startIndex + delimiter;
            myThread thread = new myThread(Arrays.copyOfRange(array, startIndex, endIndex), startIndex, endIndex);
            threads.add(thread);
            endIndex++;
            startIndex = endIndex;
        }
        myThread thread = new myThread(Arrays.copyOfRange(array, startIndex, arraySize - 1), startIndex, endIndex);
        threads.add(thread);
        for (Thread th : threads)
            th.start();

        for (Thread th : threads) {
            try { th.join(); } catch (InterruptedException e) {}
        }
    }
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        try {
            threadinfo = new ArrayList<>();
            int arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
            int threadCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
            int delimiter = arraySize / threadCount;

            if (threadCount > arraySize) {
                System.out.println("Error: Threads amount can't be more, than array size");
                System.exit(-1);
            } else {
                makeThreads(arraySize, delimiter);
            }
            System.out.println("Sum: " + totalSum);
            for (String s : threadinfo)
                System.out.println(s);
            System.out.println("Sum by threads: " + totalSum);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough arguments. Exit");
        }
    }
}
