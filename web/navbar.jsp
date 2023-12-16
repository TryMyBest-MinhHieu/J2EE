<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./resources/css/navbar.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar position-sticky w-100 navbar-expand-lg bg-body-tertiary" data-bs-theme="red">
            <div class="container-fluid ">
                
                <div class="d-flex">
                    <a class="navbar-brand" href="newfeed.jsp"><img src="./resources/img/letter-s.png"></a>               
                    <div id="formSearch" class="d-flex navbar-nav me-auto mb-2 mb-lg-0" role="search">
                        <input class="form-control me-2 search" id="searchInput" type="search" placeholder="Looking for someone..." aria-label="Search">
                        <button class="btn btn-outline-success btn-search" onclick="searchUser()"><img src="./resources/img/search.png"></button>
                        <div class="search-results search-resultstop" id="searchResults">
                            <ul></ul>
                        </div>
                    </div>
                </div> 
                    
                <% Account acc = (Account) session.getAttribute("account");%>
                
                    <div style="display: flex;align-items: center;justify-content: center;margin-right: 280px;">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0" style=" width: 400px; display: flex; justify-content: space-evenly; align-items: center;">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="newfeed.jsp"><img src="./resources/img/home.png"></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="addpost.jsp"><img src="./resources/img/post.png"></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"  href="profile.jsp?accID=<%out.print(acc.getAccID());%>"  ><img src="./resources/img/profile.png"></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="editprofile.jsp"><img src="./resources/img/settings.png"></a>
                            </li>
                        </ul>
                    </div>

                                    
                    <div class="row text-center">
                        <div class="col-md-4">
                            <%
                               // Account acc = (Account) session.getAttribute("account");
                                if (acc == null) {
                                    out.print("<div class=\"col-md-8\"><a class=\"btn btn-logout\" href=\"index.jsp\">Login</a></div>");
                                } else {
                                    out.println("<img src=\"./resources/img/" + acc.getImage() + "\" alt=\"User Profile\" class=\"profile-pic\">");
                                    out.print("</div>");
                                    out.print("<div class=\"col-md-8\"><form method=\"post\" action=\"LogOutSevlet\"><button class=\"btn btn-logout\" type=\"submit\">Log out</button></form></div>");
                                }
                            %>
                        </div>
                    </div>         
            </div>
        </nav>
       

        <script>
            var isOn = false;
            var searchResults = document.getElementById("searchResults");
            searchResults.style.display = "none";
            document.getElementById("searchInput").addEventListener("keyup", function (event) {
                if (event.key === "Enter") {
                    var inputData = document.getElementById("searchInput").value;
                    window.location.href = "FriendServlet?data=" + inputData;
                }
            });

            function searchUser() {
                var searchValue = document.getElementById("searchInput").value;
                $.ajax({
                    type: "POST",
                    url: "SearchServlet",
                    data: {searchValue: searchValue},
                    success: function (data) {
                        displayResults(data);
                    }
                });
            }

            function displayResults(data) {
                var searchResults = document.getElementById("searchResults");
                if (!isOn) {
                    searchResults.style.display = "block";
                    isOn = !isOn;

                } else {
                    searchResults.style.display = "none";
                    isOn = !isOn;
                }

                var ul = searchResults.querySelector("ul");
                ul.innerHTML = "";
                data.forEach(function (user) {
                    var li = document.createElement("li");
                    var liElement = document.querySelector("li");
                    var img = document.createElement("img");
                    var a = document.createElement("a");
                    //li.innerText = user.username;
                    img.setAttribute("class", "profile-pic");
                    img.setAttribute("src", "./resources/img/" + user.image);
                    a.setAttribute("href", "profile.jsp?accID=" + user.accID);
                    a.innerText = user.username;
                    li.appendChild(img);
                    li.appendChild(a);
                    ul.appendChild(li);
                });
            }
            
//            function redirectToProfile() {
//                var loggedInUserID = '<%= session.getAttribute("id") %>'; // Lấy ID của tài khoản đã đăng nhập từ session
//                var profileURL = 'profile.jsp?accID=' + loggedInUserID; // Tạo URL với tham số accID
//                window.location.href = profileURL;
//            }
        </script>
    </body>
</html>
