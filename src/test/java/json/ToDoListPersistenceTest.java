package json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ToDoListPersistenceTest {

    protected ToDoListPersistence toDoListPersistence;
    JsonObject _element;

    @Before
    public void initialize() {
        toDoListPersistence = new ToDoListPersistence() {

            protected void createDir() {
            }

            ;
            @Override
            protected File[] loadCategoriesFolders() {
                File[] files = new File[2];
                files[0] = new File("test");
                files[1] = new File("works");
                return files;
            }

            protected void writeJson(String listName, JsonArray element) {
                _element = element.get(0).getAsJsonObject();
            }

            protected JsonArray cutListItems(String listName) {
                JsonArray jsonArray = new JsonArray();
                return jsonArray;

            }
        };
    }

    @Test
    public void testGetCategories() {
        JsonArray ja = toDoListPersistence.getCategories();
        assertTrue(ja.toString(), ja.toString().equals("[{\"name\":\"test\"},{\"name\":\"works\"}]"));
    }

    @Test
    public void testAddItemList() throws IOException {
        toDoListPersistence.addItemList("list", "item");
        assertTrue(_element.toString(), _element.get("text").getAsString().equals("item"));
        _element = null;
    }

    @Test
    public void testInverseItemJObject() throws IOException {
        toDoListPersistence.addItemList("list", "item");
        toDoListPersistence.inverseItemJObject(_element, "item");
        assertTrue(_element.get("active").getAsBoolean());
    }

}//fim da classe ToDoListPersistenceTest 
