package factory;

import dao.UserDao;
import dao.impl.UserDaoJDBCImpl;

public class UserDaoFactory {

    private UserDaoFactory() {
    }

    private static volatile UserDao userDao;

    static {
        getInstance();
    }
    public static UserDao getUserDao() {
        return userDao;
    }

    private static synchronized UserDao getInstance() {
        UserDao localInstance = userDao;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = userDao;
                if (localInstance == null) {
                    userDao = localInstance = new UserDaoJDBCImpl();
                }
            }
        }
        return localInstance;
    }
}
