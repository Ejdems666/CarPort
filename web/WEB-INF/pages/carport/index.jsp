<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.StaticMaterial" %>
${carport.name}
<br>
Static Materials:
<table>
    <tr>
        <th>Name</th>
        <th>Amount</th>
        <th>Price</th>
    </tr>
    <% for (StaticMaterial staticMaterial : ((Carport) request.getAttribute("carport")).getStaticMaterials()) { %>
    <tr>
        <td><%= staticMaterial.getMaterial().getName() %></td>
        <td><%= staticMaterial.getAmount() %></td>
        <td><%= staticMaterial.getMaterial().getPrice() %></td>

    </tr>
    <% } %>
</table>
