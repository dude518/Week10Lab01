/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 578291
 */
public class AdminFilter implements Filter { 
    private FilterConfig filterConfig = null;
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        
        if(session.getAttribute("admin") != null)
        {
            chain.doFilter(request, response);
        }
        else
        {
            ((HttpServletResponse)response).sendRedirect("login");
        }
        
    }

    public void destroy() {   
        
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
}
