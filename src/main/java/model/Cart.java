package model;

import java.util.Objects;

public class Cart {

    private long idCart;
    private long idUser;

    public Cart(long idUser) {
        this.idUser = idUser;
    }

    public Cart(long idCart, long idUser) {
        this.idCart = idCart;
        this.idUser = idUser;
    }

    public long getIdCart() {
        return idCart;
    }

    public long getIdUser() {
        return idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return idCart == cart.idCart && idUser == cart.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCart, idUser);
    }

    @Override
    public String toString() {
        return "idCart " + idCart + " | " + "idUser" + idUser;
    }
}
