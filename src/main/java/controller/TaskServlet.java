package controller;

import com.google.gson.Gson;
import persistence.Task;
import persistence.User;
import service.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json");
        resp.setContentType("UTF-8");
        PrintWriter pw = resp.getWriter();
        User user = (User) req.getSession().getAttribute("user");
        pw.print(new Gson().toJson(PsqlStore.instOf().findAllTask(user)));
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String desc = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");
        PsqlStore.instOf().addTask(new Task(desc, new Timestamp(System.currentTimeMillis()), false), user);
    }
}
