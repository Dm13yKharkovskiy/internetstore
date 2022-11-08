package dao;

import model.Product;
import java.util.List;

public interface CartDao {
    void createCart(long userId);
    void addProduct(long cartId, long productId);

    long getCartId(long userId);
    void removeProduct(long idCart, long productId);

    List<Product> getProducts(long cartId);

    void clearCart(long idCart);
    long countProductIntoCart(long idCart, long productId);
}
