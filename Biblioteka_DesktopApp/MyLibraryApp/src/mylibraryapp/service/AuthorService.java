/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mylibraryapp.service;

import java.util.List;
import mylibraryapp.domain.Author;

/**
 *
 * @author Branislav Vujanov
 */
public interface AuthorService {
    
    List<Author> getAll() throws Exception;

    void add(Author author) throws Exception;

    public void update(Author selectedAuthor) throws Exception;
}
