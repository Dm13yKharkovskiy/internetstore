package model;


import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;

    private String salt;

    private String role;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User(String email, String password, String salt, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.salt = salt;
    }
    public User(long id, String email, String password, String salt, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }
    public User(long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role);
    }

    @Override
    public String toString() {
        return id + "|" + email + "|" + password + "|" + role;
    }
}
