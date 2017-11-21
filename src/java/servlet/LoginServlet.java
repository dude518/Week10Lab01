/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import businessservice.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 578291
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = (String)request.getAttribute("action");
        if(action != null && !action.equals(""))
        {
            if(action.equals("logout"))
            {
                HttpSession session = request.getSession();
                session.invalidate();
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService accser = new AccountService();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || username.equals("") || password == null || password.equals(""))
        {
            request.setAttribute("message", "Both username and password must be filled out.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        if(accser.loginCheck(username, password) != null)
        {
            
            session.setAttribute("username", username);
            if(accser.isAdmin(username))
            {
                session.setAttribute("admin", "yes");
                response.sendRedirect("admin");
                return;
            }
            
            response.sendRedirect("notes");
            return;
            
        }
        else
        {
            request.setAttribute("message", "Either username or password was incorrect, try again.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }

}
