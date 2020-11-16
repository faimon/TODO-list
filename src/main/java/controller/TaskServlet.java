package controller;

import com.google.gson.Gson;
import persistance.Task;
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
        pw.print(new Gson().toJson(PsqlStore.instOf().findAllTask()));
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String desc = req.getParameter("description");
        PsqlStore.instOf().addTask(new Task(1, desc,
                new Timestamp(System.currentTimeMillis()), false));
    }
}
