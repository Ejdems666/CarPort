<%@ page import="org.cba.model.cart.TemplateCart" %>
<%@ page import="static org.cba.Path.ROOT" %>
<% TemplateCart cart = ((TemplateCart) request.getAttribute("cart")); %>
<% if (request.getAttribute("noOrders") == null) { %>
${table}
<hr>
<p>
    Final price for all <%= cart.getNumberOfItems() %> items: <strong><%= cart.getPrice() %></strong> DKK
</p>
<hr>
<a href="<%=ROOT%>cart/buy" class="btn btn-success">Confirm and buy</a>
<% } else { %>
${noOrders}
<% } %>

