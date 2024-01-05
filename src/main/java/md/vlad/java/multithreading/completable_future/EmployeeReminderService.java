package md.vlad.java.multithreading.completable_future;

import md.vlad.java.multithreading.completable_future.database.EmployeeDatabase;
import md.vlad.java.multithreading.completable_future.dto.Employee;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EmployeeReminderService {

    public static void sendEmail(String email) {
        System.out.println("\t" + Thread.currentThread().getName() + "\t" + "Sending training reminder email to : " + email);
    }

    public static void main(String[] args) {
        EmployeeReminderService service = new EmployeeReminderService();
        try {
            service.sendReminderToEmployee().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public CompletableFuture<Void> sendReminderToEmployee() {
        Executor executor = Executors.newFixedThreadPool(3);
        Executor executor1 = Executors.newFixedThreadPool(4);
        Executor executor2 = Executors.newFixedThreadPool(5);
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("fetchEmployee : " + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();
        }, executor1).thenApplyAsync(employees -> {
            System.out.println("filter new joiner employee : " + Thread.currentThread().getName());
            return employees.stream()
                    .filter(employee -> "TRUE".equals(employee.getNewJoiner()))
                    .collect(Collectors.toList());
        }, executor2).thenApplyAsync(employees -> {
            System.out.println("filter learning pending employee : " + Thread.currentThread().getName());
            return employees.stream()
                    .filter(employee -> "TRUE".equals(employee.getLearningPending()))
                    .collect(Collectors.toList());
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("get emails from employees : " + Thread.currentThread().getName());
            return employees.stream().map(Employee::getEmail).collect(Collectors.toList());
        }, executor1).thenAcceptAsync(emails -> {
            System.out.println("sending emails : " + Thread.currentThread().getName());
            emails.forEach((email)->sendEmail(email));
        }, executor2);
        return completableFuture;
    }
}
