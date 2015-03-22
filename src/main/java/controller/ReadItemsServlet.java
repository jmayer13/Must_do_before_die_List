package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.ToDoListPersistence;

@WebServlet("/getItems")
public class ReadItemsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = request.getReader();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JsonParser parser = new JsonParser();
            JsonArray jo = null;

            JsonObject jsonObject = null;
            String listName = null;
            try {
                jsonObject = (JsonObject) parser.parse(sb.toString());
                listName = jsonObject.get("name").getAsString();
                ToDoListPersistence listPersistence = new ToDoListPersistence();
                jo = listPersistence.getList(listName);

            } catch (Exception ex) {
                jo = new JsonArray();
                System.err.println("ERROR in ReadItemsServlet:" + ex.getMessage());
                ex.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.print(jo);
            out.flush();
        } catch (Exception ex) {
            System.err.println("ERROR in ReadItemsServlet:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}//fim da classe ReadItemsServlet 
