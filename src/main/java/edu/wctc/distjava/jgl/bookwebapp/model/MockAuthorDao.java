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
    public int deleteAuthorByID(Integer Id) throws SQLException, ClassNotFoundException {
        
        int deletedAuthorsCount = 0;
        
        return deletedAuthorsCount;
    }
    
    @Override
    public void addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException{
                
    }
    
    @Override
    public Author getAuthorById(int id) throws SQLException, ClassNotFoundException{
        return new Author(1,"John Doe", new Date());
    }
    
    @Override
    public int updateAuthorDetails(List<Object> colValues, Object pkValue) throws SQLException, ClassNotFoundException{
        int updatedAuthors = 0;
        
        return updatedAuthors;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
      
       IAuthorDao dao = new AuthorDao(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/book",
            "root", "admin",
            new MySqlDataAccess()
        );
        
        List<Author> list = dao.getListOfAuthors();
        
        for(Author a: list){
            System.out.println(a.getAuthorId() + ","
                + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
    }
}
