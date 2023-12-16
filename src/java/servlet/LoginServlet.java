/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.AccountController;
import javax.servlet.http.HttpSession;
import model.Account;
/**
 *
 * @author HP ADMIN
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccountController ac = new AccountController();
        if(ac.checkLogin(email, password)){
            Account acc = ac.getAccount(email, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("account", acc);
            session.setAttribute("id", acc.getAccID()); // lấy id tài khoản đăng nhập
            request.getRequestDispatcher("newfeed.jsp").forward(request, response);
        } else{
            request.setAttribute("message", "Invalid User");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    

}
