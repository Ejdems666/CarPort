<%@ page import="org.cba.domain.User" %>
<% User user = ((User) session.getAttribute("user")); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${assets}style.css">
    <title>${title}</title>
    <meta charset="utf-8">
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
<body>
<div class="navbar-header">
    <button materialType="button" class="navbar-toggle collapsed" data-toggle="collapse"
            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${root}"><img src="http://lukasjay.me/cc/logo.png" height="20" width="20"> </a>
</div>
<nav class="navbar navbar-default navBg">
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
                    <a href="${root}sign/out">Logout <%=user.getName()%> <%=user.getSurname()%></a>
                </li>
                <% }%>
            </ul>
        </div>
    </div>
</nav>
<% if (request.getAttribute("alerts") != null) {%>
${alerts}
<% } %>
<div class="container">
    <jsp:include page="/WEB-INF/pages/${template}.jsp"/>
</div>
<footer class="navbar navbar-default container-fluid text-center footer">
    <p>Copyright <b>Hygge team</b></p>
</footer>
</body>
</html>
