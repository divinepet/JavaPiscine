package ex03;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    private static void downloadFile(String file, String threadName) throws InterruptedException {
        System.out.println(threadName + " start download file number " + file);
        Thread.sleep(500);
        System.out.println(threadName + " finish download file number " + file);
    }

    private static class DownloadTask implements Runnable {

        private final String name;

        public DownloadTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                downloadFile(name, Thread.currentThread().getName().substring(Thread.currentThread().getName().indexOf("thread")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int threadSize = 0;

        try {
            threadSize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        List<String> list = Files.readAllLines(new File("./src/ex03/files_urls.txt").toPath(), Charset.defaultCharset() )
                .stream().map(t -> t.substring(0, 2)).collect(Collectors.toList());

        ExecutorService pool = Executors.newFixedThreadPool(threadSize);
        for (String file : list) {
            pool.submit(new DownloadTask(file));
        }
        pool.shutdown();
    }
}
