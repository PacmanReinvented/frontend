package client;

import entities.User;

public interface IRestTemplate {
    User loginUser(String username, String password);
    //User checkUserExists(String username);
    boolean registerUser(User user);
}
