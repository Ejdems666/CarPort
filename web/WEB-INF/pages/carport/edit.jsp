<%@ page import="static org.cba.Path.ROOT" %>
<div class="panel panel-default">
    <div class="panel-heading">Carport <strong>${carport.name}</strong></div>
    <div class="panel-body">
        <form id="frameDimensions-form" method="POST" action="<%=ROOT%>cart/add/${carport.id}">
            <jsp:include page="../../components/carportEditForm.jsp" />
        </form>
    </div>
</div>