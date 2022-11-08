package service.impl;


import factory.ProductDaoFactory;
import service.ProductService;
import dao.ProductDao;
import model.Product;
import utils.HashUtils;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductDao PRODUCT_DAO = ProductDaoFactory.getProductDao();

    @Override
    public void addProduct(Product product) {
        PRODUCT_DAO.addProduct(product);
    }


    @Override
    public void removeProduct(long id) {
        PRODUCT_DAO.removeProduct(id);
    }

    @Override
    public List<Product> getCatalog() {
        return PRODUCT_DAO.getCatalog();
    }

    @Override
    public Optional getProductForId(long idProduct) {
        return PRODUCT_DAO.getProductForId(idProduct);
    }

    @Override
    public void updateProduct(Product product) {
        PRODUCT_DAO.updateProduct(product);
    }

}
