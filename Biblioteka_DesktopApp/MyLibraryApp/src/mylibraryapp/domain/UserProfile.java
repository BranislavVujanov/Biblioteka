/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.domain;

/**
 *
 * @author Branislav Vujanov
 */
public class UserProfile {
    
   
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole userRole;

    
    public UserProfile() {
    }
    
    public UserProfile(int id, String firstName, String lastName, UserRole userRole, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.email = email;
        
    }

    public UserProfile(UserRole userRole, String firstName, String lastName, String email, String password) {
        this.userRole = userRole;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email ;
    }

   

    
}
