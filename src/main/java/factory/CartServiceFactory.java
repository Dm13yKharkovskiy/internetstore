package factory;

import service.impl.CartServiceImpl;

public class CartServiceFactory {
    private CartServiceFactory() {
    }

    private static volatile CartServiceImpl cartService;

    static {
        getInstance();
    }
    public static CartServiceImpl getCartService() {
        return cartService;
    }

    private static synchronized CartServiceImpl getInstance() {
        CartServiceImpl localInstance = cartService;
        if (localInstance == null) {
            synchronized (CartServiceImpl.class) {
                localInstance = cartService;
                if (localInstance == null) {
                    cartService = localInstance = new CartServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
