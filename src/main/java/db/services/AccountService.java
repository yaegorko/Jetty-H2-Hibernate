package db.services;

import db.entity.*;
import org.hibernate.Session;

public class AccountService extends SessionUtils {

    public void addNewUser(User userProfile) {
        Session session = openTransaction();
        session.save(userProfile);
        closeTransaction();
    }

    public User getUserByLogin(String login) {
        try (Session session = openSession()) {
            User user = session.get(User.class, login);
            // closeSession();
            System.out.println(user);
            return user;
        }
    }

}
