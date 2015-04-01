package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import json.ToDoListPersistence;

@WebServlet("/eraseList")
public class DeleteListServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = req.getReader();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = null;
            jsonObject = (JsonObject) parser.parse(sb.toString());
            String listName = jsonObject.get("name").getAsString();

            ToDoListPersistence listPersistence = new ToDoListPersistence();
            listPersistence.deleteList(listName);
        } catch (Exception ex) {
            System.err.println("ERROR in DeleteItemServlet:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}//fim da classe DeleteListServlet 
