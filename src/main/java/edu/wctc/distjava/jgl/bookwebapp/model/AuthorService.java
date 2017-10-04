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
    
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException {
        return authorDao.getListOfAuthors();
    }
    
    public int deleteAuthors(String colName, Object keyValue) throws SQLException, ClassNotFoundException {
        return authorDao.deleteAuthors(colName, keyValue);
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
            new MySqlDataAccess("com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin")
        );
        
        AuthorService authorService = new AuthorService(dao);
        
        List<Author> list = authorService.getAuthorList();
                
        for(Author a: list){
            System.out.println(a.getAuthorId() + ","
                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
}
