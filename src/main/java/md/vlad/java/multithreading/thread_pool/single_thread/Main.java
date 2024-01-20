package md.vlad.java.multithreading.thread_pool.single_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Task());
        service.execute(new Task());
        service.execute(new Task());
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread().getName() + ", id = " + this.hashCode());
        }
    }
}
