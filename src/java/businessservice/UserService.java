/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessservice;

import dataaccess.NotesDBException;
import dataaccess.UserDB;
import java.util.List;
import model.Role;
import model.User;

/**
 *
 * @author 578291
 */
public class UserService {
    private UserDB ub;
    
    public UserService()
    {
        ub = new UserDB();
    }
    
    public User get(String username) throws NotesDBException
    {
        User user = ub.getUser(username);
        return user;
    }
    
    public List<User> getAll() throws NotesDBException
    {
        List<User> users = ub.getAll();
        return users;
    }
    public int insert(String username, String password, String email, boolean active, String firstname, String lastname) throws NotesDBException
    {
        User user = new User(username, password, email, active, firstname, lastname);
        Role role = new Role(2);
        user.setRole(role);
        int i = ub.insert(user);
        return i;
    }
    
    public int update(String username, String password, String email, boolean active, String firstname, String lastname) throws NotesDBException
    {
        User user = ub.getUser(username);
        user.setPassword(password);
        user.setActive(active);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        int i = ub.update(user);
        return i;
    }
    
    public int delete(String username) throws NotesDBException
    {
        User user = get(username);
        int i = ub.delete(user);
        return i;
    }
    
    
    
    
}
