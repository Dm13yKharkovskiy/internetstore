package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import utils.HashUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements service.UserService {

    private final UserDao USER_DAO = UserDaoFactory.getUserDao();

    private static UserServiceImpl userService;


    public static UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        Optional<List<User>> allUser = USER_DAO.getAllUser();
        if (allUser.isPresent()) {
            userList = allUser.get();
        }
        return userList;
    }

    @Override
    public void addUser(String email, String password, String repeatPassword) {
        //String hashPassword = hashingPassword(password);
        String randomSalt = HashUtils.getRandomSalt();
        String sha256SecurePassword = HashUtils.getSHA256SecurePassword(password, randomSalt);
        User user = new User(email, sha256SecurePassword, randomSalt, "user");
        USER_DAO.addUser(user);
    }

    @Override
    public void removeUser(String id) {
        USER_DAO.removeUser(id);
    }

    @Override
    public void getUserByID(Long id) {

    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return USER_DAO.getUserByEmail(email);
    }

    @Override
    public Optional<User> findUserByEmailPassword(String email, String password) {
        String hashPassword = hashingPassword(password);
        return USER_DAO.findUserByEmailPassword(email, hashPassword);
    }


    private String hashingPassword(String password) {
        return HashUtils.getSHA256SecurePassword(password);
    }
}
