package com.example.databaseapp.dbLogick;

import com.example.databaseapp.dbLogick.mapper.EmployeesMapper;
import com.example.databaseapp.dbLogick.model.Employees;
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;



public class EmployeesJdbcService {
    private JdbcConnectionService jdbcConnectionService;
    private EmployeesMapper employeesMapper;

    public EmployeesJdbcService() {
        this.jdbcConnectionService = new JdbcConnectionService();
        this.employeesMapper = new EmployeesMapper();
    }

    public Collection<Employees> findAllEmployees() {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM company_employees");

            Collection<Employees> employees = new ArrayList<>();
            while(rs_set.next()) {
                employees.add(employeesMapper.mapResultSet(rs_set));
            }
            return employees;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllEmployees method", exception);
        }
    }

    public Employees findEmployeeById(Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            String sql = "SELECT * FROM company_employees WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            ResultSet rs_set = stat.executeQuery();

            if (rs_set.next()) {
                return employeesMapper.mapResultSet(rs_set);
            } else {
                return null;
            }

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findEmployeeById method", exception);
        }
    }

    public Employees createNewEmployee(String password, String last_name, String first_name, String middle_name, Integer company_id, String worker_role) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "INSERT INTO company_employees (password, last_name, first_name, middle_name, company_id, worker_role)values ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, password);
            stat.setString(2, last_name);
            stat.setString(3, first_name);
            stat.setString(4, middle_name);
            stat.setInt(5, company_id);
            stat.setString(6, worker_role);

            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            ResultSet keyRS = stat.getGeneratedKeys();

            keyRS.next();
            int generatedKey = keyRS.getInt(1);

            return Employees.builder()
                    .id(generatedKey)
                    .password(password)
                    .last_name(last_name)
                    .first_name(first_name)
                    .middle_name(middle_name)
                    .company_id(company_id)
                    .worker_role(worker_role)
                    .build();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't create new employee", exception);
        }

    }

}
