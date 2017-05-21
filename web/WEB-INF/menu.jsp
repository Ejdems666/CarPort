<%@ page import="org.cba.domain.User" %>
<% User user = ((User) session.getAttribute("user")); %>
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
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            Assembly Material<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${root}assembly-material">List</a></li>
                            <li><a href="${root}assembly-material/add">Add</a></li>
                        </ul>
                    </li>
                    <li><% if (request.getAttribute("alerts") != null) {%>
                        ${alerts}
                        <% } %></li>
                    <% }%>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <% if (user != null) {%>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            <%=user.getName()%> <%=user.getSurname()%><span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${root}profile/email">Change email</a></li>
                            <li><a href="${root}profile/password">Change password</a></li>
                            <li><a href="${root}sign/out">Logout</a></li>
                        </ul>
                    </li>
                    <% }%>
                </ul>
            </div>
        </div>
    </div>
</nav>