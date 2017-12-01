/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.jgl.bookwebapp.repository.BookRepository;
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
public class BookService {

    @Autowired
    private BookRepository bookRepo;
    
    @Autowired
    private AuthorRepository authorRepo;    
    
    public BookService() {
        
    }
    
    public void addNewBook(String title, String isbn, String authorId){
        Author author = authorRepo.findOne(Integer.parseInt(authorId));
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthorId(author);
        bookRepo.save(book);
    }
    
}
