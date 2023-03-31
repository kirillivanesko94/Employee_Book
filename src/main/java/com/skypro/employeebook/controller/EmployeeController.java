package com.skypro.employeebook.controller;
import com.skypro.employeebook.exception.EmployeeValidFullNameException;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.exception.EmployeeAlreadyAddedException;
import com.skypro.employeebook.exception.EmployeeNotFoundException;
import com.skypro.employeebook.exception.EmployeeStorageIsFullException;
import com.skypro.employeebook.servise.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("employee")

public class EmployeeController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmployeeStorageIsFullException.class, EmployeeAlreadyAddedException.class, EmployeeValidFullNameException.class})
    public String handleException(RuntimeException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException (EmployeeNotFoundException e){
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    private final EmployeeService employeeServiceInterface;

    public EmployeeController(EmployeeService employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }

    @GetMapping(path = "add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("passport") Integer passport,
                        @RequestParam("salary") double salary,
                        @RequestParam("department") Integer department)  {

        return employeeServiceInterface.addEmployee(firstName, lastName, passport, salary, department);
    }

    @GetMapping(path = "remove")
    public String remove(@RequestParam("passport") Integer passport) {

       return employeeServiceInterface.removeEmployee(passport);
    }

    @GetMapping(path = "find")
    public Employee find(@RequestParam("passport") Integer passport) {

        return employeeServiceInterface.findEmployee(passport);
    }
    @GetMapping(path = "getAll")
    public Map<Integer, Employee> getAll(){
        return employeeServiceInterface.getAllEmployees();
    }

}
