package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = null;

        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))){
            dispatcher = req.getRequestDispatcher("/login.jsp");
        }else {
             dispatcher = req.getRequestDispatcher("/user/hello.jsp");
        }
        dispatcher.forward(req,resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> users = Users.getInstance().getUsers();

        String login =  req.getParameter("login");
        String password = req.getParameter("password");

        if (users.contains(login) && !password.isEmpty()){
            HttpSession session = req.getSession();
            session.setAttribute("user",login);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/user/hello.jsp");
            dispatcher.forward(req,resp);
        }else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req,resp);
        }

    }
}
