package com.example.databaseapp.dbLogick;

import com.example.databaseapp.dbLogick.mapper.FlatMapper;
import com.example.databaseapp.dbLogick.model.Flat;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FlatJdbcService {
    private JdbcConnectionService jdbcConnectionService;
    private FlatMapper flatMapper;

    public FlatJdbcService() {
        this.jdbcConnectionService = new JdbcConnectionService();
        this.flatMapper = new FlatMapper();
    }

    public Collection<Flat> findAllFlats() {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM flat");

            Collection<Flat> flats = new ArrayList<>();
            while(rs_set.next()) {
                flats.add(flatMapper.mapResultSet(rs_set));
            }
            return flats;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllFlats method", exception);
        }
    }
    public Collection<Flat> findAllFlatsInBuilding(Integer building_id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "SELECT * FROM flat, building WHERE building.id = flat.building_id";
            PreparedStatement stat = connection.prepareStatement(sql);
            ResultSet rs_set = stat.executeQuery();

            Collection<Flat> flats = new ArrayList<>();
            while(rs_set.next()) {
                flats.add(flatMapper.mapResultSet(rs_set));
            }
            return flats;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllFlatsInBuilding method", exception);
        }
    }

    public Flat createNewFlat(String flat_address, Integer flat_owner_id, Double flat_square, Integer building_id, Integer flat_number) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "INSERT INTO flat (flat_address, flat_owner_id, flat_square, building_id, flat_number) values (?, ?, ?, ?, ?)";
            PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, flat_address);
            stat.setInt(2, flat_owner_id);
            stat.setDouble(3, flat_square);
            stat.setInt(4, building_id);
            stat.setInt(5, flat_number);

            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            ResultSet keyRS = stat.getGeneratedKeys();

            keyRS.next();
            int generatedKey = keyRS.getInt(1);

            return Flat.builder()
                    .id(generatedKey)
                    .flat_address(flat_address)
                    .flat_owner_id(flat_owner_id)
                    .flat_square(flat_square)
                    .building_id(building_id)
                    .flat_number(flat_number)
                    .build();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't create new flat", exception);
        }

    }

    public void updateFlatInfo(Integer id, String flat_address, Integer flat_owner_id, Double flat_square, Integer building_id, Integer flat_number) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "UPDATE management_company SET flat_address = ?, flat_owner_id = ?, flat_square = ?, building_id = ?, flat_number = ? WHERE flat.id = ?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, flat_address);
            stat.setInt(2, flat_owner_id);
            stat.setDouble(3, flat_square);
            stat.setInt(4, building_id);
            stat.setInt(5, flat_number);
            stat.setInt(6, id);

            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);


        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't update flat with id = " + id, exception);
        }
    }

    public void deleteFlatById(Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "DELETE FROM flat WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute deleteFlatById with id + " + id, exception );
        }
    }
}
