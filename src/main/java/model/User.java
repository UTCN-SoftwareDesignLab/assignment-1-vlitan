package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private List<Role> roles = new ArrayList<>();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addRole(Role role){
        this.roles.add(role);
    }

    @Override
    public String toString(){
        return "User\t" + id + "\t" + username + "\t" + password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
