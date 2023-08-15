package com.skypro.employeebook.controller;
import com.skypro.employeebook.exception.EmployeeValidFullNameException;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.exception.EmployeeAlreadyAddedException;
import com.skypro.employeebook.exception.EmployeeNotFoundException;
import com.skypro.employeebook.exception.EmployeeStorageIsFullException;
import com.skypro.employeebook.service.EmployeeServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("employee")

public class EmployeeController {
    /**
     * Error handling. All responses with the BAD_REQUEST status will use the message passed in the constructors of the following classes:
     * {@link EmployeeValidFullNameException},
     * {@link EmployeeStorageIsFullException},
     * {@link EmployeeAlreadyAddedException},
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmployeeStorageIsFullException.class, EmployeeAlreadyAddedException.class, EmployeeValidFullNameException.class})
    public String handleException(RuntimeException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    /**
     * Error handling. All responses with the NOT_FOUND status will use the message passed in the constructors of the following class -
     * {@link EmployeeNotFoundException}
     */

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException (EmployeeNotFoundException e){
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    private final EmployeeServiceInterface employeeServiceInterface;

    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }

    /**
     * Added employee
     */

    @GetMapping(path = "add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("passport") Integer passport,
                        @RequestParam("salary") double salary,
                        @RequestParam("department") Integer department)  {

        return employeeServiceInterface.addEmployee(firstName, lastName, passport, salary, department);
    }

    /**
     * Remove employee
     */

    @GetMapping(path = "remove")
    public String remove(@RequestParam("passport") Integer passport) {

       return employeeServiceInterface.removeEmployee(passport);
    }

    /**
     * Find employee
     * @return {@link Employee}
     */

    @GetMapping(path = "find")
    public Employee find(@RequestParam("passport") Integer passport) {

        return employeeServiceInterface.findEmployee(passport);
    }

    /**
     * Getting all employee
     */
    @GetMapping(path = "getAll")
    public Map<Integer, Employee> getAll(){
        return employeeServiceInterface.getAllEmployees();
    }

}
