/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mylibraryapp.service;

import java.util.List;
import mylibraryapp.domain.Loan;

/**
 *
 * @author Branislav Vujanov
 */
public interface LoanService {
    
    List<Loan> getAll() throws Exception;
    
    List<Loan> displayCurrentLoans() throws Exception;
    
    List<Loan> displayUserLoans(int id) throws Exception;
    
    void cancelLoan(Loan loan) throws Exception;
    
    void add(Loan loan) throws Exception;
    
}
