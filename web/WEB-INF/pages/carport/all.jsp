<%@ page import="org.cba.domain.Carport" %>
<%@ page import="java.util.List" %>
<% for (Carport carport : ((List<Carport>) request.getAttribute("carports"))) {%>

<%--<%=%> print somethink from java--%>
<html>

<div class="container">
    <div class="row box">
        <div class="col-md-4 col-sm-2 col-xs-2 box ">First box </div>
        <aside>
            <img src="" alt ="first pic"class="img-circle">
            <h4>NAME</h4>
            <p>description</p>
            <p>price</p>
            <hr>
            <a href="#" class="btn btn-primary">Add to basket</a>
        </aside>
        <div class="col-md-4 col-sm-2 col-xs-2 box">Second box</div>
        <h4>NAME</h4>
        <p>description</p>
        <p>price</p>
        <hr>
        <a href="#" class="btn btn-primary">Add to basket</a>
        <div class="col-md-4 col-sm-2 col-xs-2 box">Third box</div>
        <h4>NAME</h4>
        <p>description</p>
        <p>price</p>
        <hr>
        <a href="#" class="btn btn-primary">Add to basket</a>
        </div>

    </div>

</div>



</html>

<%} %>