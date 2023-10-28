package com.example.databaseapp.dbLogick;


import com.example.databaseapp.dbLogick.mapper.BillsMapper;
import com.example.databaseapp.dbLogick.model.Bills;
import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class BillsJdbcService {
    private JdbcConnectionService jdbcConnectionService;
    private BillsMapper billsMapper;

    public BillsJdbcService() {
        this.jdbcConnectionService = new JdbcConnectionService();
        this.billsMapper = new BillsMapper();
    }

    public Collection<Bills> findAllBills() {
        try (Connection connection = jdbcConnectionService.openConnection()) {

            Statement stat = connection.createStatement();
            ResultSet rs_set = stat.executeQuery("SELECT * FROM bills");

            Collection<Bills> bills = new ArrayList<>();
            while(rs_set.next()) {
                bills.add(billsMapper.mapResultSet(rs_set));
            }
            return bills;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllBills method", exception);
        }
    }
    public Collection<Bills> findAllFlatBills(Integer flat_id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "SELECT * FROM bills,flat WHERE bills.flat_id = flat.id";
            PreparedStatement stat = connection.prepareCall(sql);
            ResultSet rs_set = stat.executeQuery();

            Collection<Bills> bills = new ArrayList<>();
            while(rs_set.next()) {
                bills.add(billsMapper.mapResultSet(rs_set));
            }
            return bills;

        }catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute findAllFlatBills method", exception);
        }
    }

    public Bills createNewBill(OffsetDateTime date, Double amount, String link, Integer flat_id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "INSERT INTO bills (date, amount, link, flat_id )values ( ?, ?, ?, ?)";
            PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setDate(1, new Date(date.toInstant().toEpochMilli()));
            stat.setDouble(2, amount);
            stat.setString(3, link);
            stat.setInt(4, flat_id);

            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            ResultSet keyRS = stat.getGeneratedKeys();

            keyRS.next();
            int generatedKey = keyRS.getInt(1);

            return Bills.builder()
                    .id(generatedKey)
                    .date(date)
                    .amount(amount)
                    .link(link)
                    .flat_id(flat_id)
                    .build();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't create new bill", exception);
        }

    }

    public void updateBillById(Integer id, OffsetDateTime date, Double  amount, String link, Integer flat_id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "UPDATE  SET date = ?, amount = ?, link = ?, flat_id = ?, WHERE id = ?";
            PreparedStatement stat = connection.prepareStatement(sql);
            int affectedRows = stat.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);


        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't update management_company with id = " + id, exception);
        }
    }

    public void deleteBillById(Integer id) {
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "DELETE FROM bills WHERE id = " + id;
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't execute deleteBillById with id + " + id, exception );
        }
    }

}
