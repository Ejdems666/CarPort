<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.Picture" %>
<head>
    <title>Carport Details</title>
</head>

<link rel="stylesheet" href="${assets}ideal-image-slider.css">

<% Carport carport = (Carport) request.getAttribute("carport"); %>
<h1>Carport <br><strong class = "text-uppercase">${carport.name}</strong></h1>

<div id="slider" class="ol-xs-6 col-sm-8 col-lg-10 slider">

       <% for (Picture picture : carport.getPictures()) { %>
        <img src="<%=picture.getUrl()%>">
    <% } %>
</div>
<div class="col-xs-6 col-sm-4 col-lg-2">
    <a href="#" class="btn btn-info customButton" role="button">Add to cart</a>
    <a href="${root}carport/edit/${carport.id}" class="btn btn-info customButton" role="button">Edit dimensions first</a>
<div class="alert alert-info col-xs-5 col-sm-6 col-lg-4 descriptionBox">
<strong>Width: </strong>${carport.defaultWidth} <strong>cm.</strong><br>
<strong>Height: </strong>${carport.defaultLength} <strong>cm.</strong><br>
<strong>Price: </strong>${carport.defaultPrice} <strong>DKK.</strong><br><hr>
<strong>Description:</strong><p>${carport.description}</p>
</div></div>

<script src="${assets}ideal-image-slider.js"></script>
<script>
    var slider = new IdealImageSlider.Slider({
        selector: '#slider',
        disableNav: false
    });
    slider.start();
</script>
