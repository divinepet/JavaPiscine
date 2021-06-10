package ex02;

public class myThread extends Thread {
    private int[] array;
    private int localSum;
    private int start;
    private int end;

    myThread (int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.localSum = 0;
    }

    public void sum() {
        Main.totalSum += localSum;
        synchronized (Main.threadinfo) {
            Main.threadinfo.add(currentThread().getName() + ": from " + start + " to " + end + " sum is " + localSum);
        }
    }

    public void run() {
        for (int i = 0; i < array.length; i++) {
            localSum += array[i];
        }
        sum();
    }
}
