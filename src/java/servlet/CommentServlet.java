/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controller.CommentController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Comment;

/**
 *
 * @author HP ADMIN
 */
public class CommentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //int postID = Integer.parseInt(request.getParameter("postID"));
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        int postID = Integer.parseInt(String.valueOf(session.getAttribute("postID")));
        int accID = acc.getAccID();
        String cmtContent = request.getParameter("cmtcontent");
        
        Comment cmt = new Comment(0, cmtContent, postID, accID);
        CommentController cmtCtrl = new CommentController();
        if(cmtCtrl.addCmt(cmt)){
            request.getRequestDispatcher("/comment.jsp?postID="+postID).forward(request, response);
        } else{
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    

    

}
