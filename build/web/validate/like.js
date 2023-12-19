function likePost(accID, postID) {
    var button = document.getElementById("like-post-btn-" + postID);
    var text = document.getElementById("like-post-" + postID);

    var buttonText = button.textContent;
    var textLike = text.textContent;

    var parts = textLike.split(" ");
    var numberOfLike = Number(parts[0]);

    if (buttonText === "Like") {
        $.ajax({
            type: "POST",
            url: "LikePostServlet",
            data: { accID: accID, postID: postID },
            success: function (data) {
                button.textContent = "Unlike";
                text.textContent = (numberOfLike + 1) + " Like";
            }
        });
    } else if (buttonText === "Unlike") {
        $.ajax({
            type: "POST",
            url: "UnLikePostServlet",
            data: { accID: accID, postID: postID },
            success: function (data) {
                button.textContent = "Like";
                text.textContent = (numberOfLike - 1) + " Like";
            }
        });
    }

}