<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.Picture" %>
<%@ page import="static org.cba.Path.CP_IMG" %>
<%@ page import="static org.cba.Path.ROOT" %>
<% Carport carport = (Carport) request.getAttribute("carport"); %>
<h1>Carport <br><strong class="text-uppercase">${carport.name}</strong></h1>

<div id="slider" class="ol-xs-6 col-sm-8 col-lg-10 slider">

    <% for (Picture picture : carport.getPictures()) { %>
    <img src="<%=CP_IMG%><%=picture.getUrl()%>">
    <% } %>
</div>
<div class="col-xs-6 col-sm-4 col-lg-2">
    <a href="#" class="btn btn-info customButton" role="button">Add to cart</a>
    <a href="<%=ROOT%>carport/edit/${carport.id}" class="btn btn-info customButton" role="button">Edit frameDimensions
        first</a>
    <div class="alert alert-info col-xs-5 col-sm-6 col-lg-4 descriptionBox">
        <strong>Width: </strong>${carport.frameWidth} <strong>cm.</strong><br>
        <strong>Height: </strong>${carport.frameLength} <strong>cm.</strong><br>
        <strong>Price: </strong>${carport.defaultPrice} <strong>DKK.</strong><br>
        <hr>
        <strong>Description:</strong>
        <p>${carport.description}</p>
    </div>
</div>
<script>
    var slider = new IdealImageSlider.Slider({
        selector: '#slider',
        disableNav: false
    });
    slider.start();
</script>
