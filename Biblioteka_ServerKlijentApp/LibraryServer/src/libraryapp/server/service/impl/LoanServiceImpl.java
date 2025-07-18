/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.service.impl;

import java.util.List;
import libraryapp.common.domain.Book;
import libraryapp.common.domain.Loan;
import libraryapp.server.repository.GenericRepository;
import libraryapp.server.repository.impl.BookRepository;
import libraryapp.server.repository.impl.LoanRepository;
import libraryapp.server.service.LoanService;

/**
 *
 * @author Branislav Vujanov
 */
public class LoanServiceImpl implements LoanService{
    
    private final GenericRepository loanRepository;
    private final GenericRepository bookRepository;

    public LoanServiceImpl() {
        this.loanRepository = new LoanRepository();
        this.bookRepository = new BookRepository();
    }

    @Override
    public List<Loan> getAll() throws Exception {
        return loanRepository.getAll();
    }

    @Override
    public List<Loan> displayCurrentLoans() throws Exception {
        String query = """
                        SELECT l.id, l.issuing_date, l.return_date, l.valid, l.user_profile_id, 
                        up.first_name, up.last_name, up.email, up.user_role,l.book_id, b.title, b.printing_year, b.quantity
                        FROM loan l
                        JOIN user_profile up ON l.user_profile_id = up.id
                        JOIN book b ON l.book_id = b.id
                        WHERE valid = 1""";
        return loanRepository.findByQuery(query);
    }
    
    @Override
    public List<Loan> displayUserLoans(int id) throws Exception {
        String query = """
                        SELECT l.id, l.issuing_date, l.return_date, l.valid, l.user_profile_id, 
                        up.first_name, up.last_name, up.email, up.user_role,l.book_id, b.title, b.printing_year, b.quantity
                        FROM loan l
                        JOIN user_profile up ON l.user_profile_id = up.id
                        JOIN book b ON l.book_id = b.id
                        WHERE valid = 1 AND user_profile_id =""" + id;
        return loanRepository.findByQuery(query);
    }

    

    @Override
    public void cancelLoan(Loan loan) throws Exception {

            Book book = loan.getBook();
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.update(book);
            loanRepository.updateLoanStatus(loan);
    }

    @Override
    public void add(Loan loan) throws Exception {
        
        loanRepository.add(loan);
        Book book = loan.getBook();
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.update(book);         
    }
}
