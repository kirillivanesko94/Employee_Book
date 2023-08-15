package com.skypro.employeebook.controller;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.service.DepartmentServiceInterface;
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

    /**
     * Getting all department employees if id is specified, or all employees across all departments if id is not specified
     * @param departmentId ID department
     */
    @GetMapping(path = "all")
    public Map<Integer, List<Employee>> getDepartment (@RequestParam(required = false) Integer departmentId){
        return departmentServiceInterface.getAllEmployeeByDepartment(departmentId);
    }

    /**
     * Getting an employee with the maximum salary in department
     * @param departmentId ID department
     * @return {@link Employee} with maximum salary in department
     */
    @GetMapping(path = "max-salary")
    public Optional<Employee> getMaxSalary (@RequestParam("departmentId") Integer departmentId){
        return departmentServiceInterface.getMaxSalaryInDepartment(departmentId);
    }
    /**
     * Getting an employee with the minimum salary in department
     * @param departmentId ID department
     * @return {@link Employee} with minimum salary in department
     */
    @GetMapping(path = "min-salary")
    public Optional<Employee> getMinSalary (@RequestParam("departmentId") Integer departmentId){
        return departmentServiceInterface.getMinSalaryInDepartment(departmentId);
    }

}
