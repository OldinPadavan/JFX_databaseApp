package com.example.databaseapp.dbLogick;


import com.example.databaseapp.dbLogick.mapper.OwnerMapper;
import com.example.databaseapp.dbLogick.model.Owner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OwnerJdbcService {
    private JdbcConnectionService jdbcConnectionService;
    private OwnerMapper ownerMapper;

    public OwnerJdbcService() {
        this.jdbcConnectionService = new JdbcConnectionService();
        this.ownerMapper = new OwnerMapper();
    }

    public Collection<Owner> findAllOwners() {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM owner");

            Collection<Owner> owners = new ArrayList<>();
            while(rs_set.next()) {
                owners.add(ownerMapper.mapResultSet(rs_set));
            }
            return owners;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllOwners method", exception);
        }
    }

    public Owner findById(Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM owner WHERE id = " + id);


            return ownerMapper.mapResultSet(rs_set);

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findById method", exception);
        }
    }

    public Owner createNewOwner(String last_name, String first_name, String middle_name) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "INSERT INTO owner (last_name, first_name, middle_name) values ( ?, ?, ?)";
            PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, last_name);
            stat.setString(2, first_name);
            stat.setString(3, middle_name);

            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            ResultSet keyRS = stat.getGeneratedKeys();

            keyRS.next();
            int generatedKey = keyRS.getInt(1);

            return Owner.builder()
                    .id(generatedKey)
                    .last_name(last_name)
                    .first_name(first_name)
                    .middle_name(middle_name)
                    .build();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't create new owner", exception);
        }

    }

    public void updateOwner(Integer id, String last_name, String first_name, String middle_name) {
        try (Connection connection = jdbcConnectionService.openConnection()) {

                String sql = "UPDATE owner SET last_name = ?, first_name = ?, middle_name = ? WHERE flat.id = ?";
                PreparedStatement stat = connection.prepareStatement(sql);
                stat.setString(1, last_name);
                stat.setString(2, first_name);
                stat.setString(3, middle_name);
                stat.setInt(4, id);

                int affectedRows = stat.executeUpdate();
                System.out.println("Affected rows: " + affectedRows);


        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't update Owner with id = " + id, exception);
        }

    }

    public void deleteOwnerById(int id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "DELETE FROM OWNER WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);

        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't delete owner", exception);
        }
    }

}
