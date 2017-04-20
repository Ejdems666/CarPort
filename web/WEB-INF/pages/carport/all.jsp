<%@ page import="org.cba.domain.Carport" %>
<%@ page import="java.util.List" %>
<div class="row box">
    <% for (Carport carport : ((List<Carport>) request.getAttribute("carports"))) { %>
    <div class="col-md-4 col-sm-6 col-xs-12 box ">
        <img src="" alt="first pic" class="img-circle">
        <%--LOOK HERE THIS IS HOW YOU GET DATA OF A CARPORT--%>
        <h4><%=carport.getName() %></h4>
        <p>description</p>
        <p>price</p>
        <hr>
        <a href="#" class="btn btn-primary">Add to basket</a>
    </div>
    <div class="col-md-4 col-sm-6 col-xs-12 box">
        <img src="" alt="first pic" class="img-circle">
        <h4>NAME</h4>
        <p>description</p>
        <p>price</p>
        <hr>
        <a href="#" class="btn btn-primary">Add to basket</a>
    </div>
    <div class="col-md-4 col-sm-6 col-xs-12 box">
        <img src="" alt="first pic" class="img-circle">
        <h4>NAME</h4>
        <p>description</p>
        <p>price</p>
        <hr>
        <a href="#" class="btn btn-primary">Add to basket</a>
    </div>
    <% } %>
</div>
