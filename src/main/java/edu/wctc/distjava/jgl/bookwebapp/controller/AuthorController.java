/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorService;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorServiceObsolete;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author jlombardo
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {
    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";
    public static final String EDIT = "edit";
    public static final String DELETE = "delete";
    public static final String ADD = "add";
    public static final String FORM = "form";
    
    
    private AuthorService authorService = new AuthorService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = "/authorList.jsp"; // default
        
        try {
            String action = request.getParameter(ACTION).toLowerCase();
            
            List<Author> authorList = null;
            
            switch(action) {
                case LIST_ACTION:
                    authorList = authorService.findAll();
                    request.setAttribute("authorList", authorList);
                    break;
                case EDIT:
                    authorService.updateAuthor(request.getParameter("authorId"), request.getParameter("authorName"));
                    authorList = authorService.findAll();
                    request.setAttribute("authorList", authorList);
                    break;
                case DELETE:
                    authorService.removeAuthorById(request.getParameter("delete"));
                    authorList = authorService.findAll();
                    request.setAttribute("authorList", authorList);
                    break;
                case ADD:
                    authorService.addAuthor(request.getParameter("authorName"));
                    authorList = authorService.findAll();
                    request.setAttribute("authorList", authorList);
                    break;
                case FORM:
                    destination = "authorForm.jsp";
                    String id = request.getParameter("id");
                    if(!id.equals("add")){
                        request.setAttribute("authorName", authorService.findById(id).getAuthorName());
                        request.setAttribute("authorId", id);
                    } else {
                        request.setAttribute("authorAdd", true);
                    }
                default:
                    break;
            }
            
        } catch(Exception e) {
            destination = "/authorList.jsp";
            request.setAttribute("errMessage", "Error: " + e.getMessage());
        }
        
        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }
    
    @Override
    public void init() throws ServletException {
        ServletContext sctx = getServletContext();
        
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authorService = (AuthorService) ctx.getBean("authorService");
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
