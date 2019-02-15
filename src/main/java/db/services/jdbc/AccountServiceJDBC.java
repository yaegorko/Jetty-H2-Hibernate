package db.services.jdbc;

import db.entity.User;
import db.services.AccountService;

import java.sql.*;

import static db.services.jdbc.DBServiceJDBC.getH2Connection;

public class AccountServiceJDBC implements AccountService {

    private final Connection connection;

    public AccountServiceJDBC() {
        this.connection = getH2Connection();
        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewUser(User userProfile) {
        try {
            connection.setAutoCommit(false);
            createTable();
            insertUser(userProfile);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE login=?");
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPass(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void createTable() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "CREATE TABLE if NOT EXISTS users (id bigint auto_increment, login VARCHAR(256) UNIQUE, " +
                        "password VARCHAR(256), PRIMARY KEY(id))");
        stmt.executeUpdate();
        stmt.close();
    }

    private void insertUser(User userProfile) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (login, password) VALUES (?,?)");
        stmt.setString(1, userProfile.getLogin());
        stmt.setString(2, userProfile.getPass());
        stmt.executeUpdate();
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("DROP TABLE users");
        stmt.close();
    }
}
