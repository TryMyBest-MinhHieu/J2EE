/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
import controller.FriendController;
import controller.MessageController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP ADMIN
 */
public class AddFriendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        int accID = Integer.parseInt(req.getParameter("id_acc"));
        int friendID = Integer.parseInt(req.getParameter("id_friend"));
        int msID = Integer.parseInt(req.getParameter("id"));
        FriendController friendCtrl = new FriendController();
        MessageController msgCtrl = new MessageController();
        if (!friendCtrl.isFriend(accID, friendID)) {
            boolean result = friendCtrl.addFriend(accID, friendID);
            boolean rs = friendCtrl.addFriend(friendID, accID);
            boolean remove = msgCtrl.deleteMess(msID);
            if (result && remove) {
                req.getRequestDispatcher("/message.jsp").forward(req, resp);
            } else {

            }
        }

    }

}
