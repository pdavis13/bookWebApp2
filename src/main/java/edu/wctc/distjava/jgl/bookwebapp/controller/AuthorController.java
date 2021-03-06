/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorService;
import edu.wctc.distjava.jgl.bookwebapp.model.IAuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.model.MySqlDataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private String driverClass;
    private String url;
    private String username;
    private String password;

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
            
            IAuthorDao dao = new AuthorDao(
                driverClass, url, username, password,
                new MySqlDataAccess()
            );

            AuthorService authorService = new AuthorService(dao); 
            
            List<Author> authorList = null;
            
            switch(action) {
                case LIST_ACTION:
                    authorList = authorService.getAuthorList();
                    request.setAttribute("authorList", authorList);
                    break;
                case EDIT:
                    System.out.println(authorService.getAuthorById(request.getParameter("authorId")).getAuthorName());
                    List<Object> authors = Arrays.asList(request.getParameter("authorName"), new Date());
                    authorService.updateAuthorDetails(authors, authorService.getAuthorById(request.getParameter("authorId")).getAuthorName());
                    authorList = authorService.getAuthorList();
                    request.setAttribute("authorList", authorList);
                    break;
                case DELETE:
                    authorService.deleteAuthorById(request.getParameter("delete"));
                    authorList = authorService.getAuthorList();
                    request.setAttribute("authorList", authorList);
                    break;
                case ADD:
                    authors = Arrays.asList(request.getParameter("authorName"), new Date());
                    authorService.addAuthor(authors);
                    authorList = authorService.getAuthorList();
                    request.setAttribute("authorList", authorList);
                    break;
                case FORM:
                    destination = "authorForm.jsp";
                    String id = request.getParameter("id");
                    if(!id.equals("add")){
                        request.setAttribute("authorName", authorService.getAuthorById(id).getAuthorName());
                        request.setAttribute("authorId", id);
                    } else {
                        request.setAttribute("authorAdd", true);
                    }
                default:
                    break;
            }
            
        } catch(Exception e) {
            destination = "/authorList.jsp";
            request.setAttribute("errMessage", e.getMessage());
        }
        
        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }
    
    @Override
    public void init() throws ServletException {
        driverClass = getServletContext()
        .getInitParameter("db.driver.class");
        url = getServletContext()
        .getInitParameter("db.url");
        username = getServletContext()
        .getInitParameter("db.username");
        password = getServletContext()
        .getInitParameter("db.password");
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
