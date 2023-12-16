<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="./resources/css/index.css" rel="stylesheet">
        <title>Login</title>
    </head>

<body>
    <form class="container text-center" action="LoginServlet" method="post">
        <div class="title">Social Network</div>
        <div class="row justify-content-center">
            <div class="col-12"><input type="email" id="email" name="email" class="form-control" placeholder="Email address"><small id="email-error" class="text-danger"></small></div>
        </div>
        <div class="row justify-content-center">
            <div class="col-12"><input type="password" id="password" name="password" class="form-control" placeholder="Password"><small id="password-error" class="text-danger"></small></div>
        </div>
        <div class="row">
            <div class="col-12"><h3>${message}</h3></div>
        </div>
        <div class="row">
            <div class="col-12"><button type="submit" class="btn btn-primary mb-3 btn-login" >Login</button></div>            
        </div>
    </form>
    <div class="row justify-content-center text-center">
        <hr class="separator">
        <div class="col-12"><label class="form-label-1">Don't have an account? <a href="signup.jsp">Sign up</a></label></div>
    </div>
    <script src="./validate/login.js"></script>
</body>
</html>


