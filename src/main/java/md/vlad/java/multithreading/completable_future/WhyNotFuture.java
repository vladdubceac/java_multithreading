package md.vlad.java.multithreading.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WhyNotFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        Future<List<Integer>> future1 = service.submit(() -> {
//            // API call
//            System.out.println("Thread : "+Thread.currentThread().getName());
//            System.out.println(10/0);
//            return Arrays.asList(1, 2, 3, 4);
//        });

//        List<Integer> list = future1.get();
//        System.out.println(list);

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.get();
        completableFuture.complete("Return some dummy data ... ");
    }

    private static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
