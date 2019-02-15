package db.services;

import db.services.hibernate.DBServiceHibernate;
import org.junit.Test;

public class DBServiceTest {

    @Test
    public void test1() {
        DBServiceHibernate service = new DBServiceHibernate();
    }
}