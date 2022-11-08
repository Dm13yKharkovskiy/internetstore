package service;

import model.Product;

import java.util.List;

public interface CartService {
    void createCart(long userId);

    void addProduct(long userId, long productId);

    long getCartId(long userId);

    void clearCart(long idCart);

    void removeProduct(long userId, long productId);

    List<Product> getProducts(long userId);

    double getAmountCart(List<Product> products);
}
