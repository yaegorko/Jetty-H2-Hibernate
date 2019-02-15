package db.services.jdbc;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AccountServiceJDBCTest {

    @Test
    public void test() throws SQLException {
        AccountServiceJDBC accountServiceJDBC = new AccountServiceJDBC();
        accountServiceJDBC.dropTable();
    }
}