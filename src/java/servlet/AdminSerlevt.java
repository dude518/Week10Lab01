/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import businessservice.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author 578291
 */
public class AdminSerlevt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if(session.getAttribute("users") == null)
        {
            try
            {
                UserService us = new UserService();
                List<User> users = us.getAll();
                session.setAttribute("users", users);
            }
            catch(Exception e)
            {
                request.setAttribute("message", "Error loading list");
            }
        }
        if(action != null && !action.equals(""))
        {
            if(action.equals("editview"))
            {
                try
                {
                    UserService us = new UserService();
                    String username = request.getParameter("selectedname");
                    User user = us.get(username);
                    request.setAttribute("editusername", user.getUsername());
                    request.setAttribute("editpassword", user.getPassword());
                    request.setAttribute("editemail", user.getEmail());
                    request.setAttribute("editfirstname", user.getFirstname());
                    request.setAttribute("editlastname", user.getLastname());
                    request.setAttribute("view", "edit");
                }
                catch(Exception e)
                {

                }
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<User> users = (List<User>)session.getAttribute("users");
        String action = request.getParameter("action");
        UserService us = new UserService();
        
        if(action.equals("add"))
        {
            String username = request.getParameter("newusername");
            String password = request.getParameter("newpassword");
            String firstname = request.getParameter("newfirstname");
            String lastname = request.getParameter("newlastname");
            String email = request.getParameter("newemail");
            
            //checks if null
            if(username != null && !username.equals("") && password != null && !password.equals("") && firstname != null && !firstname.equals("") && lastname != null && !lastname.equals("") && email != null && !email.equals(""))
            {
                try
                {
                    int i = us.insert(username, password, email, true, firstname, lastname);
                    if(i != 1)
                    {
                        throw new Exception();
                    }
                    users = us.getAll();
                    request.setAttribute("message", "User added.");
                    session.setAttribute("users", users);
                }
                catch(Exception e)
                {
                    
                }
            }
            
        }
        else if(action.equals("delete"))
        {
            try
            {
                String username = request.getParameter("selectedname");
                int i = us.delete(username);
                if(i != 1)
                {
                    throw new Exception();
                }
                users = us.getAll();
                session.setAttribute("users", users);
                request.setAttribute("message", "User deleted");
            }
            catch(Exception e)
            {
                
            }
        }
        else if(action.equals("edit"))
        {
            String username = request.getParameter("editusername");
            String password = request.getParameter("editpassword");
            String firstname = request.getParameter("editfirstname");
            String lastname = request.getParameter("editlastname");
            String email = request.getParameter("editemail");
            
            //checks if null
            if(username != null && !username.equals("") && password != null && !password.equals("") && firstname != null && !firstname.equals("") && lastname != null && !lastname.equals("") && email != null && !email.equals(""))
            {
                try
                {
                    int i = us.update(username, password, email, true, firstname, lastname);
                    if(i != 1)
                    {
                        throw new Exception();
                    }
                    users = us.getAll();
                    request.setAttribute("message", "User Updates.");
                    session.setAttribute("users", users);
                }
                catch(Exception e)
                {
                    
                }
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

}
