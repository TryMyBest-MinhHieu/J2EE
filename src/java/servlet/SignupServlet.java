/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controller.AccountController;
import controller.UserController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.User;
import util.StoreUploadImage;

/**
 *
 * @author HP ADMIN
 */
@MultipartConfig
public class SignupServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        AccountController accCtrl = new AccountController();
        UserController userCtrl = new UserController();

        String email = request.getParameter("email");
        if (accCtrl.checkEmail(email)) {
            request.setAttribute("message", "Your email already exist");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        } else {
            String fullName = request.getParameter("fullname");
            String dob = request.getParameter("dob");
            String sex = request.getParameter("sex");
            User user = new User(0, fullName, dob, sex);
            boolean addUser = userCtrl.addUser(user);

            String bio = request.getParameter("bio");
            String password = request.getParameter("password");
            String cfpassword = request.getParameter("cfpassword");
            String username = request.getParameter("username");

            Part file = request.getPart("image");
            String imageFile = "";
            if(file.getSubmittedFileName().isEmpty()){
                imageFile = "user.jpg";
            } else{
                imageFile = file.getSubmittedFileName();
            }
            String uploadPath = getServletContext().getRealPath("/resources/img/") + imageFile;

            StoreUploadImage uploadImg = new StoreUploadImage();
            uploadImg.StoreImage(file, imageFile, uploadPath);

            int userID = userCtrl.getUserID(fullName, dob, sex);
            Account acc = new Account(0, username, password, bio, email, imageFile, userID);
            boolean addAcc = accCtrl.addAcc(acc);

            if (password.equals(cfpassword)) {
                if (addUser && addAcc) {
                    Account account = accCtrl.getAccount(email, password);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("account", account);
                    request.getRequestDispatcher("/newfeed.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Fail to Sign up");
                    request.getRequestDispatcher("/signup.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Your password doesnt match");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            }
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
