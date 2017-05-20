<%@ page import="org.cba.domain.User" %>
<% User user = ((User) session.getAttribute("user")); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${assets}TemplateData/style.css">
    <script src="${assets}TemplateData/UnityProgress.js"></script>
    <script src="${assets}Build/UnityLoader.js"></script>
    <link rel="stylesheet" href="${assets}style.css">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${assets}bootstrap.min.css">
    <link rel="stylesheet" href="${assets}custom.css">
    <link rel="stylesheet" href="${assets}style.css">
    <link rel="stylesheet" href="${assets}font-awesome/css/font-awesome.min.css">
    <script src="${assets}jquery-3.1.1.min.js"></script>
    <script src="${assets}bootstrap.min.js"></script>
    <link rel="shortcut icon" type="image/png" href="${assets}favicon.ico"/>
    <link rel="shortcut icon" type="image/png" href="${assets}favicon.ico"/>
    <script src="${assets}jquery.slides.min.js"></script>
</head>
<body class="bg">
<nav class="navbar navbar-default navBg">
    <div class="container">
        <div class="navbar-header">
            <button materialType="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${root}"><img src="https://image.flaticon.com/icons/svg/149/149412.svg"
                                                        height="20" width="20"> </a>
        </div>
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${root}">Home</a>
                    </li>
                    <% if (user == null) {%>
                    <li>
                        <a href="${root}sign/up">Register</a>
                    </li>
                    <li>
                        <a href="${root}sign/in">Login</a>
                    </li>
                    <li>
                        <a href="${root}carport/all">Store</a>
                    </li>
                    <% } else {%>
                    <li>
                        <a href="${root}sign/out">Logout <%=user.getName()%> <%=user.getSurname()%>
                        </a>
                    </li>
                    <% }%>
                    <li><% if (request.getAttribute("alerts") != null) {%>
                        ${alerts}
                        <% } %></li>

                </ul>
            </div>
        </div>
    </div>
</nav>


<jsp:include page="/WEB-INF/pages/${template}.jsp"/>

<footer class="navbar navbar-default container-fluid text-center footer">
    <p>Copyright <b>Hygge team</b></p>
</footer>
</body>
</html>