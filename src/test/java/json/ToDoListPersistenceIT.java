package json;

import com.google.gson.JsonArray;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ToDoListPersistenceIT {

    @Test
    public void testCreateDir() {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        File dir = new File("json" + File.separator + "list");
        assertTrue(dir.exists());

    }

    @Test
    public void testAddItem() throws IOException {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        toDoListPersistence.createList("test");
        toDoListPersistence.addItemList("test", "item");
        String s = toDoListPersistence.getList("test").toString();
        assertTrue(s, s.equals("[{\"date\":\"" + LocalDate.now().toString()
                + "\",\"text\":\"item\",\"active\":false}]"));
    }

    @Test
    public void testgetCategories() {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        File dir = new File("json" + File.separator + "list");
        File folderTest = new File(dir, "test" + ".json");
        folderTest.mkdir();
        JsonArray ja = toDoListPersistence.getCategories();
        assertNotNull(ja);
        folderTest.delete();

    }

    @Test
    public void testCreateList() {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();

        File dir = new File("json" + File.separator + "list");
        File file = new File(dir, "test" + ".json");
        toDoListPersistence.createList("test");
        assertTrue(file.exists());
        file.delete();

    }

    @Test
    public void testEditList() throws IOException {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        File dir = new File("json" + File.separator + "list");
        File file = new File(dir, "test" + ".json");
        File fileEdited = new File(dir, "work" + ".json");
        file.createNewFile();
        toDoListPersistence.editList("test", "work");
        assertTrue(fileEdited.exists());
        fileEdited.delete();
    }

    @Test
    public void testEraseList() throws IOException {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        File dir = new File("json" + File.separator + "list");
        File file = new File(dir, "test" + ".json");
        file.createNewFile();
        toDoListPersistence.deleteList("test");
        assertFalse(file.exists());

    }

    @Before
    public void cleanData() {
        File dir = new File("json" + File.separator + "list");

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        dir.delete();
    }

    @Test
    public void testFileWritting() throws IOException {
        File f = new File("f");
        f.createNewFile();
        assertTrue(f.exists());
        f.delete();
    }

    @Test
    public void testGetList() throws IOException {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        toDoListPersistence.createList("test");
        toDoListPersistence.addItemList("test", "item");
        assertNotNull(toDoListPersistence.getList("test"));
    }

    @Test
    public void testinverseItem() throws IOException {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        toDoListPersistence.createList("test");
        toDoListPersistence.addItemList("test", "item");
        toDoListPersistence.inverseItem("test", "item");
        JsonArray jsonArray = toDoListPersistence.getList("test");
        boolean resp = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.get(i).getAsJsonObject().get("text").getAsString().equals("item")) {
                resp = jsonArray.get(i).getAsJsonObject().get("active").getAsBoolean();
            }
        }

        assertTrue(jsonArray.toString(), resp);

    }

    @Test
    public void testDeleteitem() {
        try {
            ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
            toDoListPersistence.createList("test");
            toDoListPersistence.addItemList("test", "item");
            toDoListPersistence.deleteItem("test", "item");
            //assertNull(toDoListPersistence.getList("test").toString(), toDoListPersistence.getList("test"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}//fim da classe ToDoListPersistenceIT 
