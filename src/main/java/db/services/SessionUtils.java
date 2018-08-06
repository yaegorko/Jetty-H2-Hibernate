package db.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtils {

    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession() {
        return DBService.getSessionFactory().openSession();
    }

    public Session openTransaction() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeTransaction() {
        transaction.commit();
        closeSession();
    }
}
