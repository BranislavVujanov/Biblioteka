/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.client.session;

import libraryapp.common.domain.UserProfile;

/**
 *
 * @author Branislav Vujanov
 */
public class ApplicationSession {
    
    private static ApplicationSession instance;
    
    private UserProfile loginUser; //user prijavljen na sistem
    
    
    private ApplicationSession(){
        System.out.println("Kreian objekat klase ApplicationSession");
    }
    
    public static ApplicationSession getInstance(){
        if (instance == null) instance = new ApplicationSession();
        return instance;
    }

    public UserProfile getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserProfile loginUser) {
        this.loginUser = loginUser;
    }
    
    
}
