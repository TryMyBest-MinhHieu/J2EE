document.querySelector("form").addEventListener("submit", validateSignUp);

function checkEmpty(input, event, element, message) {
    if (input.trim() === "") {
        element.textContent = message;
        event.preventDefault();
        return false;
    } else {
        element.textContent = "";
    }

}

function checkEmail(input, event, element, message) {
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!emailPattern.test(input.trim())) {
        element.textContent = message;
        event.preventDefault();
        return false;
    } else {
        element.textContent = "";
    }
}

function checkPasswordLength(input, event, element, message){
    if(input.trim().length < 6){
        element.textContent = message;
        event.preventDefault();
        return false;
    } else{
        element.textContent = "";
    }
}

function checkMatchPassword(password, cfpassword, event, element, message){
    if(password.trim() === cfpassword.trim()){
        element.textContent = "";
    } else{
        element.textContent = message;
        event.preventDefault();
        return false;
    }
}

function validateSignUp(event) {
    var fullname = document.getElementById("fullname").value;
    var email = document.getElementById("email").value;
    var username = document.getElementById("username").value;
    var bio = document.getElementById("bio").value;
    var dob = document.getElementById("dob").value;
    var password = document.getElementById("password").value;

    var fullnameError = document.getElementById("fullname-error");
    var emailError = document.getElementById("email-error");
    var usernameError = document.getElementById("username-error");
    var bioError = document.getElementById("bio-error");
    var dobError = document.getElementById("dob-error");
    var passwordError = document.getElementById("password-error");
    var cfpasswordError = document.getElementById("cfpassword-error");
    
    checkEmpty(fullname, event, fullnameError, "Full name is required");
    checkEmpty(email, event, emailError, "Email is required");
    checkEmpty(username, event, usernameError, "User name is required");
    checkEmpty(bio, event, bioError, "Bio is required");
    checkEmpty(dob, event, dobError, "DoB is required");
    checkEmpty(password, event, passwordError, "Password is required");

    checkEmail(email, event, emailError, "Invalid email");

    checkPasswordLength(password, event, passwordError, "Password must be at least 6 character");
    checkMatchPassword(password, cfpassword, event, cfpasswordError, "You password doesnt match");

    return true;

}