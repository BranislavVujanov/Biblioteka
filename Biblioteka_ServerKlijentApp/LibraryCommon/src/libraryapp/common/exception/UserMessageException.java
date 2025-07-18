/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryapp.common.exception;

import java.io.Serializable;

/**
 *
 * @author Branislav Vujanov
 */
public class UserMessageException extends Exception implements Serializable{

  
    public UserMessageException(String message) {
        super(message);
    }
    
    
}
