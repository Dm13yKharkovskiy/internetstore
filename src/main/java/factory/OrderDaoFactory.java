package factory;

import dao.OrderDao;
import dao.UserDao;
import dao.impl.OrderDaoJDBCImpl;
import dao.impl.UserDaoJDBCImpl;

public class OrderDaoFactory {
    private OrderDaoFactory() {
    }

    private static volatile OrderDao orderDao;

    static {
        getInstance();
    }
    public static OrderDao getOrderDao() {
        return orderDao;
    }

    private static synchronized OrderDao getInstance() {
        OrderDao localInstance = orderDao;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = orderDao;
                if (localInstance == null) {
                    orderDao = localInstance = new OrderDaoJDBCImpl();
                }
            }
        }
        return localInstance;
    }
}
