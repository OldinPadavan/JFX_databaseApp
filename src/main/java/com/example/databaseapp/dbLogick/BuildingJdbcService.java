package com.example.databaseapp.dbLogick;

import com.example.databaseapp.dbLogick.mapper.BuildingMapper;
import com.example.databaseapp.dbLogick.model.Building;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class BuildingJdbcService {
    private JdbcConnectionService jdbcConnectionService;
    private BuildingMapper buildingMapper;

    public BuildingJdbcService() {
        this.jdbcConnectionService = new JdbcConnectionService();
        this.buildingMapper = new BuildingMapper();
    }

    public Collection<Building> findAllBuildings() {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM buildings");

            Collection<Building> buildings = new ArrayList<>();
            while(rs_set.next()) {
                buildings.add(buildingMapper.mapResultSet(rs_set));
            }
            return buildings;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllBuildings  method", exception);
        }
    }

    public Building findById(Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            String sql = "SELECT * FROM building WHERE id = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs_set = statement.executeQuery();
            if(rs_set.next()) {
                return buildingMapper.mapResultSet(rs_set);
            } else {
                return null;
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findById" + exception);
        }

    }

    public Building findByAddress(String building_address) {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            String sql = "SELECT * FROM building WHERE building_address = " + building_address;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs_set = statement.executeQuery();
            if(rs_set.next()) {
                return buildingMapper.mapResultSet(rs_set);
            } else {
                return null;
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findById" + exception);
        }

    }

    public Building createNewBuilding( String building_address, Double square, Integer floors_number, Integer management_company_id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "INSERT INTO buildings (building_address, square, floors_number, management_company_id) values ( ?, ?, ?, ?)";
            PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, building_address);
            stat.setDouble(2, square);
            stat.setInt(3, floors_number);
            stat.setInt(4, management_company_id);

            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            ResultSet keyRS = stat.getGeneratedKeys();

            keyRS.next();
            int generatedKey = keyRS.getInt(1);

            return Building.builder()
                    .id(generatedKey)
                    .building_address(building_address)
                    .square(square)
                    .floors_number(floors_number)
                    .management_company_id(management_company_id)
                    .build();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't create new building", exception);
        }

    }

    public void updateBuildingAddress(String building_address, Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "UPDATE building SET building_address = " + "'" + building_address + "'" + " WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);


        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't update building_address with id = " + id, exception);
        }

    }

    public void UpdateBuildingManagementComp(Integer management_company_id, Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "UPDATE building SET management_company_id = " + "'" + management_company_id + "'" + " WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);


        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't update management_company_id with id = " + id, exception);
        }

    }

    public void updateBuilding(Integer id, Double square, Integer floors_number, String building_address, Integer management_company_id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "UPDATE building SET square = " + square + ", floors_number = " + floors_number + ", building_address = " + "'" + building_address + "'" + ", management_company_id = " + management_company_id + " WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);


        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't update management_company with id = " + id, exception);
        }
    }

    public void deleteBuildingById(Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "DELETE FROM building WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute deleteBuildingById with id + " + id, exception );
        }
    }
}
