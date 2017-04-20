<%@ page import="org.cba.domain.Carport" %>
<%@ page import="java.util.List" %>
<div class="container">
    <div class="row box">
        <% for (Carport carport : ((List<Carport>) request.getAttribute("carports"))) { %>

        <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading"> Name:  <%=carport.getName() %>
                </div>
                <div class="panel-body">
                    <img src="${assets}<%=carport.getPicture().getUrl()%>" alt="<%=carport.getName() %>"
                         class="img-responsive">

                    <p>Lenght: <%=carport.getDefaultLength() %>
                    </p>
                    <p>Width: <%=carport.getDefaultWidth() %>
                    </p>
                    <p>Price: <%=carport.getDefaultPrice() %>
                    </p>
                    <hr>
                    <a href="#" class="btn btn-primary">Add to basket</a>
                    <a href="${root}carport/edit/<%=carport.getId() %>" class="btn btn-primary">Or edit first</a>
                </div>
            </div>
        </div>

        <% } %>
    </div>
</div>
