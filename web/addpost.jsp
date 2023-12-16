<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="./resources/css/addpost.css" rel="stylesheet">
        <title>Add Post</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <%
            if (session.getAttribute("account") == null) {
                out.print("<h1 class=\"title-session\">Login to use</h1>");
                return;
            }
        %>
        <form class="container" action="PostServlet" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-1">
                    <%
                        Account acc = (Account) session.getAttribute("account");
                        out.println("<img src=\"./resources/img/" + acc.getImage() + "\" alt=\"User Profile\" class=\"profile-pic\">");
                        //<img src="./resources/img/user.png" alt="User Profile" class="profile-pic">
                    %>

                </div>
                <div class="col-md-11">
                    <%
                        out.println("<h4>" + acc.getUsername() + "</h4>");
                        //<h4>User Name</h4>
%>

                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="post-container">
                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label">What are you thinking?</label>
                            <textarea class="form-control" name="caption" rows="3" placeholder="Write some caption..."></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label">Upload your image</label>
                            <img src="./resources/img/user.png" id="imageDisplay" alt="Posted Image" class="img-fluid">
                            </br><input class="form-control" id="imageInput" name="image" type="file">
                        </div>
                    </div>
                </div>
            </div>
            <div class = "row">
                <div class="col"><h3>${message}</h3></br></div>
            </div>
            <div class="row">
                <div class="col"><button type="submit" class="btn btn-primary mb-3">Post</button></div>
            </div>
        </form>

        <script>
            // JavaScript code to display the selected image
            const imageInput = document.getElementById("imageInput");
            const imageDisplay = document.getElementById("imageDisplay");

            imageInput.addEventListener("change", function () {
                if (imageInput.files && imageInput.files[0]) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        imageDisplay.src = e.target.result;
                    };
                    reader.readAsDataURL(imageInput.files[0]);
                }
            });
        </script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </body>
</html>
