/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author L117student
 */
public interface IAuthorDao {

    List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
    
}
