/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package libraryapp.server.service;

import java.util.List;
import libraryapp.common.domain.UserProfile;

/**
 *
 * @author Branislav Vujanov
 */
public interface UserProfileService {
    
     List<UserProfile> getAll() throws Exception;

     void add(UserProfile userProfile) throws Exception;

     void update(UserProfile userProfile) throws Exception;

     void delete(UserProfile userProfile) throws Exception;
     
     List<UserProfile> getAllUsers() throws Exception;
     
     UserProfile login(String email, String password) throws Exception;
    
    
}
