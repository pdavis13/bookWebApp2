package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jlombardo
 */
public class AuthorService {
    private IAuthorDao authorDao;
    
    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }
    
    public final int deleteAuthorById(String id) 
            throws ClassNotFoundException, SQLException, 
            NumberFormatException {
        
        if (id == null) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }
        
        Integer value = Integer.parseInt(id);

        return authorDao.deleteAuthorByID(value);
    }
    
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException {
        return authorDao.getListOfAuthors();
    }
    
    public Author getAuthorById(String id) throws SQLException, ClassNotFoundException {
        return authorDao.getAuthorById(Integer.parseInt(id));
    }
    
    public int deleteAuthorByID(String id) throws SQLException, ClassNotFoundException {
        
        Integer value = Integer.parseInt(id);
        
        return authorDao.deleteAuthorByID(value);
    }
    
    public void addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
        authorDao.addAuthor(colValues);
    }

    public void updateAuthorDetails(List<Object> colValues, Object pkValue) throws SQLException, ClassNotFoundException{
        authorDao.updateAuthorDetails(colValues, pkValue);
    }
    
    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IAuthorDao dao = new AuthorDao(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin",
            new MySqlDataAccess()
        );
        
        AuthorService authorService = new AuthorService(dao);
        
        List<Author> list = authorService.getAuthorList();
                
        for(Author a: list){
            System.out.println(a.getAuthorId() + ","
                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
}
