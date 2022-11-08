package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();
    void addUser(String email, String password, String repeatPassword);
    void removeUser(String id);
    void getUserByID(Long id);
    Optional<User> getUserByEmail(String email);
    Optional<User> findUserByEmailPassword(String email, String password);

}
