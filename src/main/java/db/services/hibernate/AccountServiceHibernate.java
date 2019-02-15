package db.services.hibernate;

import db.entity.*;
import db.services.AccountService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

public class AccountServiceHibernate extends SessionUtils implements AccountService {

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
