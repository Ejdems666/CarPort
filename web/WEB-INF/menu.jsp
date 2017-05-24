<%@ page import="org.cba.domain.User" %>
<%@ page import="org.cba.model.cart.TemplateCart" %>
<% User loggedUser = ((User) session.getAttribute("loggedUser")); %>
<% TemplateCart cart = ((TemplateCart) request.getAttribute("cart")); %>
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
                    <li>
                        <a href="${root}carport/all">Store</a>
                    </li>
                    <% if (loggedUser == null) {%>
                    <li>
                        <a href="${root}sign/up">Register</a>
                    </li>
                    <li>
                        <a href="${root}sign/in">Login</a>
                    </li>
                    <% } else {%>
                    <li>
                        <a href="${root}sign/out">Logout <%=loggedUser.getName()%> <%=loggedUser.getSurname()%>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                           aria-expanded="false">
                            Assembly Material<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${root}assembly-material">List</a></li>
                            <li><a href="${root}assembly-material/add">Add</a></li>
                        </ul>
                    </li>
                    <% }%>
                    <li>
                        <% if (request.getAttribute("alerts") != null) {%>
                        ${alerts}
                        <% } %>
                    </li>

                </ul>
                <% if (cart.getNumberOfItems() > 0) {%>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${root}cart">
                            <%= cart.getNumberOfItems() %>
                            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                            <%= cart.getPrice() %> DKK
                        </a>
                    </li>
                </ul>
                <% } %>
            </div>
        </div>
    </div>
</nav>