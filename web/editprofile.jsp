<%@page import="model.User"%>
<%@page import="controller.UserController"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="./resources/css/editprofile.css" rel="stylesheet">
        <title>Edit Profile</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <%
            if (session.getAttribute("account") == null) {
                out.print("<h1 class=\"title-session\">Login to use</h1>");
                return;
            }
        %>
        <form class="container text-center margin-header" method="post" action="EditProfileServlet" enctype="multipart/form-data">
            <div class="cycle">
                <div class="avatar">
                <%
                    UserController userCtrl = new UserController();
                    Account acc = (Account) session.getAttribute("account");
                    User user = userCtrl.getUser(acc.getUserID());
                    out.print("<img src=\"./resources/img/" + acc.getImage() + "\" id=\"imageDisplay\" class=\"rounded\" alt=\"avatar\" width=\"200\" height=\"200\">");
                    //<img src="./resources/img/" id="imageDisplay" class="rounded" alt="avatar" width="200" height="200">
                %>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    </br><input class="form-control" id="imageInput" name="image" type="file" value="" ></br>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><% out.print("<input id=\"fullname\" type=\"text\" name=\"fullname\" class=\"form-control\" placeholder=\"Enter your full name\" value = \"" + user.getName() + "\">"); %></br><small id="fullname-error" class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><% out.print("<input  id=\"email\" type=\"email\" name=\"email\" class=\"form-control\" placeholder=\"name@example.com\" value = \"" + acc.getEmail() + "\">"); %></br><small id="email-error"  class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><% out.print("<input id=\"username\" type=\"text\" name=\"username\" class=\"form-control\" placeholder=\"Enter your user name\" value = \"" + acc.getUsername() + "\">"); %></br><small id="username-error"  class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><% out.print("<input id=\"bio\" type=\"text\" name=\"bio\" class=\"form-control\" placeholder=\"Enter your bio\" value = \"" + acc.getBio() + "\">"); %></br><small id="bio-error"  class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    <select name="sex" class="form-control">
                        <option value="0" <% if (user.getSex().equals("0")) {
                                out.print("selected");
                            } %> >Female</option>
                        <option value="1" <% if (user.getSex().equals("1")) {
                                out.print("selected");
                            } %> >Male</option>
                    </select><br>
                </div>
            </div>
            <div class = "row justify-content-center">
                <div class="col-6">Date of birth</div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><% out.print("<input id=\"dob\" type=\"date\" name=\"dob\" class=\"form-control\" value = \"" + user.getDob() + "\">");%></br><small id="dob-error"  class="text-danger"></small></div>
            </div>
            <!--
            <div class="row justify-content-center">
                <div class="col-2"><label class="form-label">Password</label></div>
                <div class="col-6"><input type="password" name="password" class="form-control" placeholder="Enter your password"></br></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-2"><label class="form-label">Password Again</label></div>
                <div class="col-6"><input type="password" name="cfpassword" class="form-control" placeholder="Enter your password again"></br></div>
            </div>
            -->
            <div class = "row">
                <div class="col"><h3>${message}</h3></br></div>
            </div>
            <div class="row">
                <div class="col"><button type="submit" class="btn btn-primary mb-3 btn-update">Update Profile</button></div>
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
        <script src="./validate/editProfile.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </body>
</html>
