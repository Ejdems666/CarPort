<%@ page import="static org.cba.Path.ROOT" %>
<form action="<%=ROOT%>assembly-material/add" method="POST">
    <div class="input-group">
        <label for="name">Name</label>
        <input type="text" required id="name" name="name" class="form-control">
    </div>

    <div class="input-group">
        <label for="price">Price</label>
        <input type="number" required id="price" name="price" class="form-control">
    </div>

    <div class="input-group">
        <label for="stock">Amount on stock</label>
        <input type="number" required id="stock" name="stock" class="form-control">
    </div>

    <div class="input-group">
        <label for="desc">Description</label>
        <textarea required id="desc" name="description" class="form-control"></textarea>
    </div>

    <button>Add</button>
</form>
