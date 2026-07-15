/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package libraryapp.server.service;

import java.util.List;
import libraryapp.common.domain.Author;
import libraryapp.common.domain.Book;

/**
 *
 * @author Branislav Vujanov
 */
public interface BookService {

    List<Book> getAll() throws Exception;

    void add(Book book) throws Exception;

    void update(Book book) throws Exception;

    void delete(Book book) throws Exception;

    List<Book> findBookByAuthor(int id) throws Exception;

   

}
