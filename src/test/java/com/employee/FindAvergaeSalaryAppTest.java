package com.employee;

import com.employee.model.Employee;
import com.employee.ops.FindAverageSalaryApp;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class FindAvergaeSalaryAppTest {

  @Test
  public void testFindAverageSalary() {
    // Mock data creation
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("Ashish", "A", "IT", "Pune", "Software Engineer", 12000.0));
    employees.add(new Employee("Amit", "R", "HR", "Pune", "Recruiter", 12000.0));
    employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Senior Recruiter", 14000.0));
    employees.add(new Employee("Jaya", "S", "IT", "Pune", "Tech Lead", 15000.0));
    employees.add(new Employee("Smita", "M", "IT", "Bangalore", "Recruiter", 16000.0));

    FindAverageSalaryApp app = new FindAverageSalaryApp();

    // Call the findAverageSalary method
    Map<String, Map<String, Double>> result = app.findAverageSalary(employees);

    // Verify the result for different test case scenarios
    assertNotNull(result);
    //        number of locations
    assertEquals(2, result.size());
    assertTrue(result.containsKey("Pune"));
    assertTrue(result.containsKey("Bangalore"));

    // Verify average salaries for Pune
    Map<String, Double> puneSalaries = result.get("Pune");
    assertNotNull(puneSalaries);
    assertEquals(4, puneSalaries.size());
    assertTrue(puneSalaries.containsKey("Software Engineer"));
    assertTrue(puneSalaries.containsKey("Recruiter"));
    assertTrue(puneSalaries.containsKey("Tech Lead"));
    assertEquals(12000.0, puneSalaries.get("Software Engineer"), 0.01);
    assertEquals(12000.0, puneSalaries.get("Recruiter"), 0.01);
    assertEquals(15000.0, puneSalaries.get("Tech Lead"), 0.01);

    // Verify average salaries for Bangalore
    Map<String, Double> bangaloreSalaries = result.get("Bangalore");
    assertNotNull(bangaloreSalaries);
    assertEquals(1, bangaloreSalaries.size()); // One designation: Recruiter
    assertTrue(bangaloreSalaries.containsKey("Recruiter"));
    assertEquals(16000.0, bangaloreSalaries.get("Recruiter"), 0.01);
  }

  @Test
  public void testCalculateAvgSalaryUsingParallelStream() {
    FindAverageSalaryApp app = new FindAverageSalaryApp();

    // Mock data creation
    List<Employee> employees =
        Arrays.asList(
            new Employee("Ashish", "A", "IT", "Pune", "Software Engineer", 10000.0),
            new Employee("Amit", "R", "HR", "Pune", "Recruiter", 12000.0),
            new Employee("Ramesh", "D", "HR", "Pune", "Senior Recruiter", 14000.0),
            new Employee("Jaya", "S", "IT", "Pune", "Tech Lead", 15000.0),
            new Employee("Smita", "M", "IT", "Bangalore", "Recruiter", 16000.0),
            new Employee("Umesh", "A", "IT", "Bangalore", "Software Engineer", 12000.0),
            new Employee("Pooja", "R", "HR", "Bangalore", "Software Engineer", 12000.0),
            new Employee("Ramesh", "D", "HR", "Pune", "Recruiter", 16000.0),
            new Employee("Bobby", "S", "IT", "Bangalore", "Tech Lead", 20000.0),
            new Employee("Vipul", "M", "IT", "Bangalore", "Software Engineer", 14000.0));

    Map<String, Map<String, Double>> result = app.calculateAvgSalaryUsingParallelStream(employees);

    // Verify the result for different test case scenarios
    assertEquals(2, result.size());
    assertEquals(4, result.get("Pune").size());
    assertEquals(3, result.get("Bangalore").size());
    assertEquals(10000.0, result.get("Pune").get("Software Engineer"), 0.01);
    assertEquals(14000.0, result.get("Pune").get("Senior Recruiter"), 0.1);
    assertEquals(16000.0, result.get("Bangalore").get("Recruiter"), 0.1);
    assertEquals(12666.67, result.get("Bangalore").get("Software Engineer"), 0.01);
  }
}
