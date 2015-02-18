/*- 
 * Classname:             ReadItemsServlet.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  13/02/2015 - 21:46:37 
 * 
 * author:                Jonas Mayer (jonas.mayer.developer@gmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package controller;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/getItems")
public class ReadItemsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String listName = req.getParameter("name");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");

        ToDoListPersistence listPersistence = new ToDoListPersistence();

        JsonObject jo = listPersistence.getList(listName);

        PrintWriter out
                = response.getWriter();
        out.print(jo);
        out.flush();

    }

}//fim da classe ReadItemsServlet 
