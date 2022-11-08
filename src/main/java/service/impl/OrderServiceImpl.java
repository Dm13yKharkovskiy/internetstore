package service.impl;

import dao.CartDao;
import dao.OrderDao;
import factory.CartDaoFactory;
import factory.CartServiceFactory;
import factory.OrderDaoFactory;
import model.Code;
import model.Order;
import model.Product;
import service.CartService;
import service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final CartService CART_SERVICE = CartServiceFactory.getCartService();
    private final OrderDao ORDER_DAO = OrderDaoFactory.getOrderDao();
    private final CartDao CART_DAO = CartDaoFactory.getCartDao();

    private static OrderServiceImpl orderService;


    public static OrderServiceImpl getUserService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    @Override
    public void createOrder(long userId) {
        List<Product> products = CART_SERVICE.getProducts(userId);
        if (products != null) {
            long countProducts = products.stream().count();
            double amountOrder = products.stream().mapToDouble(Product::getPrice).sum();
            Order order = new Order(userId, amountOrder, countProducts,
                    "Требует подтв", "test", 5432);
            ORDER_DAO.createOrder(order);

        }

    }

    @Override
    public List<Order> getOrders(long userId) {
        return ORDER_DAO.getOrders(userId);
    }

    @Override
    public void setConfirmCode(Code code) {
        ORDER_DAO.setConfirmCode(code);
    }

    @Override
    public boolean getConfirmCode(Code code) {
        Optional confirmCode = ORDER_DAO.getConfirmCode(code);
        if (confirmCode.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changeStatusOrder(String status, long idOrder) {
        ORDER_DAO.changeStatusOrder(status, idOrder);
    }

    @Override
    public void createProductListIdToString() {

    }

    @Override
    public void createProductListNameToString() {

    }
}
