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

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
@WebServlet("/activeItem")
public class EditActiveItemServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        try {
            StringBuffer sb = new StringBuffer();

            BufferedReader reader = req.getReader();
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
            String listName = oldList.get("name").getAsString();
            String task = newList.get("name").getAsString();
            ToDoListPersistence listPersistence = new ToDoListPersistence();

            listPersistence.inverseItem(listName, task);
        } catch (Exception ex) {
            System.err.println("ERROR in EditActiveItemServlet:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}//fim da classe EditActiveItemServlet 
