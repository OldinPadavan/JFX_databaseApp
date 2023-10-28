package com.example.databaseapp.appUtils;

import com.example.databaseapp.dbLogick.EmployeesJdbcService;
import com.example.databaseapp.dbLogick.model.Employees;

public class LogInUtils {
    private Integer id;

    private String password;

    public LogInUtils (Integer id, String password) {
        this.id = id;
        this.password = new EncryptionUtil().encryptString(password);
    }

    public boolean checkInputInfo() {
        Employees employee = new EmployeesJdbcService().findEmployeeById(id);
        if (employee != null) {
            if (employee.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
