package com.skypro.employeebook.controller;

import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.servise.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "department")

public class DepartmentController {
    public DepartmentController(DepartmentService departmentServiceInterface) {
        this.departmentServiceInterface = departmentServiceInterface;
    }

    private final DepartmentService departmentServiceInterface;


    @GetMapping(path = "{id}/employees")
    public List<Employee> getDepartment(@PathVariable("id") Integer id) {

        return departmentServiceInterface.getAllEmployeeByDepartment(id);
    }

    @GetMapping(path = "{id}/salary/max")
    public double getMaxSalary(@PathVariable("id") Integer id) {
        return departmentServiceInterface.getMaxSalaryInDepartment(id);
    }

    @GetMapping(path = "{id}/salary/min")
    public double getMinSalary(@PathVariable("id") Integer id) {
        return departmentServiceInterface.getMinSalaryInDepartment(id);
    }

    @GetMapping(path = "{id}/salary/sum")
    public double getSumSalary(@PathVariable("id") Integer id) {
        return departmentServiceInterface.getSumSalaryInDepartment(id);
    }
    @GetMapping(path = "employees")
    public Map<Integer,List<Employee>> getAllEmployees () {
        return departmentServiceInterface.getAllEmployees();
    }

}
