/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public Author findById(String id) {
        authorRepo.findOne(Integer.parseInt(id));
        return null;
    }
    
    public void addAuthor(String authorName){
        Date dateAdded = new Date();
        Author author = new Author();
        author.setAuthorName(authorName);
        author.setDateAdded(dateAdded);
        
        authorRepo.save(author);
    }
}
