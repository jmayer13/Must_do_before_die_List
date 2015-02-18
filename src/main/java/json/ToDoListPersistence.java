/*- 
 * Classname:             ToDoListPersistence.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  10/02/2015 - 22:12:01 
 * 
 * author:                Jonas Mayer (jonas.mayer.developer@gmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ToDoListPersistence {

    private File dir;

    public ToDoListPersistence() {
        createDir();
    }

    protected void createDir() {
        dir = new File("json" + File.separator + "list");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    //CRUD category
    public JsonArray getCategories() {
        File[] files = loadCategoriesFolders();
        JsonArray jsonArray = new JsonArray();
        for (File file : files) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", file.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    protected File[] loadCategoriesFolders() {
        return dir.listFiles();
    }

    public void createList(String listName) {
        File file = new File(dir, listName);
        file.mkdir();
    }

    public void editList(String oldListName, String listName) {
        File file = new File(dir, listName);
        file.renameTo(new File(dir, oldListName));
    }

    public void deleteList(String listName) {
        File file = new File(dir, listName);
        file.delete();
    }

    //CRUD list
    //CREATE
    public void addItemList(String listName, String itemText) throws FileNotFoundException, IOException {

        JsonObject element = new JsonObject();
        element.addProperty("date", LocalDate.now().toString());
        element.addProperty("text", itemText);
        element.addProperty("active", false);

        Gson gson = new Gson();
        JsonWriter jsonWriter = new JsonWriter(new FileWriter(new File(dir, listName)));
        gson.toJson(element, jsonWriter);
        jsonWriter.close();

    }

    //SELECT
    public JsonObject getList(String listName) {
        JsonReader jsonReader = null;
        JsonObject jsonObject = null;
        try {
            JsonParser jsonParser = new JsonParser();
            jsonReader = new JsonReader(new FileReader(new File(dir, listName)));
            jsonObject = (JsonObject) jsonParser.parse(jsonReader);
            jsonReader.close();

        } catch (IOException ex) {
            Logger.getLogger(ToDoListPersistence.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            jsonObject = null;
        }
        try {
            if (jsonReader != null) {
                jsonReader.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ToDoListPersistence.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return jsonObject;

    }

//EDIT
    public void inverseItem(String listName, String item) {
        JsonParser jsonParser = new JsonParser();
        List<JsonObject> jsonObjectList = new ArrayList();
        JsonObject jsonObject = null;

        do {
            try {
                JsonReader jsonReader = new JsonReader(new FileReader(new File(dir, listName)));
                jsonReader.close();
                jsonObject = (JsonObject) jsonParser.parse(jsonReader);
                jsonObjectList.add(jsonObject);
            } catch (Exception ex) {
                ex.printStackTrace();
                jsonObject = null;
            }
        } while (jsonObject != null);
        File file = new File(dir, listName);
        file.delete();
        for (JsonObject jsonItem : jsonObjectList) {
            if (jsonItem.get("text").getAsString().equals(item)) {
                boolean activeInverse = jsonItem.get("active").getAsBoolean();
                jsonItem.addProperty("active", !activeInverse);
            }
        }
        createList(listName);
        for (JsonObject jsonItem : jsonObjectList) {
            Gson gson = new Gson();
            JsonWriter jsonWriter;
            try {
                jsonWriter = new JsonWriter(new FileWriter(new File(dir, listName)));
                gson.toJson(jsonItem, jsonWriter);
                jsonWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(ToDoListPersistence.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }

        }

    }

    //DELETE
    public void deleteItem(String listName, String item) {
        JsonParser jsonParser = new JsonParser();
        List<JsonObject> jsonObjectList = new ArrayList();
        JsonObject jsonObject = null;

        do {
            try {
                JsonReader jsonReader = new JsonReader(new FileReader(new File(dir, listName)));
                jsonReader.close();
                jsonObject = (JsonObject) jsonParser.parse(jsonReader);
                jsonObjectList.add(jsonObject);
            } catch (Exception ex) {
                ex.printStackTrace();
                jsonObject = null;
            }
        } while (jsonObject != null);
        File file = new File(dir, listName);
        file.delete();
        for (JsonObject jsonItem : jsonObjectList) {
            if (jsonItem.get("text").getAsString().equals(item)) {
                jsonObjectList.remove(jsonItem);
            }
        }
        createList(listName);
        for (JsonObject jsonItem : jsonObjectList) {
            Gson gson = new Gson();
            JsonWriter jsonWriter;
            try {
                jsonWriter = new JsonWriter(new FileWriter(new File(dir, listName)));
                gson.toJson(jsonItem, jsonWriter);
                jsonWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(ToDoListPersistence.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }

        }
    }

}//fim da classe ToDoListPersistence 
