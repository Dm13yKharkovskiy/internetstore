package dao.impl;


import dao.OrderDao;
import model.Code;
import model.Order;
import org.apache.log4j.Logger;
import utils.ConnectionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoJDBCImpl implements OrderDao {

    private Logger logger = Logger.getLogger(OrderDaoJDBCImpl.class);

    private static OrderDaoJDBCImpl orderDao;


    public static OrderDaoJDBCImpl getUserService() {
        if (orderDao == null) {
            orderDao = new OrderDaoJDBCImpl();
        }
        return orderDao;
    }


    @Override
    public void createOrder(Order order) {
        String sqlQuery = "insert into orders (id_user, amount_order, " +
                "status_order, list_product_id, count_products, confirm_code) values (?, ?, ?, ?, ?, ?)";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, order.getIdUser());
            ps.setDouble(2, order.getAmountOrder());
            ps.setString(3, order.getStatusOrder());
            ps.setString(4, order.getListProductId());
            ps.setLong(5, order.getCountProducts());
            ps.setInt(6, order.getConfirmCode());
            ps.executeUpdate();
            logger.info("Order create success ");
        } catch (SQLException e) {
            logger.error("Order create failure ");
        }


    }

    @Override
    public List<Order> getOrders(long userId) {
        String sqlQuery = "select * from orders where id_user = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long idOrder = rs.getLong("id");
                long idUser = rs.getLong("id_user");
                double amountOrder = rs.getDouble("amount_order");
                String statusOrder = rs.getString("status_order").trim();
                String listProductId = rs.getString("list_product_id").trim();
                long countProducts = rs.getLong("count_products");
                int confirmCode = rs.getInt("count_products");
                orders.add(new Order(idOrder, idUser, amountOrder, countProducts, statusOrder, listProductId, confirmCode));
            }
        } catch (SQLException e) {
        }
        return orders;
    }

    @Override
    public void setConfirmCode(Code code) {
        String sqlQuery = "update orders set confirm_code = ? where id = ?";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setInt(1, code.getCode());
            ps.setLong(2, code.getIdOrder());
            ps.executeUpdate();
            logger.info("Confirm code to orders  " + code.getIdOrder() + " set success");
        } catch (SQLException e) {
            logger.error("Confirm code to orders  " + code.getIdOrder() + " set failure");
        }
    }

    @Override
    public void updateConfirmCode(Code code) {

    }

    @Override
    public Optional getConfirmCode(Code code) {
        String sqlQuery = "select * from orders where id = ?";
        Optional<Code> codeDb = Optional.empty();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, code.getIdOrder());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long idOrder = rs.getLong("id");
                int confirmCode = rs.getInt("confirm_code");
                codeDb = Optional.of(new Code(idOrder, confirmCode));
            }
        } catch (SQLException e) {

        }
        return codeDb;
    }

    @Override
    public void changeStatusOrder(String status, long idOrder) {
        String sqlQuery = "update orders set status_order = ? where id = ?";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setString(1, status);
            ps.setLong(2, idOrder);
            ps.executeUpdate();
            logger.info("Change status order id   " + idOrder + " success");
        } catch (SQLException e) {
            logger.error("Change status order id   " + idOrder + " failure");
        }

    }

    @Override
    public void createProductListIdToString() {

    }

    @Override
    public void createProductListNameToString() {

    }
}
