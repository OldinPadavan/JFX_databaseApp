package com.example.databaseapp.dbLogick;

import com.example.databaseapp.dbLogick.mapper.Management_companyMapper;
import com.example.databaseapp.dbLogick.model.Management_company;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Management_companyJdbcService {
    private JdbcConnectionService jdbcConnectionService;
    private Management_companyMapper companyMapper;

    public Management_companyJdbcService() {
        this.jdbcConnectionService = new JdbcConnectionService();
        this.companyMapper = new Management_companyMapper();
    }

    public Collection<Management_company> findAllManagement_companies() {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM management_company");

            Collection<Management_company> companies = new ArrayList<>();
            while(rs_set.next()) {
                companies.add(companyMapper.mapResultSet(rs_set));
            }
            return companies;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllManagement_companies method", exception);
        }
    }

    public Management_company findById(Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM management_company WHERE id = " + id);

            if(rs_set.next()) {
                return companyMapper.mapResultSet(rs_set);
            } else {
                return null;
            }

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findById", exception);
        }
    }

    public Management_company findByName(String name) {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM management_company WHERE name = " + "'" + name + "'");

            if(rs_set.next()) {
                return companyMapper.mapResultSet(rs_set);
            } else {
                return null;
            }

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findById", exception);
        }
    }

    public void updateManagement_company(Integer id, String name, Integer inn, String address, String phone) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "UPDATE management_company SET name = " + "'" + name + "'" + ", inn = " + inn + ", address = " + "'" + address + "'" +", phone = " + "'" + phone + "'" + " WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);


        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't update management_company with id = " + id, exception);
        }
    }



    public void createNewManagement_company(String name, Integer inn, String address, String phone) {
         try (Connection connection = jdbcConnectionService.openConnection()) {
             String sql = "INSERT INTO management_company (name, inn, address, phone) values (?, ?, ?, ?)";
             PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             stat.setString(1, name);
             stat.setInt(2, inn);
             stat.setString(3, address);
             stat.setString(4, phone);

             int affectedRows = stat.executeUpdate();
             System.out.println("Affected rows: " + affectedRows);

             ResultSet keyRS = stat.getGeneratedKeys();

             keyRS.next();
             int generatedKey = keyRS.getInt(1);

         } catch (SQLException exception) {
             throw new RuntimeException("Couldn't create new management_company", exception);
         }

    }


}
