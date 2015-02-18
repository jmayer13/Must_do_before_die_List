/*- 
 * Classname:             ToDoListPersistenceIT.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  18/02/2015 - 14:57:35 
 * 
 * author:                Jonas Mayer (jonas.mayer.developer@gmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package json;

import com.google.gson.JsonArray;
import java.io.File;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
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
    public void testgetCategories() {
        ToDoListPersistence toDoListPersistence = new ToDoListPersistence();
        File dir = new File("json" + File.separator + "list");
        File folderTest = new File(dir, "test");
        folderTest.mkdir();
        JsonArray ja = toDoListPersistence.getCategories();
        assertNotNull(ja);
        folderTest.delete();

    }

}//fim da classe ToDoListPersistenceIT 
