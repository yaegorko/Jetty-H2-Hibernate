package db.services.hibernate;

import db.services.hibernate.DBServiceHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtils {

    private Session session;
    private Transaction transaction;
    private DBServiceHibernate service = new DBServiceHibernate();

    public Session openSession() {
        return service.getSessionFactory().openSession();
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
