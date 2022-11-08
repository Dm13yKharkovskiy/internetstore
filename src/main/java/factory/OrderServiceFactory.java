package factory;

import dao.UserDao;
import service.OrderService;
import service.impl.OrderServiceImpl;

public class OrderServiceFactory {

    private OrderServiceFactory() {
    }

    private static volatile OrderService orderService;

    static {
        getInstance();
    }
    public static OrderService getOrderService() {
        return orderService;
    }

    private static synchronized OrderService getInstance() {
        OrderService localInstance = orderService;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = orderService;
                if (localInstance == null) {
                    orderService = localInstance = new OrderServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
