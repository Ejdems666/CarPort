<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.StaticMaterial" %>
<%@ page import="org.cba.domain.Picture" %>
${carport.name}

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="${assets}slider.js"></script>

<div class="container">
    <div class="span8">

        <h1>Carport selection</h1>


        <div class="well">

            <div id="myCarousel" class="carousel slide">

                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>



                <% for (Picture picture : ((Carport) request.getAttribute("carport")).getPictures()) { %>
                <div class="carousel-inner">
                    <div class="span3"><a href="#x" class="thumbnail">
            <img class="mySlides" style="display: <% if (picture.isThumbnail()) { %>block<%} else {%>none<%}%>"
                 src="${assets}<%=picture.getUrl()%>">
                <% } %>
                    </div>
                </div>
                    <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                    <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>


        </div>
    </div>
</div>
</div>
<h2> Text</h2>
<p>fsafdasdfadsfsaafds</p>