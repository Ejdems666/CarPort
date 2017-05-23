<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 5/21/2017
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<form action="${root}roof-tile/edit/${tile.id}" method="POST">

    <div class="input-group">
        <label for="name">Name</label>
        <input type="text" required id="name" name="name" value="${tile.name}" class="form-control">
    </div>

    <div class="input-group">
        <label for="width">Width</label>
        <input type="number" required id="width" name="width" value="${tile.width}" class="form-control">
    </div>

    <div class="input-group">
        <label for="width overlap">Width Overlap</label>
        <input type="number" required id="width overlap" name="width overlap" value="${tile.widthOverlap}" class="form-control">
    </div>

    <div class="input-group">
        <label for="length">Length</label>
        <input type="number" required id="length" name="length" value="${tile.length}" class="form-control">
    </div>

    <div class="input-group">
        <label for="length overlap">Length</label>
        <input type="number" required id="length overlap" name="length overlap" value="${tile.lengthOverlap}" class="form-control">
    </div>

    <div class="input-group">
        <label for="price">Price</label>
        <input type="number" required id="price" name="price" value="${tile.price}" class="form-control">
    </div>

    <div class="input-group">
        <label for="stock">Amount on stock</label>
        <input type="number" required id="stock" name="stock" value="${tile.stock}" class="form-control">
    </div>

    <div class="input-group">
        <label for="desc">Description</label>
        <textarea required id="desc" name="description" class="form-control">${tile.description}</textarea>
    </div>

    <button>Edit</button>

</form>