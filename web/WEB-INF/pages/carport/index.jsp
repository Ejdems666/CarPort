<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.DefaultMaterial" %>
${carport.name}
<br>
Materials:
<table>
    <tr>
        <th>Name</th>
        <th>Amount</th>
        <th>Price</th>
        <th>Dinamic 0/1</th>
    </tr>
    <% for (DefaultMaterial defaultMaterial : ((Carport) request.getAttribute("carport")).getDefaultMaterials()) { %>
    <tr>
        <td><%= defaultMaterial.getMaterial().getName() %></td>
        <td><%= defaultMaterial.getAmount() %></td>
        <td><%= defaultMaterial.getMaterial().getPrice() %></td>
        <td><%= defaultMaterial.getDynamic() %></td>

    </tr>
    <% } %>
</table>
