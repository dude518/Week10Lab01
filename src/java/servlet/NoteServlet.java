/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import businessservice.NoteService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Note;

/**
 *
 * @author 578291
 */
public class NoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String username = (String)session.getAttribute("username");
        if(session.getAttribute("notes") == null)
        {
            try
            {
                NoteService ns = new NoteService();
                List<Note> notes = ns.getAll(username);
                session.setAttribute("notes", notes);
            }
            catch(Exception e)
            {
                
            }
        }
        if(action != null && !action.equals(""))
        {
            if(action.equals("editview"))
            {
                try
                {
                    NoteService ns = new NoteService();
                    int noteid = Integer.parseInt(request.getParameter("selectednote"));
                    Note note = ns.get(noteid);
                    request.setAttribute("edittitle", note.getTitle());
                    request.setAttribute("editcontent", note.getContents());
                    request.setAttribute("editid", note.getNoteID());
                    request.setAttribute("view", "edit");
                }
                catch(Exception e)
                {

                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/note.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Note> notes = (List<Note>)session.getAttribute("notes");
        String action = request.getParameter("action");
        NoteService ns = new NoteService();
        String username = (String)session.getAttribute("username");
        
        if(action.equals("add"))
        {
            String title = request.getParameter("newtitle");
            String content = request.getParameter("newcontent");
            if(title != null && !title.equals("") && content != null && !content.equals(""))
            {
                try
                {
                    int i = ns.insert(title, content, username);
                    if(i != 1)
                    {
                        throw new Exception();
                    }
                    notes = ns.getAll(username);
                    session.setAttribute("notes", notes);
                    request.setAttribute("message", "Note added.");
                }
                catch(Exception e)
                {
                    e = e;
                }
            }
        }
        else if(action.equals("delete"))
        {
            try
            {
                
                int noteid = Integer.parseInt(request.getParameter("selectednote"));
                int i = ns.delete(noteid, username);
                if(i != 1)
                {
                    throw new Exception();
                }
                notes = ns.getAll(username);
                session.setAttribute("notes", notes);
                request.setAttribute("message", "Note deleted.");
            }
            catch(Exception e)
            {
                
            }
        }
        else if(action.equals("edit"))
        {
            
            String title = request.getParameter("edittitle");
            String content = request.getParameter("editcontent");
            String tempid = request.getParameter("editid");
            int noteid = Integer.parseInt(tempid);
            if(title != null && !title.equals("") && content != null && !content.equals(""))
            {
                try
                {
                    int i = ns.update(noteid, title, content);
                    if(i != 1)
                    {
                        throw new Exception();
                    }
                    notes = ns.getAll(username);
                    session.setAttribute("notes", notes);
                    request.setAttribute("message", "Note changed.");
                }
                catch(Exception e)
                {

                }
            }
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/note.jsp").forward(request, response);
    }

}
