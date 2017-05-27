<%@ page import="static org.cba.Path.ROOT" %>
<form action="<%=ROOT%>assembly-material/edit/${material.id}" method="POST">
    <div class="input-group">
        <label for="name">Name</label>
        <input type="text" required id="name" name="name" value="${material.name}" class="form-control">
    </div>

    <div class="input-group">
        <label for="price">Price</label>
        <input type="number" required id="price" name="price" value="${material.price}" class="form-control">
    </div>

    <div class="input-group">
        <label for="stock">Amount on stock</label>
        <input type="number" required id="stock" name="stock" value="${material.stock}" class="form-control">
    </div>

    <div class="input-group">
        <label for="desc">Description</label>
        <textarea required id="desc" name="description" class="form-control">${material.description}</textarea>
    </div>

    <button>Add</button>
</form>