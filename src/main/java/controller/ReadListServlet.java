package controller;

import com.google.gson.JsonArray;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.ToDoListPersistence;

@WebServlet("/getList")
public class ReadListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; charset=UTF-8");

            ToDoListPersistence listPersistence = new ToDoListPersistence();
            JsonArray ja = listPersistence.getCategories();
            PrintWriter out
                    = response.getWriter();
            out.print(ja);
            out.flush();
            out.close();
        } catch (Exception ex) {
            System.err.println("ERROR in ReadListServlet:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}//fim da classe ReadListServlet 
