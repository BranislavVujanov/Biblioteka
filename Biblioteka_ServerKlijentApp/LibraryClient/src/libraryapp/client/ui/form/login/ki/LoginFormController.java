/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.client.ui.form.login.ki;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import libraryapp.client.communication.Communication;
import libraryapp.client.controller.Controller;
import libraryapp.common.domain.UserProfile;
import libraryapp.common.exception.UserMessageException;
import libraryapp.client.session.ApplicationSession;
import libraryapp.client.ui.form.MainForm;
import libraryapp.common.transfer.Operation;
import libraryapp.common.transfer.Request;
import libraryapp.common.transfer.Response;


/**
 *
 * @author Branislav Vujanov
 */
public class LoginFormController {
    
    public static void login(JTextField txtEmail, JPasswordField txtPassword, JFrame LoginForm){

        
        String email = txtEmail.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());
        try {
            UserProfile userProfile = Controller.getInstance().login(email, password);
            
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
