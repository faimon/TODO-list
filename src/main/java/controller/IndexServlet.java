package controller;

import com.google.gson.Gson;
import service.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("UTF-8");
        resp.setContentType("json");
        PrintWriter pw = resp.getWriter();
        pw.print(new Gson().toJson(PsqlStore.instOf().findNotDoneTask()));
        pw.flush();
        pw.close();
    }
}
