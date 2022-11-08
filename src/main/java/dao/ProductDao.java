package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    void addProduct(Product product);

    void updateProduct(Product product);

    Optional getProductForId(long idProduct);

    void removeProduct(long id);

    List<Product> getCatalog();

}
