package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<List<User>> getAllUser();

    void addUser(User user);

    void removeUser(String id);

    void getUserByID(Long id);

    Optional<User> getUserByEmail(String email);

    Optional<User> findUserByEmailPassword(String email, String password);
}
