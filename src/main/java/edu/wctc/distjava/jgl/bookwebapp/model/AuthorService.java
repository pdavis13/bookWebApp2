/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author L117student
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    public AuthorService() {
        
    }
    
    public List<Author> findAll() {
        return authorRepo.findAll();
    }
    
    public Author findById(String id) throws DataAccessException{
        authorRepo.findOne(Integer.parseInt(id));
        return null;
    }
    
    public void updateAuthor(String id, String authorName) throws DataAccessException{
        Author author = findById(id);
        author.setAuthorName(authorName);
        authorRepo.save(author);
    }
    
    public void addAuthor(String authorName) throws DataAccessException{
        Date dateAdded = new Date();
        Author author = new Author();
        author.setAuthorName(authorName);
        author.setDateAdded(dateAdded);
        author.setBookSet(new HashSet());
        
        authorRepo.save(author);
    }
    
    public void removeAuthorById(String id) throws DataAccessException{
        Integer value = Integer.parseInt(id);
        
        authorRepo.delete(value);
    }
}
