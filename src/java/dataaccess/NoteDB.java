/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Note;
import model.User;

/**
 *
 * @author 578291
 */
public class NoteDB {
    public Note getNote(int noteID) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try
        {
            Note note = em.find(Note.class, noteID);
            return note;
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
    
    public int delete(Note note, User owner) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            owner.getNoteCollection().remove(note);
            trans.begin();
            em.remove(em.merge(note));
            em.merge(owner);
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
    
    public Note getRecent() throws NotesDBException
    {
        int i = 0;
        Note recent;
        List<Note> notes = getAll();
        for(Note note : notes)
        {
            if(note.getNoteID() > i)
            {
                i = note.getNoteID();
            }
        }
        recent = getNote(i);
        return recent;
        
    }
    
    public int insert(Note note, User owner) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            note.setOwner(owner);
            owner.getNoteCollection().add(note);
            trans.begin();
            em.persist(note);
            em.merge(owner);
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
    
    public int update(Note note) throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try
        {
            trans.begin();
            em.merge(note);
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
    
    public List<Note> getAll() throws NotesDBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try
        {
            List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return notes;
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
