/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.server.session;

import java.util.Date;
import libraryapp.common.domain.UserProfile;

/**
 *
 * @author Branislav Vujanov
 */
public class Client extends UserProfile {
    
    private String clientName;
    private Date loginTime;
    

    
     public String getClientName() {
        return clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

   
    
}
