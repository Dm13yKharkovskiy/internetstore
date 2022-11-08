package dao.impl;

import dao.CartDao;
import model.Product;
import org.apache.log4j.Logger;
import utils.ConnectionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJDBCImpl implements CartDao {
    private final Logger logger = Logger.getLogger(CartDaoJDBCImpl.class);

    private static CartDaoJDBCImpl cartDao;


    public static CartDaoJDBCImpl getCartDao() {
        if (cartDao == null) {
            cartDao = new CartDaoJDBCImpl();
        }
        return cartDao;
    }


    @Override
    public void createCart(long userId) {
        String sqlQuery = "insert into carts (id_user) values (?)";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, userId);
            ps.executeUpdate();
            logger.info("Cart for user id: " + userId + " create success");
        } catch (SQLException e) {
            logger.error("Cart for user id: " + userId + " create failure");
        }

    }

    @Override
    public void addProduct(long cartId, long productId) {
        String sqlQuery = "insert into products_basket (id_cart, id_product) values (?, ?)";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, cartId);
            ps.setLong(2, productId);
            ps.executeUpdate();
            logger.info("Product id: " + productId + " add to cart " + cartId + " success");
        } catch (SQLException e) {
            logger.error("Product id: " + productId + " add to cart " + cartId + " failure");
        }
    }


    @Override
    public long getCartId(long userId) {
        String sqlQuery = "select id from cart where id_user = ?";
        long idCart = 0;
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idCart = rs.getLong("id");
            }
            return idCart;
        } catch (SQLException e) {

        }

        return idCart;
    }

    @Override
    public void removeProduct(long idCart, long productId) {
        String sqlQuery = "delete from products_basket where id_cart = ? and id_product = ?";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect
                     .prepareStatement(sqlQuery)) {
            ps.setLong(1, idCart);
            ps.setLong(2, productId);
            ps.executeUpdate();
            logger.info("Product id: " + productId + " remove from cart " + idCart + " success");
        } catch (SQLException e) {
            logger.error("Product id: " + productId + " remove from to cart " + idCart + " failure");
        }

    }

    @Override
    public List<Product> getProducts(long cartId) {
        String sqlQuery = "select b.id, b.product_name, b.price, b.description from products_basket as a join products as b on a.id_product = b.id  where id_cart = ? order by a.time_add desc";
        List<Product> listCart = new ArrayList<>();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long productId = rs.getLong("id");
                String productName = rs.getString("product_name").trim();
                double productPrice = rs.getDouble("price");
                String productDescription = rs.getString("description").trim();
                listCart.add(new Product(productId, productName, productPrice, productDescription));
            }
        } catch (SQLException e) {

        }

        return listCart;
    }

    @Override
    public void clearCart(long idCart) {
        String sqlQuery = "delete from products_basket where id_cart = ?";
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, idCart);
            ps.executeUpdate();
            ps.close();
            logger.info("Cart id: " + idCart + " clear " + "success");
        } catch (SQLException e) {
            logger.error("Cart id: " + idCart + " clear " + "failure");
        }
    }

    @Override
    public long countProductIntoCart(long idCart, long productId) {
        return 0;
    }
}
