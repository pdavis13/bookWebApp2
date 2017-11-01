package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AuthorService implements Serializable{

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public AuthorService() {
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public final int deleteAuthorById(String id) 
            throws ClassNotFoundException, SQLException, 
            NumberFormatException {
        
        if (id == null) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }
        
        Integer value = Integer.parseInt(id);

        return 0;
    }
    
    public List<Author> getAuthorList() throws Exception {
        String jpql = "select a from Author";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500);
        
        return q.getResultList();
    }
    
    public Author getAuthorById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
    
    public int deleteAuthorByID(String id) throws SQLException, ClassNotFoundException {
        
        Integer value = Integer.parseInt(id);
        
        return 0;
    }
    
    public void addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
    }

    public void updateAuthorDetails(List<Object> colValues, Object pkValue) throws SQLException, ClassNotFoundException{
    }

    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        IAuthorDao dao = new AuthorDao(
//            "com.mysql.jdbc.Driver",
//            "jdbc:mysql://localhost:3306/book",
//            "root", "admin",
//            new MySqlDataAccess()
//        );
        
//        AuthorService authorService = new AuthorService(dao);
//        
//        List<Author> list = authorService.getAuthorList();
//                
//        for(Author a: list){
//            System.out.println(a.getAuthorId() + ","
//                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
//        }
    }
}
