/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package libraryapp.server.repository;

import java.util.List;
import libraryapp.common.domain.Author;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;

/**
 *
 * @author Branislav Vujanov
 * @param <T>
 */
public interface GenericRepository<T, ID> {

    List<T> getAll() throws Exception;

    void add(T t) throws Exception;

    List<T> findByQuery(String query) throws Exception;

    void connectEntities(int bookId, int authorId) throws Exception;

    void update(T t) throws Exception;

    void deleteConnectingEntities(T t) throws Exception;

    void delete(T t) throws Exception;

    List<T> findConnectingEntities(ID id) throws Exception;

    T findById(ID id) throws Exception;

    void updateLoanStatus(Loan loan) throws Exception;



}
