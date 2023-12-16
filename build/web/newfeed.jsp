<%@page import="controller.AdvertisementController"%>
<%@page import="model.Advertisement"%>
<%@page import="model.Friend"%>
<%@page import="controller.FriendController"%>
<%@page import="controller.CommentController"%>
<%@page import="controller.AccountController"%>
<%@page import="controller.PostController"%>
<%@page import="java.util.List"%>
<%@page import="model.Post"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link href="./resources/css/newfeed.css" rel="stylesheet">
        <title>New Feed</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <%
            if (session.getAttribute("account") == null) {
                out.print("<h1 class=\"title-session\">Login to use</h1>");
                return;
            }
        %>
        <div class="row back-ground">
            <div class="col-2 quangcao">             
<!--                 <h4>Advetisement</h4>  -->              
               <%
                    AdvertisementController adCtrl = new AdvertisementController();
                    List<Advertisement> listAd = adCtrl.getAll();
                    for (Advertisement ad : listAd) {
                        out.println("<div class=\"post-container align-items-center\">");
                        out.println("<p class=\"post-content\">" + ad.getDiscription() + "</p>");
                        out.println("<img src=\"./resources/img/" + ad.getImage() + "\" alt=\"Posted Image\" class=\"img-fluid\" width = \"400\" height = \"400\">");
                        out.println("</div>");
                    }

                %>   
            </div>
            
            <%                out.println("<div class=\"col-8 main-col\">");
                Account acc = (Account) session.getAttribute("account");
                PostController postCtrl = new PostController();
                AccountController accCtrl = new AccountController();
                CommentController cmtCtrl = new CommentController();
                List<Post> posts = postCtrl.getPosts(acc.getAccID());
                List<Post> friendPosts = postCtrl.getPostsFriend(acc.getAccID());
                if (posts.isEmpty()) {
//                    out.print("<h1 class=\"container\">No post in your account</h1>");
                } else {
                    for (Post post : posts) {
                        out.println("<div class=\"container postcontainer\">");

                        out.println("<div class=\"card-header d-flex justify-content-between align-items-center\">");

                        out.println("<div class=\"col-md-1\">");
                        out.println("<img src=\"./resources/img/" + acc.getImage() + "\" alt=\"User Profile\" class=\"profile-pic me-2 pic\">");
                        out.println("</div>");
                        out.println("<div class=\"col-md-10\">");
                        out.println("<h5 class=\"fw-bold\">" + acc.getUsername() + "</h5>");
                        out.println("<small class=\"text-muted\">" + post.getDate() + "</small>");
                        out.println("</div>");

                        out.println("</div>");

                        out.println("<div class=\"row\">");
                        out.println("<div class=\"col-md-12 post-content\">");
                        out.println("<div class=\"post-container\">");
                        out.println("<p class=\"post-content\">" + post.getCaption() + "</p>");
                        if (post.getImage().isEmpty()) {

                        } else {
                            out.println("<img src=\"./resources/img/" + post.getImage() + "\" alt=\"Posted Image\" class=\"img-fluid postimage\" width = \"400\" height = \"400\">");
                        }
                        out.println("</div>");
                        out.println("</div>");
                        out.println("<p class=\"post-content\">" + cmtCtrl.countCmt(post.getPostID()) + " Comment</p>");
                        out.println("</div>");

                        out.println("<div class=\"row\">");
                        out.println("<div class=\"col-md-12\">");
                        out.println("<div class=\"d-flex justify-content-between\">");
                        out.println("<div class=\"like-comment-share blueText\">");
                        out.println("<i class=\"far ti-thumb-up\"></i> Like");
                        out.println("</div>");
                        out.println("<div class=\"like-comment-share\">");
                        out.println("<a class = \"text-comment\" href = \"comment.jsp?postID=" + post.getPostID() + "\">Comment</a>");
                        out.println("</div>");
                        out.println("<div class=\"like-comment-share blueText\">");
                        out.println("<i class=\"far ti-sharethis\"></i> Share");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("</div>");

                        out.println("</div>");
                    }

                }

                for (Post post : friendPosts) {
                    Account account = accCtrl.getAccountByID(post.getAccID());
                    out.println("<div class=\"container postcontainer\">");

                    out.println("<div class=\"card-header d-flex justify-content-between align-items-center\">");

                    out.println("<div class=\"col-md-1\">");
                    out.println("<img src=\"./resources/img/" + account.getImage() + "\" alt=\"User Profile\" class=\"profile-pic me-2 pic\">");
                    out.println("</div>");
                    out.println("<div class=\"col-md-10\">");
                    out.println("<h5 class=\"fw-bold\">" + account.getUsername() + "</h5>");
                    out.println("<small class=\"text-muted\">" + post.getDate() + "</small>");
                    out.println("</div>");

                    out.println("</div>");

                    out.println("<div class=\"row\">");
                    out.println("<div class=\"col-md-12 post-content\">");
                    out.println("<div class=\"post-container\">");
                    out.println("<p class=\"post-content\">" + post.getCaption() + "</p>");
                    if (post.getImage().isEmpty()) {

                    } else {
                        out.println("<img src=\"./resources/img/" + post.getImage() + "\" alt=\"Posted Image\" class=\"img-fluid postimage\" width = \"400\" height = \"400\">");
                    }
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<p class=\"post-content\">" + cmtCtrl.countCmt(post.getPostID()) + " Comment</p>");
                    out.println("</div>");

                    out.println("<div class=\"row\">");
                    out.println("<div class=\"col-md-12\">");
                    out.println("<div class=\"d-flex justify-content-between\">");
                    out.println("<div class=\"like-comment-share blueText\">");
                    out.println("<i class=\"ti-thumb-up blueText\"></i> Like");
                    out.println("</div>");
                    out.println("<div class=\"like-comment-share\">");
                    out.println("<i class=\"far fa-comment-alt\"></i><a class = \"text-comment\" href = \"comment.jsp?postID=" + post.getPostID() + "\">Comment</a>");
                    out.println("</div>");
                    out.println("<div class=\"like-comment-share blueText\">");
                    out.println("<i class=\"ti-sharethis blueText\"></i> Share");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");

                    out.println("</div>");
                }
                out.println("</div>");
                out.println("<div class=\"col-2\">");
                out.println("<div class=\"friend\">");
                out.println("<h4>Friend List</h4>");

                FriendController friendCtrl = new FriendController();
                List<Friend> list = friendCtrl.getFollowing(acc.getAccID());
                if (list.isEmpty()) {
                    out.print("<h1 class=\"container\">Your friend list is empty</h1>");
                } else {
                    for (Friend fri : list) {
                        Account friend = accCtrl.getAccountByID(fri.getfriendID());
                        out.println("<div class=\"friend-element\">");
                        out.println("<div class=\"row\">");
                        out.println("<div class=\"col-4\">");
                        out.println("<img src=\"./resources/img/" + friend.getImage() + "\" alt=\"User Profile\" class=\"profile-pic\">");
                        out.println("</div>");
                        out.println("<div class=\"col-4\">");
                        out.println("<h4>" + friend.getUsername() + "</h4>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("</div>");
                    }
                }

                out.println("</div>");
                out.println("</div>");

            %>

        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>


    </body>
</html>
