package ex01;

public class Main {
    public static void main(String[] args) {
        try {
            int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
            Dispute dispute = new Dispute();
            Producer producer = new Producer(dispute, count);
            Consumer consumer = new Consumer(dispute, count);
            new Thread(producer).start();
            new Thread(consumer).start();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Dispute {
    private boolean isSaid = false;
    public synchronized void sayHen() {
        while (!isSaid) {
            try { wait(); }
            catch (InterruptedException e) {}
        }
        isSaid = false;
        System.out.println("Hen");
        notify();
    }
    public synchronized void sayEgg() {
        while (isSaid) {
            try { wait(); }
            catch (InterruptedException e) {}
        }
        isSaid = true;
        System.out.println("Egg");
        notify();
    }
}

class Producer implements Runnable {

    Dispute dispute;
    private final int count;

    Producer(Dispute dispute, int count) {
        this.dispute = dispute;
        this.count = count;
    }
    public void run(){
        for (int i = 0; i < count; i++)
            dispute.sayHen();
    }
}

class Consumer implements Runnable {

    Dispute dispute;
    private final int count;

    Consumer(Dispute dispute, int count) {
        this.dispute = dispute;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++)
            dispute.sayEgg();
    }
}