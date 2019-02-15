package server.servlets;

import db.entity.User;
import db.services.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {

    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User newUser = new User(req.getParameter("login"), req.getParameter("password"));
        accountService.addNewUser(newUser);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
