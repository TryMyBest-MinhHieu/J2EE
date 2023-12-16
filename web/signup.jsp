<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="./resources/css/signup.css" rel="stylesheet">

        <title>Sign Up</title>
    </head>
    <body>
        <form class="container text-center" method="post" action="SignupServlet" enctype="multipart/form-data">
            <div class="cycle">
                <div class="avatar">
                <img src="./resources/img/profile_male.png" id="imageDisplay" class="rounded" alt="avatar">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    </br><input class="form-control" id="imageInput" name="image" type="file"></br>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><input type="text" id="fullname" name="fullname" class="form-control" placeholder="Full name"></br><small id="fullname-error"  class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><input type="email" id="email" name="email" class="form-control" placeholder="Email address"></br><small id="email-error" class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><input type="text" id="username" name="username" class="form-control" placeholder="User Name"></br><small id="username-error" class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><input type="text" id="bio" name="bio" class="form-control" placeholder="Bio"></br><small id="bio-error" class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    <select name="sex" class="form-control">
                        <option value="0">Female</option>
                        <option value="1">Male</option>
                    </select><br>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><input type="password" id="password" name="password" class="form-control" placeholder="Password"></br><small id="password-error" class="text-danger"></small></div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><input type="password" id="cfpassword" name="cfpassword" class="form-control" placeholder="Enter your password again"></br><small id="cfpassword-error" class="text-danger"></small></div>
            </div>
            <div class = "row justify-content-center">
                <div class="col-6">Date of birth</div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6"><input type="date" id="dob" name="dob" class="form-control"></br><small id="dob-error" class="text-danger"></small></div>
            </div>
            <div class = "row">
                <div class="col"><h3>${message}</h3></br></div>
            </div>
            <div class="row">
                <div class="col"><button type="submit" class="btn btn-primary mb-3 btn-login">Sign Up</button></div>
            </div>
        </form>
        <script src="./validate/signUp.js"></script>
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
    </body>
</html>
