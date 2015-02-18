/*- 
 * Classname:             EditListServlet.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  13/02/2015 - 21:40:13 
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
@WebServlet("/editList")
public class EditListServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");

        ToDoListPersistence listPersistence = new ToDoListPersistence();
        String listName = req.getParameter("name");
        String oldName = req.getParameter("oldName");

        listPersistence.editList(oldName, listName);
    }
}//fim da classe EditListServlet 
