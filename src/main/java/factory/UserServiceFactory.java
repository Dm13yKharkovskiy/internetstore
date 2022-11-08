package factory;

import dao.UserDao;
import dao.impl.UserDaoJDBCImpl;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserServiceFactory {

    private UserServiceFactory() {
    }

    private static volatile UserServiceImpl userServiceImpl;

    static {
        getInstance();
    }
    public static UserServiceImpl getUserService() {
        return userServiceImpl;
    }

    private static synchronized UserServiceImpl getInstance() {
        UserServiceImpl localInstance = userServiceImpl;
        if (localInstance == null) {
            synchronized (UserServiceImpl.class) {
                localInstance = userServiceImpl;
                if (localInstance == null) {
                    userServiceImpl = localInstance = new UserServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
