/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.User;

/**
 *
 * @author 578291
 */
public class UserDB {
    public User getUser(String username) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            User user = em.find(User.class, username);
            return user;
        }
        catch(Exception e)
        {
            throw new NotesDBException("Error getting user");
        }
        finally
        {
            em.close();
        }
    }
    
    public int delete(User user) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
            return 1;
        }
        catch(Exception e)
        {
            trans.rollback();
            throw new NotesDBException("Error deleting user");
        }
        finally
        {
            em.close();
        }
    }
    
    public int insert(User user) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.persist(user);
            trans.commit();
            return 1;
        }
        catch(Exception e)
        {
            trans.rollback();
            throw new NotesDBException("Error inserting user");
        }
        finally
        {
            em.close();
        }
    }
    
    public int update(User user) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.merge(user);
            trans.commit();
            return 1;
        }
        catch(Exception e)
        {
            trans.rollback();
            throw new NotesDBException("Error updating user");
        }
        finally
        {
            em.close();
        }
    }
    
    public List<User> getAll() throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try
        {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        }
        catch(Exception e)
        {
            throw new NotesDBException("Error updating user");
        }
        finally
        {
            em.close();
        }
    }
}
