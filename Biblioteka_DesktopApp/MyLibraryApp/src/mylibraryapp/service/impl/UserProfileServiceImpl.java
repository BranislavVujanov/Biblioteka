/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.service.impl;

import java.util.List;
import mylibraryapp.domain.Loan;
import mylibraryapp.domain.UserProfile;
import mylibraryapp.exception.UserMessageException;
import mylibraryapp.repository.GenericRepository;
import mylibraryapp.repository.impl.LoanRepository;
import mylibraryapp.repository.impl.UserProfileRepository;
import mylibraryapp.service.UserProfileService;

/**
 *
 * @author Branislav Vujanov
 */
public class UserProfileServiceImpl implements UserProfileService {

    private final GenericRepository userProfileRepository;
    private final GenericRepository loanRepository;

    public UserProfileServiceImpl() {
        this.userProfileRepository = new UserProfileRepository();
        this.loanRepository = new LoanRepository();
    }

    @Override
    public List<UserProfile> getAll() throws Exception {
        return userProfileRepository.getAll();
    }

    @Override
    public void add(UserProfile userProfile) throws Exception {
        
        String query = "SELECT *\n"
                + "FROM user_profile\n"
                + "WHERE first_name = '"+ userProfile.getFirstName()+"' AND last_name = '"+userProfile.getLastName()+"' AND email = '"+userProfile.getEmail()+"' AND user_role = '"+userProfile.getEmail()+"'";
        List<UserProfile> users = userProfileRepository.findByQuery(query);
        //validacija
        if (users.isEmpty()) userProfileRepository.add(userProfile);
        else throw new UserMessageException("Uneti korisnik vec postoji!");
    }

    @Override
    public void update(UserProfile userProfile) throws Exception {
        userProfileRepository.update(userProfile);
    }

    @Override
    public void delete(UserProfile userProfile) throws Exception {
        //proveri da li korisnik ima trenutnih zaduzenja
        String query = """
                       SELECT l.id, l.issuing_date, l.return_date, l.valid, l.user_profile_id, 
                                               up.first_name, up.last_name, up.email, up.user_role,l.book_id, b.title, b.printing_year, b.quantity
                                               FROM loan l
                                               JOIN user_profile up ON l.user_profile_id = up.id
                                               JOIN book b ON l.book_id = b.id
                                               WHERE valid = 1 AND user_profile_id = """ + userProfile.getId();
        List <Loan> loans = loanRepository.findByQuery(query);
        if (loans.isEmpty()) userProfileRepository.delete(userProfile);
        else throw new UserMessageException("Ovaj korisnik mora prethodno da razduzi sve knjige!\nTek nakon toga bice omoguceno brisanje korisnika");
    }

    @Override
    public List<UserProfile> getAllUsers() throws Exception {
        String query = "SELECT * FROM `user_profile` WHERE user_role = 'user'";
        List<UserProfile> users = userProfileRepository.findByQuery(query);
        
        return users;
    }

    @Override
    public UserProfile login(String email, String password) throws Exception {
        String query = "SELECT *\n" +
                        "FROM user_profile\n" +
                        "WHERE email = '"+ email +"' AND PASSWORD = '" + password + "'";
        List<UserProfile> users = userProfileRepository.findByQuery(query);
        //validacija
        if (users.isEmpty()) throw new UserMessageException("Trazeni korisnik ne postoji u sistemu!");
        
        return users.get(0);
    }

}
