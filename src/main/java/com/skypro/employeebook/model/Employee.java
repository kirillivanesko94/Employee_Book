package com.skypro.employeebook.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * A class representing the employee entity
 */
    public class Employee {
        private final String firstName;
        private final String lastName;
        private final Integer passport;
        private final double salary;
        private final Integer department;


        public Employee(String firstName, String lastName, Integer passport, double salary, Integer department) {
            this.firstName = StringUtils.capitalize(firstName.toLowerCase());
            this.lastName = StringUtils.capitalize(lastName.toLowerCase());
            this.passport = passport;
            this.salary = salary;
            this.department = department;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public Integer getPassport() {
            return passport;
        }

        public double getSalary() {
            return salary;
        }

        public Integer getDepartment() {
            return department;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return Double.compare(employee.salary, salary) == 0 && Objects.equals(firstName, employee.firstName)
                    && Objects.equals(lastName, employee.lastName)
                    && Objects.equals(passport, employee.passport)
                    && Objects.equals(department, employee.department);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, passport, salary, department);
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", passport=" + passport +
                    ", salary=" + salary +
                    ", department=" + department +
                    '}';
        }
    }
