<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 5/21/2017
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>

<form action="${root}roof-tiles/add/" method="POST">
    <div class="input-group">
        <label for="name">Name</label>
        <input type="text" required id="name" name="name" class="form-control">
    </div>

    <div class="input-group">
        <label for="width">Price</label>
        <input type="number" required id="width" name="width" class="form-control">
    </div>

    <div class="input-group">
        <label for="width overlap">Price</label>
        <input type="number" required id="width overlap" name="width overlap" class="form-control">
    </div>

    <div class="input-group">
        <label for="length">Price</label>
        <input type="number" required id="length" name="length" class="form-control">
    </div>

    <div class="input-group">
        <label for="length overlap">Price</label>
        <input type="number" required id="length overlap" name="length overlap" class="form-control">
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