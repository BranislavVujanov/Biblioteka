/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.service.impl;

import java.util.List;
import libraryapp.common.domain.Author;
import libraryapp.common.exception.UserMessageException;
import libraryapp.server.repository.GenericRepository;
import libraryapp.server.repository.impl.AuthorRepository;
import libraryapp.server.service.AuthorService;

/**
 *
 * @author Branislav Vujanov
 */
public class AuthorServiceImpl implements AuthorService {

    private final GenericRepository authorRepository;

    public AuthorServiceImpl() {
        this.authorRepository = new AuthorRepository();
    }

    @Override
    public List<Author> getAll() throws Exception {
        return authorRepository.getAll();
    }

    @Override
    public void add(Author author) throws Exception {
        
        String query = """
                       SELECT * FROM author
                       WHERE first_name = '""" + author.getFirstName() + "' AND last_name = '" + author.getLastName() + "'";
        List<Author> authors = authorRepository.findByQuery(query);
        
        if (authors.isEmpty()) authorRepository.add(author);
        else throw new UserMessageException("Autor sa tim imenom vec postoji!");
    }

    @Override
    public void update(Author author) throws Exception {
        authorRepository.update( author);
    }

}
