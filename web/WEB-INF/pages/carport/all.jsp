<%@ page import="org.cba.domain.Carport" %>
<%@ page import="java.util.List" %>
<%@ page import="static org.cba.Path.ROOT" %>
<%@ page import="static org.cba.Path.CP_IMG" %>
<head>
    <title>Carport List</title>
</head>
<div class="container">
    <div class="row box">
        <% for (Carport carport : ((List<Carport>) request.getAttribute("carports"))) { %>

        <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading"> Name:  <%=carport.getName() %>
                </div>
                <div class="panel-body">
                    <a href="<%=ROOT%>carport/<%=carport.getId() %>"><img
                            src="<%=CP_IMG + carport.getThumbnail().getUrl()%>" alt="<%=carport.getName() %>"
                            class="img-responsive"></a>
                    <p>Lenght: <%=carport.getFrameLength() %>
                    </p>
                    <p>Width: <%=carport.getFrameWidth() %>
                    </p>
                    <p>Price: <%=carport.getDefaultPrice() %>
                    </p>
                    <hr>
                    <a href="<%=ROOT%>carport/<%=carport.getId() %>" class="btn btn-primary">Go to detail</a>
                    <a href="<%=ROOT%>cart/add/<%=carport.getId() %>" class="btn btn-primary">Add to cart</a>
                    <a href="<%=ROOT%>carport/edit/<%=carport.getId() %>" class="btn btn-primary">Or edit first</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>
