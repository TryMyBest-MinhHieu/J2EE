<%@page import="model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="controller.CommentController"%>
<%@page import="controller.AccountController"%>
<%@page import="model.Post"%>
<%@page import="controller.PostController"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="./resources/css/comment.css" rel="stylesheet">

        <title>Comment</title>
    </head>
    <body style="
              background-color: #f0f2f5;
">
        <jsp:include page="navbar.jsp"/>
        <%
            if (session.getAttribute("account") == null) {
                out.print("<h1 class=\"title-session\">Please login</h1>");
                return;
            }
        %>

        <div class="container margin-header postcontainer">
            <!-- Post Section -->
            <div class="card mb-3 contentcomment">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <div>
                        <%
                            int postID = Integer.parseInt(request.getParameter("postID"));
                            HttpSession postSession = request.getSession(true);
                            postSession.setAttribute("postID", postID);
                            PostController postCtrl = new PostController();
                            AccountController accCtrl = new AccountController();
                            Post post = postCtrl.getPostByID(postID);
                            Account account = accCtrl.getAccountByID(post.getAccID());
                            out.print("<img src=\"./resources/img/" + account.getImage() + "\" alt=\"Profile Picture\" class=\"profile-pic me-2 \">");
                            out.print("<span class=\"fw-bold\">" + account.getUsername() + "</span>");
                        %>

                    </div>
                    <div>
                        <%
                            out.print("<small class=\"text-muted\">" + post.getDate() + "</small>");
                        %>
                    </div>
                </div>
                <div class="card-body">
                    <%
                        out.print("<p class=\"card-text\">" + post.getCaption() + "</p>");
                        if(post.getImage().isEmpty()){
                            
                        } else{
                            out.println("<img src=\"./resources/img/" + post.getImage() + "\" alt=\"Posted Image\" class=\"img-fluid postimage\" width = \"400\" height = \"400\">");
                        }
                    %>
                </div>
            </div>
            <form class="input-group mb-3" method="post" action="CommentServlet">
                <input type="text" class="form-control" name="cmtcontent" placeholder="Write a comment">
                <button class="btn btn-primary" type="submit"><i class="ti-marker-alt">submit</i></button>
            </form>
            <!-- Comment Section -->
            <div class="card mb-3">
                <div class="card-body comment-section">

                    <%
                        CommentController cmtCtrl = new CommentController();
                        List<Comment> cmts = cmtCtrl.getCommentOfPost(postID);
                        if (cmts.isEmpty()) {
                            out.print("<h1>No comment in this post</h1>");
                        } else {
                            for (Comment cmt : cmts) {
                                Account accCmt = accCtrl.getAccountByID(cmt.getAccID());
                                out.print("<div class=\"media mb-3 media-container\">");
                                out.print("<img src=\"./resources/img/"+accCmt.getImage()+"\" alt=\"Profile Picture\" class=\"profile-pic me-2\">");
                                out.print("<span class=\"fw-bold\">"+accCmt.getUsername()+"</span>");
                                out.print("<div class=\"media-body\">");
                                out.print("<p>" + cmt.getContent() + "</p>");
                                out.print("</div>");
                                out.print("</div>");
                            }
                        }

                    %>
                </div>
            </div>
        </div>
    </body>
</html>
