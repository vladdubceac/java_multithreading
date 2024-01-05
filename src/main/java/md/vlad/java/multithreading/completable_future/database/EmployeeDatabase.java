package md.vlad.java.multithreading.completable_future.database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.vlad.java.multithreading.completable_future.dto.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeDatabase {
    public static final String JSON_FILENAME = "employees.json";

    public static List<Employee> fetchEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(JSON_FILENAME), new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
