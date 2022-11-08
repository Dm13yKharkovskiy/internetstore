package service;

import model.Code;
import model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void createOrder(long userId);
    List<Order> getOrders(long userId);
    void setConfirmCode(Code code);
    boolean getConfirmCode(Code code);
    void changeStatusOrder(String status, long idOrder);
    void createProductListIdToString();
    void createProductListNameToString();
}
