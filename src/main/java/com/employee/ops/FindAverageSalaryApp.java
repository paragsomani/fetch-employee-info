package com.employee.ops;

import com.employee.model.Employee;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class FindAverageSalaryApp {

  public Map<String, Map<String, Double>> findAverageSalary(List<Employee> employees) {
    Map<String, List<Employee>> employeesByLocation =
        employees.stream().collect(Collectors.groupingBy(Employee::getOfficeLocation));

    // Create a thread pool
    ExecutorService executorService =
        Executors.newFixedThreadPool(
            Math.min(employeesByLocation.size(), Runtime.getRuntime().availableProcessors()));

    // Process each office location in parallel

    List<CompletableFuture<Map.Entry<String, Map<String, Double>>>> futures =
        employeesByLocation.entrySet().stream()
            .map(
                entry ->
                    CompletableFuture.supplyAsync(
                        () -> {
                          String location = entry.getKey();
                          List<Employee> locationEmployees = entry.getValue();
                          // Calculate average salary by designation for this location
                          Map<String, Double> avgSalariesByDesignation =
                              locationEmployees.stream()
                                  .collect(
                                      Collectors.groupingBy(
                                          Employee::getDesignation,
                                          Collectors.averagingDouble(Employee::getSalary)));

                          return (Map.Entry<String, Map<String, Double>>)
                              new AbstractMap.SimpleEntry<>(location, avgSalariesByDesignation);
                        },
                        executorService))
            .collect(Collectors.toList());

    // Combine results
    Map<String, Map<String, Double>> result =
        futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    // Shutdown the executor service
    executorService.shutdown();

    return result;
  }

  public Map<String, Map<String, Double>> calculateAvgSalaryUsingParallelStream(
      List<Employee> employees) {
    return employees.parallelStream()
        .collect(
            Collectors.groupingBy(
                Employee::getOfficeLocation,
                Collectors.groupingBy(
                    Employee::getDesignation, Collectors.averagingDouble(Employee::getSalary))));
  }
}
