/*- 
 * Classname:             ToDoListPersistenceTest.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  18/02/2015 - 14:56:31 
 * 
 * author:                Jonas Mayer (jonas.mayer.developer@gmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package json;

import com.google.gson.JsonArray;
import java.io.File;
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
        };
    }

    @Test
    public void testGetCategories() {
        JsonArray ja = toDoListPersistence.getCategories();
        assertTrue(ja.toString(), ja.toString().equals("[{\"name\":\"test\"},{\"name\":\"works\"}]"));
    }

}//fim da classe ToDoListPersistenceTest 
