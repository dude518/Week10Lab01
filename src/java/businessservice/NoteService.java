/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessservice;

import dataaccess.NoteDB;
import dataaccess.NotesDBException;
import java.util.Date;
import java.util.List;
import model.Note;
import model.User;

/**
 *
 * @author 578291
 */
public class NoteService {
    private NoteDB nb;
    
    public NoteService()
    {
        nb = new NoteDB();
    }
    
    public Note get(int id) throws NotesDBException
    {
        Note note = nb.getNote(id);
        return note;
    }
    
    public List<Note> getAll(String username) throws NotesDBException
    {
        UserService us = new UserService();
        User user = us.get(username);
        List<Note> notes = nb.getAll();
        List<Note> actual = notes;
        for(Note note : notes)
        {
            if(!note.getOwner().getUsername().equals(user.getUsername()))
            {
                actual.remove(note);
            }
        }
        return actual;
    }
    
    public int insert(String title, String content, String username) throws NotesDBException
    {
        Date date = new Date();
        UserService us = new UserService();
        User owner = us.get(username);
        Note note = new Note(0, date, title, content);
        int i = nb.insert(note, owner);
        return i;
    }
    public int delete(int id, String username) throws NotesDBException
    {
        UserService us = new UserService();
        User owner = us.get(username);
        Note note = get(id);
        int i = nb.delete(note, owner);
        return i;
    }
    public int update(int id, String title, String content) throws NotesDBException
    {
        Note note = nb.getNote(id);
        note.setContents(content);
        note.setTitle(title);
        int i = nb.update(note);
        return i;
    }
}
