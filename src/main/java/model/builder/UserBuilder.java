package model.builder;

import model.User;

public class UserBuilder {
    private User user;

    public UserBuilder(){
        user = new User();
    }

    public UserBuilder setId(int id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public User build(){
        return user;
    }
}
