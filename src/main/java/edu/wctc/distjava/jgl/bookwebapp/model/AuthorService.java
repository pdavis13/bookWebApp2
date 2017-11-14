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
import javax.persistence.Query;
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
    
    public void removeAuthor(Author author){
        getEm().remove(getEm().merge(author));
    }
    
    public void deleteAuthorById(String id) 
            throws ClassNotFoundException, SQLException, 
            NumberFormatException {
        
        Integer value = Integer.parseInt(id);        

//        Author author = getEm().find(Author.class, id);
//        getEm().remove(author);
        
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", value);
        q.executeUpdate();
    }
    
    public List<Author> getAuthorList() throws Exception {
        String jpql = "select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500);
        
        return q.getResultList();
    }
    
    public Author getAuthorById(String id) throws SQLException, ClassNotFoundException {
        return getEm().find(Author.class, Integer.parseInt(id));
    }
    
    public void addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
        Author author = new Author();
        author.setAuthorName(colValues.get(0).toString());
        author.setDateAdded((Date)colValues.get(1));
        getEm().persist(author);
    }

    public void updateAuthorDetails(List<Object> colValues, Object pkValue) throws SQLException, ClassNotFoundException{
//        Author author = getAuthorById(pkValue.toString());
//        author.setAuthorName(colValues.get(0).toString());
//        getEm().merge(author);
        
        String jpql = "update Author a set a.authorName = :name where a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("name", colValues.get(0).toString());
        q.setParameter("id", Integer.parseInt(pkValue.toString()));
        q.executeUpdate();
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
