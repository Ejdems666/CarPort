<div class="panel panel-default">
    <div class="panel-heading">Carport <strong>${carport.name}</strong></div>
    <div class="panel-body">
        *Insert cool SVG/OpenGL render here*
        <hr>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" form="width">Width (in cm):</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" id="width" name="width" value="${carport.defaultWidth}">
                </div>
                <hr>
                <label class="control-label col-sm-2" form="length">Length (in cm): </label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" id="length" name="length"
                           value="${carport.defaultLength}">
                </div>
            </div>
        </form>
        <hr>


        <div class="alert alert-info">
            <strong>Price: </strong>
            <p id="price">${carport.defaultPrice} DKK.</p> <strong></strong>
            <hr>
            <button type="button" class="btn btn-success">Confirm</button>
            <button onclick="calculatePrice()" type="button" class="btn btn-info">Calculate Price</button>
            <button type="button" class="btn btn-danger">Discard</button>
        </div>
    </div>
</div>

<script>
    function calculatePrice() {
        var width = document.getElementById('width').value;
        var length = document.getElementById('length').value;
    }
</script>