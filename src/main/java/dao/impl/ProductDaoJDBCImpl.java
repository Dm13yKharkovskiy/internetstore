package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import utils.ConnectionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoJDBCImpl implements ProductDao {

    private final Logger logger = Logger.getLogger(ProductDaoJDBCImpl.class);

    @Override
    public void addProduct(Product product) {
        String sqlQuery = "insert into products (product_name, price, description) values (?, ?, ?)";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.executeUpdate();
            logger.info("Product " + product.getName() + " add to DB success");
        } catch (SQLException e) {
            logger.error("Product " + product.getName() + " add to DB failure");
        }
    }

    @Override
    public void updateProduct(Product product) {
        String sqlQuery = "update products set product_name = ?, price = ?, description = ? where id = ?";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setLong(4, product.getId());
            boolean a = ps.execute();
            logger.info("Product " + product.getId() + " update success");
        } catch (SQLException e) {
            logger.error("Product " + product.getId() + " update failure");
        }
    }

    @Override
    public Optional getProductForId(long idProduct) {
        String sqlQuery = "select * from products where id = ?";
        Optional<Product> product = Optional.empty();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, idProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("product_name").trim();
                double price = rs.getDouble("price");
                String description = rs.getString("description").trim();
                product = Optional.of(new Product(id, name, price, description));
            }
            logger.info("Product find from DB success");
        } catch (SQLException e) {
            logger.error("Product find from DB failure");
        }
        return product;
    }

    @Override
    public void removeProduct(long id) {
        String sqlQuery = "delete from products where id = ?";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            logger.info("Product id " + id + " remove from DB success");
        } catch (SQLException e) {
            logger.error("Product id " + id + " remove from DB failure");
        }
    }

    @Override
    public List<Product> getCatalog() {
        String sqlQuery = "select * from products order by id asc";
        List<Product> productList = new ArrayList<>();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            productList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("product_name").trim();
                double price = rs.getDouble("price");
                String description = rs.getString("description").trim();
                Product product = new Product(id, name, price, description);
                productList.add(product);
            }
        } catch (SQLException e) {

        }
        return productList;
    }

}
