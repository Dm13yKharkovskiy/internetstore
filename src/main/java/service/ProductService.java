package service;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getCatalog();

    Optional getProductForId(long idProduct);

    void updateProduct(Product product);

    void addProduct(Product product);

    void removeProduct(long id);
}
