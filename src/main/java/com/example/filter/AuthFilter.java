package com.example.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
@WebFilter("/user/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
            HttpSession session = ((HttpServletRequest)servletRequest).getSession();

            if (Objects.isNull((session.getAttribute("user")))){
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/login.jsp");
                dispatcher.forward(servletRequest,servletResponse);
        }
            filterChain.doFilter(servletRequest,servletResponse);
    }
}