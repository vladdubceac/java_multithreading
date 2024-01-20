package md.vlad.java.multithreading.thread_pool.cached;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        testThreadPool();
    }

    private static void testThreadPool() {
        // for lot of short lived tasks
        ExecutorService service = Executors.newCachedThreadPool();

        // submit the tasks for execution
        for (int i = 0; i < 100; i++) {
            service.execute(new Task());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            // short lived task
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            System.out.println("Thread : " + Thread.currentThread().getName() + " \t sum = " + sum);
        }
    }
}
