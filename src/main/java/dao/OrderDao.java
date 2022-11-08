package dao;

import model.Code;
import model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    void createOrder(Order order);
    List<Order> getOrders(long userId);

    void setConfirmCode(Code code);
    void updateConfirmCode(Code code);
    Optional getConfirmCode(Code code);

    void changeStatusOrder(String status, long idOrder);

    void createProductListIdToString();
    void createProductListNameToString();
}
