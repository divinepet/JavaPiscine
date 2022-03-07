package ex00;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void startDispute() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();

        Runnable eggTask = () -> System.out.println("Egg");
        Runnable henTask = () -> System.out.println("Hen");
        Thread threadHen = new Thread(henTask);
        Thread threadEgg = new Thread(eggTask);
        threads.add(threadHen);
        threads.add(threadEgg);
        Collections.shuffle(threads);
        for (Thread th : threads) {
            th.start();
            th.join();
        }
        threadHen.join();
        threadEgg.join();
    }

    public static void runTask(int count) throws InterruptedException {
        for (int i = 0; i < count; i++)
            startDispute();

        for (int i = 0; i < count; i++)
            System.out.println("Human");

    }

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException, InterruptedException {
        try {
            int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
            runTask(count);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}