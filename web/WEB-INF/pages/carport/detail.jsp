<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.StaticMaterial" %>
<%@ page import="org.cba.domain.Picture" %>
${carport.name}

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="${assets}slider.js"></script>

<div class="w3-content w3-display-container">

    <% for (Picture picture : ((Carport) request.getAttribute("carport")).getPictures()) { %>
    <img class="mySlides" style="display:<% if (picture.isThumbnail()) { %>block<%} else {%>none<%}%>"
         src="${assets}<%=picture.getUrl()%>">
    <% } %>

        <div class="w3-center w3-container w3-section w3-large w3-text-white w3-display-bottommiddle" style="width:100%">
            <div class="w3-left w3-hover-text-khaki" onclick="plusDivs(-1)">&#10094;</div>
            <div class="w3-right w3-hover-text-khaki" onclick="plusDivs(1)">&#10095;</div>
    <% for (int i = 1; i <= ((Carport) request.getAttribute("carport")).getPictures().size(); i++) { %>
         <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(<%=i%>)"></span>
   <% } %>
        </div>



</div>