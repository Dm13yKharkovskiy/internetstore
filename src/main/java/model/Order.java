package model;

import java.util.Objects;

public class Order {
    private long idOrder;
    private long idUser;
    private double amountOrder;
    private long countProducts;
    private String statusOrder;
    private String listProductId;
    private int confirmCode;

    public Order(long idUser, double amountOrder, long countProducts,
                 String statusOrder, String listProductId, int confirmCode) {
        this.idUser = idUser;
        this.amountOrder = amountOrder;
        this.countProducts = countProducts;
        this.statusOrder = statusOrder;
        this.listProductId = listProductId;
        this.confirmCode = confirmCode;
    }

    public Order(long idOrder, long idUser, double amountOrder, long countProducts,
                 String statusOrder, String listProductId, int confirmCode) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.amountOrder = amountOrder;
        this.countProducts = countProducts;
        this.statusOrder = statusOrder;
        this.listProductId = listProductId;
        this.confirmCode = confirmCode;
    }

    public long getIdOrder() {
        return idOrder;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public double getAmountOrder() {
        return amountOrder;
    }

    public void setAmountOrder(double amountOrder) {
        this.amountOrder = amountOrder;
    }

    public long getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(int countProducts) {
        this.countProducts = countProducts;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getListProductId() {
        return listProductId;
    }

    public void setListProductId(String listProductId) {
        this.listProductId = listProductId;
    }

    public int getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(int confirmCode) {
        this.confirmCode = confirmCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder && idUser == order.idUser && Double.compare(order.amountOrder, amountOrder) == 0 && countProducts == order.countProducts && confirmCode == order.confirmCode && statusOrder.equals(order.statusOrder) && listProductId.equals(order.listProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idUser, amountOrder, countProducts, statusOrder, listProductId, confirmCode);
    }

    @Override
    public String toString() {
        return idOrder + " | " + " | " + idUser + " | " + amountOrder +
                " | " + countProducts + " | " + statusOrder +
                " | " + listProductId + " | " + confirmCode;
    }
}
