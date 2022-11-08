package factory;

import dao.ProductDao;
import dao.impl.ProductDaoJDBCImpl;
import service.ProductService;
import service.impl.ProductServiceImpl;

public class ProductServiceFactory {
    private ProductServiceFactory() {
    }

    private static volatile ProductServiceImpl productServiceImpl;

    static {
        getInstance();
    }
    public static ProductServiceImpl getProductServiceImpl() {
        return productServiceImpl;
    }

    private static synchronized ProductServiceImpl getInstance() {
        ProductServiceImpl localInstance = productServiceImpl;
        if (localInstance == null) {
            synchronized (ProductServiceImpl.class) {
                localInstance = productServiceImpl;
                if (localInstance == null) {
                    productServiceImpl = localInstance = new ProductServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
