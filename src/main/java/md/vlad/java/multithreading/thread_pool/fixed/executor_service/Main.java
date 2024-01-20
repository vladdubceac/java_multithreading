package md.vlad.java.multithreading.thread_pool.fixed.executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
//        testExecutorService();
        testExecutorServiceWithCoreCount();
    }

    private static void testExecutorServiceWithCoreCount() {
        // get count of available cores
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(coreCount);

        // submit the tasks for execution
        for (int i = 0; i < 100; i++) {
            service.execute(new CpuIntensiveTask());
        }
    }

    private static void testExecutorService() {
        // create the pool
        ExecutorService service = Executors.newFixedThreadPool(10);

        // submit the tasks for execution
        for (int i = 0; i < 100; i++) {
            service.execute(new Task());
        }
        System.out.println("Thread : " + Thread.currentThread().getName());
    }

    static class CpuIntensiveTask implements Runnable {

        @Override
        public void run() {
            // some CPU intensive operations
            System.out.println("CPU intensive task , thread : " + Thread.currentThread().getName());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread Name : " + Thread.currentThread().getName());
        }
    }
}
