package md.vlad.java.multithreading.thread_pool.scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        testThreadPool();
    }

    private static void testThreadPool() {
        // for scheduling of tasks
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        // task to run after 10 second delay
        service.schedule(new Task(), 10, TimeUnit.SECONDS);

        // task to run repeatedly every 10 seconds
        service.scheduleAtFixedRate(new Task(), 15, 10, TimeUnit.SECONDS);

        // task to run repeatedly 10 seconds after previous task completes
        service.scheduleWithFixedDelay(new Task(), 15, 10, TimeUnit.SECONDS);
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread().getName() + " \t time = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
        }
    }
}
