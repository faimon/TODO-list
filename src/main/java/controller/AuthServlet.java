package controller;

import persistence.User;
import service.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = PsqlStore.instOf().findUser(new User(req.getParameter("login"),
                req.getParameter("password")));
        try (PrintWriter pw = resp.getWriter()) {
            if (user == null) {
                pw.write("fail");
            } else {
                pw.write(user.getLogin());
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            }
            pw.flush();
        }
    }
}
