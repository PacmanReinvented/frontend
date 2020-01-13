package client;

import entities.User;

public interface IRestTemplate {
    User loginUser(String username, String password);
    boolean registerUser(User user);
}
