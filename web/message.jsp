<%@page import="servlet.AddFriendServlet"%>
<%@page import="controller.AccountController"%>
<%@page import="model.Message"%>
<%@page import="java.util.List"%>
<%@page import="controller.MessageController"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link href="./resources/css/friend.css" rel="stylesheet">
        <title>Message</title>
    </head>
    <body>
       <jsp:include page="navbar.jsp"/>
       <%
            if (session.getAttribute("account") == null) {
                out.print("<h1 class=\"title-session\">Login to use</h1>");
                return;
            }
        %>
        <div>
            <%
            Account acc = (Account) session.getAttribute("account");
            AccountController accCtrl = new AccountController();
            MessageController messCtrl = new MessageController();
            List<Message> list = messCtrl.getMessage(acc.getAccID());
            for(Message ms : list)
            {
                Account accc = accCtrl.getAccountByID(ms.getId_Send());
                out.println("<div class=\"search-result-mess\">");
                    out.println("<div class=\"row item-search-mess\">");
                    out.println("<div class=\"col-md-1\">");
                    out.println("<img src=\"./resources/img/" + accc.getImage() + "\" alt=\"User Profile\" class=\"profile-pic\">");
                    out.println("</div>");
                    out.println("<div class=\"col-md-8\">");
                    out.println("<h4><a href=\"profile.jsp?accID="+accc.getAccID()+"\">" + accc.getUsername() + "</a></h4>");
                    out.println("</div>");
                    out.println("<div class=\"button-container\">");
                    out.print("<form class=\"container\" action=\"AddFriendServlet\" method=\"post\">");
                    out.print("<input type=\"hidden\" name=\"id_acc\" value=\""+ms.getId_Receive()+"\"/>");
                    out.print("<input type=\"hidden\" name=\"id_friend\" value=\""+ms.getId_Send()+"\"/>");
                    out.print("<input type=\"hidden\" name=\"id\" value=\""+ms.getId()+"\"/>");
                    out.println("<button id=\"follow-btn-"+acc.getAccID()+"\" class=\"btn btn-outline-success btn-search\" onclick=\"beFriend(" + acc.getAccID() + "," + acc.getAccID()+ ")\">Accept</button>");
                    out.print("</form>");
                    
                    out.print("<form class=\"container\" action=\"RemoveMessageServlet\" method=\"post\">");
                    out.print("<input type=\"hidden\" name=\"id\" value=\""+ms.getId()+"\"/>");
                    out.println("<button id=\"follow-btn-"+acc.getAccID()+"\" class=\"btn btn-outline-success btn-search\" onclick=\"beFriend(" + acc.getAccID() + "," + acc.getAccID() + ")\">Refuse</button>");
                    out.print("</form>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
            }
            %>
        </div>
    </body>
</html>
