package org.naumenHW;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Task3 {
    public void getSortAscSalary() {
        List<Employee> employees = new ArrayList<>(5);
        employees.add(new Employee("Печенькин Петр Иванович", 32, "IT", 150000.0));
        employees.add(new Employee("Супов Иван Александрович", 25, "HR", 75000.0));
        employees.add(new Employee("Рисова Анна Владимировна", 24, "Finance", 140000.0));
        employees.add(new Employee("Салатиков Алексей Сергеевич", 35, "IT", 120000.0));
        employees.add(new Employee("Котлеткина Екатерина Дмитриевна", 20, "HR", 56000.0));

        System.out.println("Исходный список сотрудников:");
        employees.forEach(System.out::println);

        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .toList();

        System.out.println("\nОтсортированный список сотрудников по зарплате:");
        sortedEmployees.forEach(System.out::println);
    }
}
