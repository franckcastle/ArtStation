package entities;

import java.util.List;

public class User {
    private int userId ,tel;
    private String username,password,email,role;
    private List<Resevation> reservations;

    public User() {}

    public User(String username, String password, String email,int tel ,String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.role = role;
    }

    public User(int userId, String username, String password, String email,int tel ,String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.role = role;
    }

    public User(List<Resevation> reservations) {
        this.reservations = reservations;
    }

    public List<Resevation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Resevation> reservations) {
        this.reservations = reservations;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}
