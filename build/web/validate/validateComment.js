document.querySelector(".commentform").addEventListener("submit", validateAddPost);

function checkEmpty(input, event, element, message) {
    if (input.trim() === "") {
        element.textContent = message;
        event.preventDefault();
        return false;
    } else {
        element.textContent = "";
    }

}

function validateAddPost(event){
    var caption = document.getElementById("caption").value;
    var captionError = document.getElementById("caption-error");

    checkEmpty(caption, event, captionError, "Write something before posting");

    return true;
}


