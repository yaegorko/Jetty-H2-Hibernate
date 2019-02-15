package db.services;

import db.entity.User;

public interface AccountService {

    void addNewUser(User userProfile);

    User getUserByLogin(String login);
}
