/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import libraryapp.common.domain.Author;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.common.exception.UserMessageException;
import libraryapp.server.repository.GenericRepository;
import libraryapp.server.repository.impl.AuthorRepository;
import libraryapp.server.repository.impl.BookRepository;
import libraryapp.server.repository.impl.LoanRepository;
import libraryapp.server.service.BookService;

/**
 *
 * @author Branislav Vujanov
 */
public class BookServiceImpl implements BookService {

    private final GenericRepository bookRepository;
    private final GenericRepository authorRepository;
    private final GenericRepository loanRepository;

    public BookServiceImpl() {
        bookRepository = new BookRepository();
        authorRepository = new AuthorRepository();
        this.loanRepository = new LoanRepository();
    }

    @Override
    public List<Book> getAll() throws Exception {

        List<Book> books = bookRepository.getAll();

        for (Book book : books) {

            String query = """
                           SELECT a.id, a.first_name, a.last_name
                           FROM  writing w
                           JOIN author a ON w.author_id = a.id
                           WHERE book_id =""" + book.getId();
            List<Author> authors = authorRepository.findByQuery(query);
            book.setAuthors(authors);
        }
        return books;
    }

    @Override
    public void add(Book book) throws Exception{
        String query ="""
                      SELECT * FROM book
                      WHERE title = '"""+ book.getTitle() +"' AND printing_year = '"+ book.getPrintingYear()+"' AND quantity = '"+ book.getQuantity() +"'";
        List<Book> books = bookRepository.findByQuery(query);
        
        if (books.isEmpty()) bookRepository.add(book);
        else throw new UserMessageException("Ova knjiga vec postoji u biblioteci!");
        
        //povezi autore sa knjigom
        List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                int bookId = book.getId();
                int authorId = author.getId();
                bookRepository.connectEntities(bookId, authorId);
            }          
    }

    @Override
    public void update(Book book) throws Exception {
        //update knjige
        bookRepository.update(book);
        //izbrisi knjigu i sve njene predjasnje autore iz tabele writing
        bookRepository.deleteConnectingEntities(book);  
        //povezi knjigu sa novim autorima u tabeli writing
        List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                bookRepository.connectEntities(book.getId(), author.getId());
            } 
    }

    @Override
    public void delete(Book book) throws Exception {
        //proveri da li korisnik ima trenutnih zaduzenja
        String query = """
                       SELECT l.id, l.issuing_date, l.return_date, l.valid, l.user_profile_id, 
                                               up.first_name, up.last_name, up.email, up.user_role,l.book_id, b.title, b.printing_year, b.quantity
                                               FROM loan l
                                               JOIN user_profile up ON l.user_profile_id = up.id
                                               JOIN book b ON l.book_id = b.id
                                               WHERE valid = 1 AND book_id = """ + book.getId();
        List <Loan> loans = loanRepository.findByQuery(query);
        if (loans.isEmpty()) bookRepository.delete(book);
        else throw new UserMessageException("Ova knjiga je trenutno zaduzena!\nTek nakon razduzivanja bice omoguceno brisanje knjige");
    }

    @Override
    public List<Book> findBookByAuthor(int id) throws Exception {
        List<Book> booksId = bookRepository.findConnectingEntities(id);

        List<Book> books = new ArrayList<>();
        if (booksId != null){
            for (Book b:booksId){
                Book book = (Book) bookRepository.findById(b.getId());
                books.add(book);
                
            }  
        }
        return books;
    }

   

   
}
