package dao.impl;

import dao.UserDao;
import utils.ConnectionToDB;
import model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class);

    private static UserDaoJDBCImpl userDaoJDBCImpl;


    public static UserDaoJDBCImpl getUserService() {
        if (userDaoJDBCImpl == null) {
            userDaoJDBCImpl = new UserDaoJDBCImpl();
        }
        return userDaoJDBCImpl;
    }

    @Override
    public Optional<List<User>> getAllUser() {
        String sqlQuery = "select * from public.users";
        List<User> userList = new ArrayList<>();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String email = rs.getString("email").trim();
                String user_password = rs.getString("user_password").trim();
                String user_role = rs.getString("user_role").trim();
                User user = new User(id, email, user_password, user_role);
                userList.add(user);
            }
            if (userList.size() > 0) {
                return Optional.of(userList);
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        String sqlQuery = "insert into public.users (email, user_password, salt, user_role) values (?, ?, ?, ?)";
        try (Connection connect = ConnectionToDB.connect()) {
            PreparedStatement ps = connect.prepareStatement(sqlQuery);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSalt());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
            logger.info("User " + user.getEmail() + " add to DB success");
        } catch (SQLException e) {
            logger.error("User " + user.getEmail() + " add to DB failure");
        }
    }

    @Override
    public void removeUser(String id) {
        String sqlQuery = "delete from public.users where id = ? ";
        long idToQuery = Long.parseLong(id);
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setLong(1, idToQuery);
            ps.executeUpdate();
            ps.close();
            logger.info("User id " + idToQuery + " remove from DB success");
        } catch (SQLException e) {
            logger.error("User id " + idToQuery + " remove from DB failure");
        }
    }

    @Override
    public void getUserByID(Long id) {

    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String sqlQuery = "select * from users where email = ?";
        Optional<User> user = Optional.empty();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String user_email = rs.getString("email").trim();
                String user_password = rs.getString("user_password").trim();
                String user_role = rs.getString("user_role").trim();
                user = Optional.of(new User(id, user_email, user_password, user_role));
            }
        } catch (SQLException e) {

        }
        return user;
    }

    @Override
    public Optional<User> findUserByEmailPassword(String email, String password) {
        String sqlQuery = "select * from users where email = ? and user_password = ?";
        Optional<User> user = Optional.empty();
        try (Connection connect = ConnectionToDB.connect();
             PreparedStatement ps = connect.prepareStatement(sqlQuery)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String user_email = rs.getString("email").trim();
                String user_password = rs.getString("user_password").trim();
                String user_role = rs.getString("user_role").trim();
                user = Optional.of(new User(id, user_email, user_password, user_role));
            }
            logger.info("User " + email + " find into DB success");
        } catch (SQLException e) {
            logger.error("User " + email + " find into DB failure");
        }
        return user;
    }
}
