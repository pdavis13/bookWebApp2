package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.Date;
import java.sql.SQLException;
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
public class AuthorDao implements IAuthorDao {
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;

    public AuthorDao(String driverClass, String url, String userName, String password, DataAccess db) {
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }
    
    @Override
    public List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException {
        
        List<Author> list = new Vector<>();
        List<Map<String,Object>> rawData = db.getAllRecords("author", 0);
        
        Author author = null;
        
        for(Map<String,Object> rec : rawData) {
            author = new Author();
            
            Object objRecId = rec.get("author_id");
            Integer recId = objRecId == null ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);
            
            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);
            
            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date)objRecAdded;
            author.setDateAdded(recAdded);
            
            //author.setAuthorId(Integer.parseInt(rec.get("author_id").toString()))
            //author.setAuthorName(rec.get("author_name").toString());
            //author.setDateAdded((Date)rec.get("date_added"));
                    
            list.add(author);
            
            //Integer authorId = null;
            //Object objAuthorId = rec.get("author_id");
            //authorId = Integer.parseInt(objAuthorId.toString());
            
            //Set<String> keys = rec.keySet();
            //for(String key : keys) {
            //    author.setAuthorId(key);
            //}
            
        }
        
        return list;
    }
    
    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    
    
    

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AuthorDao dao = new AuthorDao(
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
