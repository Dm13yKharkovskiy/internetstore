package factory;

import dao.ProductDao;
import dao.UserDao;
import dao.impl.ProductDaoJDBCImpl;
import dao.impl.UserDaoJDBCImpl;

public class ProductDaoFactory {
    private ProductDaoFactory() {
    }

    private static volatile ProductDao productDao;

    static {
        getInstance();
    }
    public static ProductDao getProductDao() {
        return productDao;
    }

    private static synchronized ProductDao getInstance() {
        ProductDao localInstance = productDao;
        if (localInstance == null) {
            synchronized (ProductDao.class) {
                localInstance = productDao;
                if (localInstance == null) {
                    productDao = localInstance = new ProductDaoJDBCImpl();
                }
            }
        }
        return localInstance;
    }
}
