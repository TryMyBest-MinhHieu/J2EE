<%@page import="controller.MessageController"%>
<%@page import="controller.AccountController"%>
<%@page import="model.Friend"%>
<%@page import="java.util.List"%>
<%@page import="controller.FriendController"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="./resources/css/friend.css" rel="stylesheet">
        <title>Friend</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <%
            if (session.getAttribute("account") == null) {
                out.print("<h1 class=\"title-session\">Please login</h1>");
                return;
            }
        %>
        
        <%
            Account acc = (Account) session.getAttribute("account");
            String searchValue = request.getParameter("data");
            AccountController accCtrl = new AccountController();
            FriendController friendCtrl = new FriendController();
            List<Account> list = accCtrl.searchAcc(searchValue);
            MessageController msgController = new MessageController();
            out.println("<div class=\"container friend-container margin-header\">");
            if (list.isEmpty()) {
                out.print("<h1 class=\"container\">No result</h1>");
            } else {
                for (Account account : list) {
                    int friendID = account.getAccID();
                    if (acc.getAccID() == friendID)
                    {
                        out.print("<h1 class=\"container\">No result</h1>");
                    }
                    else{
                    out.println("<div class=\"search-result\">");
                    out.println("<div class=\"row item-search\">");
                    out.println("<div class=\"col-md-1\">");
                    out.println("<img src=\"./resources/img/" + account.getImage() + "\" alt=\"User Profile\" class=\"profile-pic\">");
                    out.println("</div>");
                    out.println("<div class=\"col-md-8\">");
                    out.println("<h4><a href=\"profile.jsp?accID="+account.getAccID()+"\">" + account.getUsername() + "</a></h4>");
                    out.println("</div>");
                    out.println("<div class=\"col-md-2\">");
                    if (friendCtrl.isFriend(acc.getAccID(), friendID)) {
                        out.println("<button id=\"follow-btn-"+friendID+"\" class=\"btn btn-outline-success btn-search\" onclick=\"beFriend(" + acc.getAccID() + "," + friendID + ")\">Unfriend</button>");
                    } else if(msgController.isMess(acc.getAccID(), friendID)) {
                        out.println("<button id=\"follow-btn-"+friendID+"\" class=\"btn btn-outline-success btn-search\" onclick=\"beFriend(" + acc.getAccID() + "," + friendID + ")\">Waiting</button>");
                    } else {
                        out.println("<button id=\"follow-btn-"+friendID+"\" class=\"btn btn-outline-success btn-search\" onclick=\"beFriend(" + acc.getAccID() + "," + friendID + ")\">Friend</button>");
                    }
            
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                    }
                    
                }
            }
            out.println("</div>");

        %>


        <script>
            function beFriend(accID, friendID) {
                var button = document.getElementById("follow-btn-"+friendID+"");
                var buttonText = button.textContent;
                if (buttonText === "Friend") {
                    $.ajax({
                        type: "POST",
                        url: "AddMessageServlet",
                        data: {accID: accID, friendID: friendID},
                        success: function (data) {
                            button.textContent = "Waiting";
                        }
                    });
                } else if(buttonText === "Unfriend"){
                    $.ajax({
                        type: "POST",
                        url: "UnFriendServlet",
                        data: {accID: accID, friendID: friendID},
                        success: function (data) {

                            button.textContent = "Friend";
                        }
                    });
                } else if(buttonText === "Waiting"){
                    $.ajax({
                        type: "POST",
                        url: "RemoveMessageServlet",
                        data: {accID: accID, friendID: friendID},
                        success: function (data) {
                            button.textContent = "Friend";
                        }
                    });
                }

            }

        </script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </body>
</html>
