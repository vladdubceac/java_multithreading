package md.vlad.java.multithreading.completable_future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WhyNotFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<List<Integer>> future = service.submit(() -> {
            // API call
            System.out.println("Thread : "+Thread.currentThread().getName());
            delay(1);
            return Arrays.asList(1, 2, 3, 4);
        });

        List<Integer> list = future.get();
        System.out.println(list);
    }

    private static void delay(int min) {
        try {
            TimeUnit.MINUTES.sleep(min);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
