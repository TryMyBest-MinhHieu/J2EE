document.querySelector("form").addEventListener("submit", validateLogin);

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

function checkLenght(input) {

}

function validateLogin(event) {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var emailError = document.getElementById("email-error");
    var passwordError = document.getElementById("password-error");

    checkEmpty(email, event, emailError, "Email is required");
    checkEmpty(password, event, passwordError, "Password is required");
    checkEmail(email, event, emailError, "Invalid email address");
    return true;
}