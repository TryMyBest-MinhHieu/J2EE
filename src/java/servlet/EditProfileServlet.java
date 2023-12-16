/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controller.AccountController;
import controller.UserController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class EditProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountController accCtrl = new AccountController();
        UserController userCtrl = new UserController();

        String fullName = request.getParameter("fullname");
        String dob = request.getParameter("dob");
        String sex = request.getParameter("sex");
        User user = new User(account.getUserID(), fullName, dob, sex);

        String email = request.getParameter("email");
        String bio = request.getParameter("bio");
        String username = request.getParameter("username");
        String image = "";
        Part file = request.getPart("image");
        String imageFile = file.getSubmittedFileName();
        if (imageFile.isEmpty()) {
            image = account.getImage();
        } else {
            image = imageFile;
            String uploadPath = getServletContext().getRealPath("/resources/img/") + imageFile;
            StoreUploadImage uploadImg = new StoreUploadImage();
            uploadImg.StoreImage(file, imageFile, uploadPath);
        }

        int userID = account.getUserID();
        Account acc = new Account(account.getAccID(), username, "", bio, email, image, userID);
        if (userCtrl.updateUser(user) && accCtrl.updateAcc(acc)) {
            Account newAcc = accCtrl.getAccountByID(account.getAccID());
            session.setAttribute("account", acc);
            request.setAttribute("message", "Update Success");
            request.getRequestDispatcher("/editprofile.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Update Fail");
            request.getRequestDispatcher("/editprofile.jsp").forward(request, response);
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
