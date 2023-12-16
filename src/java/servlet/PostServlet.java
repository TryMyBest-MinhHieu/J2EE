/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controller.PostController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Post;
import util.StoreUploadImage;

/**
 *
 * @author HP ADMIN
 */
@MultipartConfig
public class PostServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        PostController postCtrl = new PostController();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        
        // xử lý lưu trữ hình ảnh
        Part file = request.getPart("image");
        String imageFile = file.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("/resources/img/") + imageFile;
        /*FileOutputStream fos = new FileOutputStream(uploadPath);
        InputStream is = file.getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);
        fos.write(data);
        fos.close();*/
        StoreUploadImage uploadImg = new StoreUploadImage();
        uploadImg.StoreImage(file, imageFile, uploadPath);
        
        // lấy thông tin ảnh, caption, accid
        String caption = request.getParameter("caption");
        String image = request.getParameter("image");
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        int accID = acc.getAccID();
        // tạo đối tượng bài viết
        Post post = new Post(0, caption, imageFile, date, accID);
        
        if (postCtrl.addPost(post)) { // gọi tới hàm thêm bài viết
            request.getRequestDispatcher("/newfeed.jsp").forward(request, response);// trả về true thì gọi tới trang newfeed

        } else {// trả về false thì trả message và ở lại trang
            request.setAttribute("message", "Error");// message là đối tg, được set là error
            request.getRequestDispatcher("/addpost.jsp").forward(request, response);
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
