/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DaoSqlite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Task;

/**
 *
 * @author admin
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext sc = this.getServletContext();
        String dbPath = sc.getRealPath("/WEB-INF/employee.db");

        //  DaoSqlite.dropTable(dbPath);
        //  DaoSqlite.createTable(dbPath);
        // set default url
        String url = "/index.html";

        // get current action
        String action = request.getParameter("action");

        if (action == null) {
            action = "home";
        }

        // perform action and set url
        if (action.equalsIgnoreCase("home")) {
            url = "/index.html";

        } else if (action.equalsIgnoreCase("report")) {
            List<Task> mydata = null;
            mydata = DaoSqlite.retrieveAllRecordsByName(dbPath);
            request.setAttribute("mydata", mydata);
            url = "/displayRecords.jsp";

        } else if (action.equalsIgnoreCase("deleteRecord")) {
            String idString = request.getParameter("id");
            if (idString == null || idString.isEmpty()) {
                url = "/deleteRecord.jsp";
            } else {
                // delete this data record from the database
                DaoSqlite.deleteRecord(Integer.parseInt(idString), dbPath);
                url = "/index.html";
            }

        } else if (action.equalsIgnoreCase("updateRecord1")) {
            String idString = request.getParameter("taskId");
            if (idString == null || idString.isEmpty()) {
                url = "/updateRecord1.jsp";
            } else {
                // get task
                Task task = DaoSqlite.retrieveRecordById(Integer.parseInt(idString), dbPath);
                request.setAttribute("task", task);
                url = "/updateRecord2.jsp";
            }

        }else if (action.equalsIgnoreCase("updateRecord2")) {

            // get parameters passed in from the request
            String taskId = request.getParameter("taskId");
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");

            Task task = new Task(Integer.parseInt(taskId), name, desc);

           
                // insert this data record into the database
                DaoSqlite.updateRecord(task, dbPath);
                url = "/index.html";
            

        }   else if (action.equalsIgnoreCase("createRecord")) {

            // get parameters passed in from the request
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");

            // store data in an Face object
            Task task = new Task(0, name, desc);

            // insert this data record into the database
            DaoSqlite.createRecord(task, dbPath);
            url = "/index.html";

            // add the user object to the request object so that the data is available on the next page
            request.setAttribute("task", task);

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
