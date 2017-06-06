<form action="${root}material/edit/${material.id}" method="POST">
    <div class="input-group">
        <label for="name">Name</label>
        <input type="text" required id="name" name="name" value="${material.name}" class="form-control">
    </div>

    <div class="input-group">
        <label for="height">Price</label>
        <input type="number" required id="height" name="height" value="${material.price}" class="form-control">
    </div>

    <div class="input-group">
        <label for="weight">Amount on stock</label>
        <input type="number" required id="weight" name="wight" value="${material.stock}" class="form-control">
    </div>

    <div class="input-group">
        <label for="desc">Description</label>
        <textarea required id="desc" name="description" class="form-control">${material.description}</textarea>
    </div>

    <button>Add</button>
</form>