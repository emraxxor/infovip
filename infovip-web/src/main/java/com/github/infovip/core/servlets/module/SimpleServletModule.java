package com.github.infovip.core.servlets.module;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.security.BasicSecureFunctions;
import com.github.infovip.spring.controllers.ModuleController;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Basically, It's just a simple servlet loader which has been replaced by
 * another servlet.
 *
 * The main module loader component can be found in the following package :
 * com.github.infovip.spring.controllers
 *
 * @see ModuleController
 *
 * @author attila
 *
 * @todo
 * Refactoring
 *
 */
public class SimpleServletModule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getPathInfo() != null && request.getPathInfo().contains("/")) {
            String[] paths = request.getPathInfo().split("/");
            String moduleDirectoryName = paths[1];
            try (PrintWriter out = response.getWriter()) {
                String fileToInclude = String.format("/%s/%s/index.jsp", Configuration.MODULE_DIRECTORY, moduleDirectoryName);
                File f = new File(getServletContext().getRealPath(fileToInclude));
                if (BasicSecureFunctions.directoryTraversalInputCheck(moduleDirectoryName) && f.isFile()) {
                    request.setAttribute("modulePath", Configuration.MODULE_DIRECTORY + "/" + moduleDirectoryName);
                    request.getRequestDispatcher(fileToInclude).include(request, response);
                    return;
                }
            }
        }
        response.getWriter().println("Ooops... Something went wrong :( Sorry for that .. Try to reload the page ... No path defined ..");
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
