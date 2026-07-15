/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylibraryapp.ui.form.login.ki;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mylibraryapp.domain.UserProfile;
import mylibraryapp.exception.UserMessageException;
import mylibraryapp.service.UserProfileService;
import mylibraryapp.service.impl.UserProfileServiceImpl;
import mylibraryapp.session.ApplicationSession;
import mylibraryapp.ui.form.MainForm;

/**
 *
 * @author Branislav Vujanov
 */
public class LoginFormController {
    
    public static void login(JTextField txtEmail, JPasswordField txtPassword, JFrame LoginForm){
        UserProfileService userProfileService = new UserProfileServiceImpl();
        
        String email = txtEmail.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());
        try {
            UserProfile userProfile = userProfileService.login(email, password);
            
            //postavi prijavljenog korisnika u Session obj
            ApplicationSession.getInstance().setLoginUser(userProfile);

            
            new MainForm().setVisible(true);
            LoginForm.dispose();
            
        } catch (UserMessageException e) {
            JOptionPane.showMessageDialog(LoginForm, e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
}
