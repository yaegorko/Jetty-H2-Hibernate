package db.services;

import db.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.FROM;

public class AccountService extends SessionUtils {

    public void addNewUser(User userProfile) {
        Session session = openTransaction();
        session.save(userProfile);
        closeTransaction();
    }

    public User getUserByLogin(String login) {
        try (Session session = openSession()) {
            Query query = session.createQuery("from User where login = :login");
            try {
                User user = (User) query.setParameter("login", login).getSingleResult();
                return user;
            } catch (NoResultException noResultException) {
                return null;
            }
        }
    }
}
