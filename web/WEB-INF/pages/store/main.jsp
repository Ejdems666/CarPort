<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.Picture" %>
<%@ page import="static org.cba.Path.ROOT" %>
<%@ page import="static org.cba.Path.CP_IMG" %>
<% Carport carport = (Carport) request.getAttribute("carport"); %>
<h1>Carport ${carport.name}</h1>
<script>
    $(function(){
        $("#slides").slidesjs();
    });
</script>
<div id="slides" class="col-lg-8">
    <% for (Picture picture : carport.getPictures()) { %>
    <img src="<%=CP_IMG%><%=picture.getUrl()%>">
    <% } %>
</div>
<div class="col-lg-4">
    <a href="#" class="btn btn-info" role="button">Add to cart</a>
    <a href="<%=ROOT%>carport/edit/${carport.id}" class="btn btn-info" role="button">Edit frameDimensions first</a>
</div>
<div class="col-sm-12">
    <p>${carport.description}</p>
</div>
