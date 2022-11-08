import dao.CartDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
import factory.*;
import model.Code;
import model.Order;
import model.Product;
import model.User;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.impl.ProductServiceImpl;
import utils.HashUtils;
import validation.StringValidation;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

class Scratch {
    private static final OrderService ORDER_SERVICE = OrderServiceFactory.getOrderService();
    private static final CartService CART_SERVICE = CartServiceFactory.getCartService();
    private static final UserService USER_SERVICE = UserServiceFactory.getUserService();

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String email = "95@gmail.com";
        String password = "1";
        String repeatPassword = "1";
        String randomSalt = HashUtils.getRandomSalt();
        String sha256SecurePassword = HashUtils.getSHA256SecurePassword(password, randomSalt);
        User user = new User(email, sha256SecurePassword, randomSalt, "user");
        System.out.println(randomSalt);
        System.out.println(user.getSalt());
        //USER_SERVICE.addUser(email, password, repeatPassword);

    }
}