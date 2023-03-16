package com.skypro.employeebook.controller;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.servise.DepartmentServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "departments")

public class DepartmentController {
    public DepartmentController(DepartmentServiceInterface departmentServiceInterface) {
        this.departmentServiceInterface = departmentServiceInterface;
    }

    private final DepartmentServiceInterface departmentServiceInterface;


    @GetMapping(path = "all")
    public Map<Integer, List<Employee>> getDepartment (@RequestParam(required = false) Integer departmentId){
        return departmentServiceInterface.getAllEmployeeByDepartment(departmentId);
    }
    @GetMapping(path = "max-salary")
    public Optional<Employee> getMaxSalary (@RequestParam("departmentId") Integer departmentId){
        return departmentServiceInterface.getMaxSalaryInDepartment(departmentId);
    }
    @GetMapping(path = "min-salary")
    public Optional<Employee> getMinSalary (@RequestParam("departmentId") Integer departmentId){
        return departmentServiceInterface.getMinSalaryInDepartment(departmentId);
    }

}
