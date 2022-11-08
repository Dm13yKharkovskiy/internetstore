package factory;

import dao.impl.CartDaoJDBCImpl;

public class CartDaoFactory {
    private CartDaoFactory() {
    }

    private static volatile CartDaoJDBCImpl cartDao;

    static {
        getInstance();
    }
    public static CartDaoJDBCImpl getCartDao() {
        return cartDao;
    }

    private static synchronized CartDaoJDBCImpl getInstance() {
        CartDaoJDBCImpl localInstance = cartDao;
        if (localInstance == null) {
            synchronized (CartDaoJDBCImpl.class) {
                localInstance = cartDao;
                if (localInstance == null) {
                    cartDao = localInstance = new CartDaoJDBCImpl();
                }
            }
        }
        return localInstance;
    }
}
