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

@WebServlet("/editItem")
public class EdititemServlet extends HttpServlet {

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
            JsonObject oldItem = (JsonObject) jsonObject.get("oldItem");
            JsonObject newItem = (JsonObject) jsonObject.get("newItem");

            String listString = list.get("name").getAsString();
            String olditemString = oldItem.get("text").getAsString();
            String newItemString = newItem.get("text").getAsString();

            ToDoListPersistence listPersistence = new ToDoListPersistence();
            listPersistence.deleteItem(listString, olditemString);
            listPersistence.addItemList(listString, newItemString);
        } catch (Exception ex) {
            System.err.println("ERROR in EdititemServlet:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}//fim da classe EdititemServlet 
