package com.employee;

import com.employee.model.Employee;
import com.employee.ops.FindAverageSalaryApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {

    List<Employee> employees = new ArrayList<>();

    employees.add(
        new Employee("Ashish", "A", "IT", "Pune", "Software Engineer", Double.valueOf(10000)));
    employees.add(new Employee("Amit", "R", "HR", "Pune", "Recruiter", Double.valueOf(12000)));
    employees.add(
        new Employee("Ramesh", "D", "HR", "Pune", "Senior Recruiter", Double.valueOf(14000)));
    employees.add(new Employee("Jaya", "S", "IT", "Pune", "Tech Lead", Double.valueOf(15000)));
    employees.add(
        new Employee("Smita", "M", "IT", "Bangalore", "Recruiter", Double.valueOf(16000)));
    employees.add(
        new Employee("Umesh", "A", "IT", "Bangalore", "Software Engineer", Double.valueOf(12000)));
    employees.add(
        new Employee("Pooja", "R", "HR", "Bangalore", "Software Engineer", Double.valueOf(12000)));
    employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Recruiter", Double.valueOf(16000)));
    employees.add(
        new Employee("Bobby", "S", "IT", "Bangalore", "Tech Lead", Double.valueOf(20000)));
    employees.add(
        new Employee("Vipul", "M", "IT", "Bangalore", "Software Engineer", Double.valueOf(14000)));

    FindAverageSalaryApp app = new FindAverageSalaryApp();
    long startTime = System.currentTimeMillis();
    var result = app.findAverageSalary(employees);
    long endTime = System.currentTimeMillis();
    System.out.println(
        "Result using completable futures: "
            + result
            + "\n Time taken: "
            + (endTime - startTime)
            + "ms");

    startTime = System.currentTimeMillis();
    var resultNew = app.calculateAvgSalaryUsingParallelStream(employees);
    endTime = System.currentTimeMillis();
    System.out.println(
        "Result using parallel stream: "
            + resultNew
            + "\n Time taken: "
            + (endTime - startTime)
            + "ms");

    printResult(result);
    System.out.println("__________________________________________________________");
    System.out.println("Using Parallel Stream:");
    System.out.println("__________________________________________________________");
    printResult(resultNew);
  }

  private static void printResult(Map<String, Map<String, Double>> result) {
    result.forEach(
        (location, designationSalaries) -> {
          System.out.println("Office Location: " + location);
          designationSalaries.forEach(
              (designation, avgSalary) -> {
                System.out.printf("  %s: INR %.2f%n", designation, avgSalary);
              });
          System.out.println();
        });
  }
}
