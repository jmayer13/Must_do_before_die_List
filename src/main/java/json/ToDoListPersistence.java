package json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
            String fileName = file.getName();
            fileName = fileName.replace(".json", "");
            jsonObject.addProperty("name", fileName);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    protected File[] loadCategoriesFolders() {
        return dir.listFiles();
    }

    public void createList(String listName) {
        File file = new File(dir, listName + ".json");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ToDoListPersistence.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void editList(String oldListName, String listName) {
        File oldfile = new File(dir, oldListName + ".json");
        oldfile.renameTo(new File(dir, listName + ".json"));
    }

    public void deleteList(String listName) {
        File list = new File(dir, listName + ".json");
        File[] files = list.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        list.delete();
    }

    //CRUD list
    //CREATE
    public void addItemList(String listName, String itemText) throws FileNotFoundException, IOException {
        JsonArray listJson
                = cutListItems(listName);
        if (listJson == null) {
            listJson = new JsonArray();
        }
        JsonObject element = new JsonObject();
        element.addProperty("date", LocalDate.now().toString());
        element.addProperty("text", itemText);
        element.addProperty("active", false);
        listJson.add(element);
        writeJson(listName, listJson);
    }

    protected void writeJson(String listName, JsonArray element) {
        try {
            if (element == null) {
                throw new NullPointerException("JsonArray is null!");
            }
            File file = new File(dir, listName + ".json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            gson.toJson(element, jsonWriter);
            jsonWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ToDoListPersistence.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    //SELECT
    public JsonArray getList(String listName) {
        if (listName == null || listName.equals("")) {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = null;
            jsonArray = (JsonArray) parser.parse("[]");
            return jsonArray;

        }

        JsonReader jsonReader = null;
        JsonArray jsonArray = null;
        try {
            JsonParser jsonParser = new JsonParser();
            File file = new File(dir, listName + ".json");
            FileReader fileReader = new FileReader(file);
            jsonReader = new JsonReader(fileReader);
            JsonElement je = jsonParser.parse(jsonReader);
            if (je.isJsonNull()) {

                jsonArray = null;
            } else {
                jsonArray = (JsonArray) je;
            }
            jsonReader.close();
            fileReader.close();
        } catch (Exception ex) {
            Logger.getLogger(ToDoListPersistence.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        if (jsonArray == null) {
            JsonParser parser = new JsonParser();
            jsonArray = (JsonArray) parser.parse("[]");
        }
        return jsonArray;

    }

//EDIT
    public void inverseItem(String listName, String item) {
        JsonArray jsonObjectList = cutListItems(listName);
        for (int i = 0; i < jsonObjectList.size(); i++) {
            JsonObject jsonItem = jsonObjectList.get(i).getAsJsonObject();
            if (jsonItem.get("text").getAsString().equals(item)) {
                jsonObjectList.remove(i);
                boolean activeInverse = jsonItem.get("active").getAsBoolean();
                jsonItem.addProperty("active", !activeInverse);
            }
            jsonObjectList.add(jsonItem);
        }
        createList(listName);
        writeJson(listName, jsonObjectList);

    }

    protected JsonObject inverseItemJObject(JsonObject jsonItem, String item) {
        if (jsonItem.get("text").getAsString().equals(item)) {
            boolean activeInverse = jsonItem.get("active").getAsBoolean();
            jsonItem.addProperty("active", !activeInverse);
        }
        return jsonItem;
    }

    //DELETE
    public void deleteItem(String listName, String item) {
        JsonArray jsonObjectList = cutListItems(listName);
        for (int i = 0; i < jsonObjectList.size(); i++) {
            if (jsonObjectList.get(i).getAsJsonObject().get("text").getAsString().equals(item)) {
                jsonObjectList.remove(i);
            }
        }
        createList(listName);
        writeJson(listName, jsonObjectList);
    }

    protected JsonArray cutListItems(String listName) {
        JsonArray jsonArray = getList(listName);

        File file = new File(dir, listName + ".json");
        file.delete();

        return jsonArray;
    }

}//fim da classe ToDoListPersistence 
