package Servlets;

import DAOFactory.UserDaoFactory;
import Service.UsersService;
import UserDAO.UserJdbcDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlets extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        UserJdbcDAO userJdbcDAO = new UserJdbcDAO();
        userJdbcDAO.createTable();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("users.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("users.jsp").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJdbcDAO userJdbcDAO = new UserJdbcDAO();
        userJdbcDAO.createTable();
    }
}
