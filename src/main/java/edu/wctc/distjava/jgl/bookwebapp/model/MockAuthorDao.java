package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author L117student
 */
public class MockAuthorDao implements IAuthorDao {

    public MockAuthorDao() {
        
    }
    
    @Override
    public List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException {
        
        List<Author> list = Arrays.asList(
                new Author(1,"John Doe", new Date()),
                new Author(2, "Alice Bobbington", new Date())
        );
        
        return list;
    }
    
    @Override
    public int deleteAuthors(String colName, Object keyValue) throws SQLException, ClassNotFoundException {
        
        int deletedAuthorsCount = 0;
        
        return deletedAuthorsCount;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       // IAuthorDao dao = new MockAuthorDao(
       
       IAuthorDao dao = new AuthorDao(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin",
            new MySqlDataAccess("com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin")
        );
        
        List<Author> list = dao.getListOfAuthors();
        
        for(Author a: list){
            System.out.println(a.getAuthorId() + ","
                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
}
