package service.impl;

import dao.CartDao;
import dao.impl.CartDaoJDBCImpl;
import factory.CartDaoFactory;
import model.Product;
import service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {

    private final CartDao CART_DAO = CartDaoFactory.getCartDao();

    private static CartServiceImpl cartService;


    public static CartServiceImpl getCartService() {
        if (cartService == null) {
            cartService = new CartServiceImpl();
        }
        return cartService;
    }

    @Override
    public void createCart(long userId) {
        if (CART_DAO.getCartId(userId) != 0) {
            CART_DAO.createCart(userId);
        }
    }

    @Override
    public void addProduct(long userId, long productId) {
        long cartId = CART_DAO.getCartId(userId);
        if (cartId != 0){
            CART_DAO.addProduct(cartId, productId);
        }
    }

    @Override
    public long getCartId(long userId) {
        return CART_DAO.getCartId(userId);
    }

    @Override
    public void clearCart(long idCart) {
        CART_DAO.clearCart(idCart);
    }

    @Override
    public void removeProduct(long userId, long productId) {
        long cartId = CART_DAO.getCartId(userId);
        if (cartId != 0){
            CART_DAO.removeProduct(cartId, productId);
        }
    }

    @Override
    public List<Product> getProducts(long userId) {
        long cartId = CART_DAO.getCartId(userId);
        return CART_DAO.getProducts(cartId);
    }

    public double getAmountCart(List<Product> products) {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}
