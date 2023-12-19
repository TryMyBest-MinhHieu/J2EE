package servlet;

import com.google.gson.Gson;
import controller.LikePostController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnLikePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        int accID = Integer.parseInt(req.getParameter("accID"));
        int postID = Integer.parseInt(req.getParameter("postID"));

        LikePostController likeCtrl = new LikePostController();
        Gson gson = new Gson();
        boolean isUnLike = likeCtrl.UnlikePost(accID, postID);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(isUnLike));
    }

}
