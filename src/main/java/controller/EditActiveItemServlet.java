/*- 
 * Classname:             EditActiveItemServlet.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  12/02/2015 - 15:47:54 
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
@WebServlet("/activeItem")
public class EditActiveItemServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");

        ToDoListPersistence listPersistence = new ToDoListPersistence();
        String listName = req.getParameter("name");
        String task = req.getParameter("item.text");

        listPersistence.inverseItem(listName, task);
    }

}//fim da classe EditActiveItemServlet 
