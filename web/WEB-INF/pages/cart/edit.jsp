<%@ page import="static org.cba.Path.ROOT" %>
<div class="panel panel-default">
    <div class="panel-heading">Editing order n. ${purchaseNumber} | <strong>${carport.name}</strong></div>
    <div class="panel-body">
        <form id="frameDimensions-form" method="POST" action="<%=ROOT%>cart/edit-confirm/${purchaseNumber}">
            <jsp:include page="../../components/carportEditForm.jsp" />
        </form>
    </div>
</div>
