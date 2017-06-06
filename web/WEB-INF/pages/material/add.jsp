<form action="${root}material/add" method="POST">
    <div class="input-group">
        <label for="name">Name</label>
        <input type="text" required id="name" name="name" class="form-control">
    </div>

    <div class="input-group">
        <label for="height">Height</label>
        <input type="number" required id="height" name="height" class="form-control">
    </div>

    <div class="input-group">
        <label for="width">Width</label>
        <input type="number" required id="width" name="width" class="form-control">
    </div>

    <div class="input-group">
        <label for="desc">Description</label>
        <textarea required id="desc" name="description" class="form-control"></textarea>
    </div>

    <button>Add</button>
</form>