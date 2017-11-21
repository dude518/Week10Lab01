/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessservice;

import dataaccess.UserDB;
import model.User;

/**
 *
 * @author 578291
 */
public class AccountService {
    private UserDB ub;
    
    public AccountService()
    {
        ub = new UserDB();
    }
    public User loginCheck(String username, String password)
    {
        
        try
        {
            User user = ub.getUser(username);
            if(user.getPassword().equals(password))
            {
                if(user.getActive() == true)
                {
                    return user;
                }
            }
        }
        catch(Exception e)
        {
            e = e;
        }
        return null;
    }
    public boolean isAdmin(String username)
    {
        boolean result = false;
        try
        {
            User user = ub.getUser(username);
            if(user.getRole().getRoleID() == 1)
            {
                result = true;
            }
        }
        catch(Exception e)
        {
            
        }
        
        return result;
    }
}
