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

@WebServlet("/eraseItem")
public class DeleteItemServlet extends HttpServlet {

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
            JsonObject jsonObject = null;
            jsonObject = (JsonObject) parser.parse(sb.toString());
            JsonObject list = (JsonObject) jsonObject.get("list");
            JsonObject item = (JsonObject) jsonObject.get("item");

            String listName = list.get("name").getAsString();
            String itemText = item.get("text").getAsString();
            ToDoListPersistence listPersistence = new ToDoListPersistence();
            listPersistence.deleteItem(listName, itemText);
        } catch (Exception ex) {
            System.err.println("ERROR in DeleteItemServlet:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}//fim da classe DeleteItemServlet 
