<%@ page import="org.cba.domain.Carport" %>
<%@ page import="java.util.List" %>
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
                    <a href="${root}carport/<%=carport.getId() %>"><img src="${assets}<%=carport.getThumbnail().getUrl()%>" alt="<%=carport.getName() %>"
                                                                        class="img-responsive"></a>
                    <p>Lenght: <%=carport.getDefaultLength() %>
                    </p>
                    <p>Width: <%=carport.getDefaultWidth() %>
                    </p>
                    <p>Price: <%=carport.getDefaultPrice() %>
                    </p>
                    <hr>
                    <a href="${root}carport/<%=carport.getId() %>" class="btn btn-primary">Go to detail</a>
                    <a href="#" class="btn btn-primary">Add to basket</a>
                    <a href="${root}carport/edit/<%=carport.getId() %>" class="btn btn-primary">Or edit first</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>
