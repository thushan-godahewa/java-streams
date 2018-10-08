package com.tsg.jstreams.streams;

import com.github.javafaker.Faker;
import com.tsg.jstreams.model.Employee;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    private Faker faker = new Faker();

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String employeeId1 = faker.idNumber().ssnValid();
    private String employeeId2 = faker.idNumber().ssnValid();
    private String employeeId3 = faker.idNumber().ssnValid();

    private int accesskey1 = 9534;
    private int accesskey2 = 3521;
    private int accesskey3 = 152;

    private String careerChampionId1 = UUID.randomUUID().toString();
    private String careerChampionId2 = UUID.randomUUID().toString();

    private Employee employee1_1 = new Employee(employeeId1, accesskey1, careerChampionId1
            , faker.address().fullAddress(), 0
            , LocalDateTime.parse("2017-05-30 10:33:28", DATE_TIME_FORMATTER));
    private Employee employee1_2 = new Employee(employeeId1, accesskey1, careerChampionId1
            , faker.address().fullAddress(), 0
            , LocalDateTime.parse("2013-11-12 11:33:45", DATE_TIME_FORMATTER));
    private Employee employee1_3 = new Employee(employeeId1, accesskey1, careerChampionId1
            , faker.address().fullAddress(), 0
            , LocalDateTime.parse("2018-01-04 09:13:28", DATE_TIME_FORMATTER));

    private Employee employee2_1 = new Employee(employeeId2, accesskey2, careerChampionId2
            , faker.address().fullAddress(), 0
            , LocalDateTime.parse("2017-10-10 22:52:28", DATE_TIME_FORMATTER));
    private Employee employee2_2 = new Employee(employeeId2, accesskey2, careerChampionId2
            , faker.address().fullAddress(), 1
            , LocalDateTime.parse("2009-11-25 17:51:11", DATE_TIME_FORMATTER));

    private Employee employee3_1 = new Employee(employeeId3, accesskey3, careerChampionId1
            , faker.address().fullAddress(), 0
            , LocalDateTime.parse("2008-02-11 10:11:22", DATE_TIME_FORMATTER));
    private Employee employee3_2 = new Employee(employeeId3, accesskey3, careerChampionId1
            , faker.address().fullAddress(), 0
            , LocalDateTime.parse("2011-11-12 20:14:15", DATE_TIME_FORMATTER));
    private Employee employee3_3 = new Employee(employeeId3, accesskey3, careerChampionId1
            , faker.address().fullAddress(), 0
            , LocalDateTime.parse("2003-01-31 15:33:09", DATE_TIME_FORMATTER));

    @Test
    public void testGroupByJustOneField() throws Exception{
        List<Employee> listOfAllEmployees = getListOfAllEmployees();

        Map<String, List<Employee>> listOfEmployeesGroupByEmployeeId = listOfAllEmployees.stream().collect(Collectors.groupingBy(Employee::getId));

        for (Map.Entry<String, List<Employee>> entry : listOfEmployeesGroupByEmployeeId.entrySet()) {
            String employeeId = entry.getKey();
            List<Employee> listOfEmployees = entry.getValue();
            System.out.println(employeeId);
            printList(listOfEmployees);
        }
    }

    @Test
    public void testGroupByMultipleFieldClass() throws Exception{
        List<Employee> listOfAllEmployees = getListOfAllEmployees();

        Map<Employee.GroupingCombination, List<Employee>> listOfEmployeesGroupByEmployeeId = listOfAllEmployees.stream().collect(Collectors.groupingBy(Employee::getGroupingCombination));

        for (Map.Entry<Employee.GroupingCombination, List<Employee>> entry : listOfEmployeesGroupByEmployeeId.entrySet()) {
            Employee.GroupingCombination groupingCombination = entry.getKey();
            List<Employee> listOfEmployees = entry.getValue();
            System.out.println(groupingCombination.toString());
            printList(listOfEmployees);
        }
    }

    @Test
    public void testGroupByMultipleFieldString() throws Exception{
        List<Employee> listOfAllEmployees = getListOfAllEmployees();

        Map<String, List<Employee>> listOfEmployeesGroupByEmployeeId = listOfAllEmployees.stream().collect(Collectors.groupingBy(Employee::getGroupingCombinationString));

        List<Employee> listOfSubSetEmployees = new ArrayList<>();

        for (Map.Entry<String, List<Employee>> entry : listOfEmployeesGroupByEmployeeId.entrySet()) {
            String groupingCombination = entry.getKey();
            List<Employee> listOfEmployees = entry.getValue();
            boolean foundDefaultAddress = false;
            for(Employee employee : listOfEmployees){
                if(employee.getDefaultAddress() == 1){
                    foundDefaultAddress = true;
                    listOfSubSetEmployees.add(employee);
                    break;
                }
            }
            if(foundDefaultAddress == false){
                Employee employeeWithMaxLastUpdatedDate = Collections.max(listOfEmployees, Comparator.comparing(Employee::getLastUpdated));
                listOfSubSetEmployees.add(employeeWithMaxLastUpdatedDate);
            }
        }

        printList(listOfSubSetEmployees);
    }

    private List<Employee> getListOfAllEmployees(){
        List<Employee> listOfAllEmployees = new ArrayList<>();

        listOfAllEmployees.add(employee1_1);
        listOfAllEmployees.add(employee1_2);
        listOfAllEmployees.add(employee1_3);

        listOfAllEmployees.add(employee2_1);
        listOfAllEmployees.add(employee2_2);

        listOfAllEmployees.add(employee3_1);
        listOfAllEmployees.add(employee3_2);
        listOfAllEmployees.add(employee3_3);

        return listOfAllEmployees;
    }

    private void printList(List<Employee> list){
        for(Employee employee : list){
            System.out.println("\t"+employee.toString());
        }
    }
}
