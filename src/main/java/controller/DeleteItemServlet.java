/*- 
 * Classname:             DeleteItemServlet.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  13/02/2015 - 21:40:41 
 * 
 * author:                Jonas Mayer (jonas.mayer.developer@gmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package controller;

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
@WebServlet("/eraseItem")
public class DeleteItemServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");

        ToDoListPersistence listPersistence = new ToDoListPersistence();
        String listName = req.getParameter("name");
        String textItem = req.getParameter("item.text");
        listPersistence.deleteItem(listName, textItem);
    }

}//fim da classe DeleteItemServlet 
