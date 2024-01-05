package md.vlad.java.multithreading.completable_future;

import md.vlad.java.multithreading.completable_future.database.EmployeeDatabase;
import md.vlad.java.multithreading.completable_future.dto.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {
    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> listCompletableFuture = CompletableFuture
                .supplyAsync(()->{
                    System.out.println("Executed by : "+Thread.currentThread().getName());
                    return EmployeeDatabase.fetchEmployees();
                },executor);
        return listCompletableFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        supplyAsyncDemo.getEmployees().forEach(System.out::println);
    }
}
