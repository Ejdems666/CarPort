<%@ page import="org.cba.model.cart.TemplateCart" %>
<% TemplateCart cart = ((TemplateCart) request.getAttribute("cart")); %>
<% if (request.getAttribute("noOrders") == null) { %>
    ${table}
    <hr>
    <p>
        Final price for all <%= cart.getNumberOfItems() %> items: <%= cart.getPrice() %>
    </p>
<% } else { %>
    ${noOrders}
<% } %>

