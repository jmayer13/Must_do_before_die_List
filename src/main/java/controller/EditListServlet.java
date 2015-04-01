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

@WebServlet("/editList")
public class EditListServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            StringBuffer sb = new StringBuffer();

            BufferedReader reader = request.getReader();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                System.err.println(line);
            }

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = null;
            jsonObject = (JsonObject) parser.parse(sb.toString());
            JsonObject oldList = (JsonObject) jsonObject.get("oldList");
            JsonObject newList = (JsonObject) jsonObject.get("newList");
            String oldNameString = oldList.get("name").getAsString();

            String newListString = newList.get("name").getAsString();

            ToDoListPersistence listPersistence = new ToDoListPersistence();

            listPersistence.editList(oldNameString, newListString);
        } catch (Exception ex) {
            System.err.println("ERROR in EditListServlet:" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}//fim da classe EditListServlet 
