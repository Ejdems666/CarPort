<div class="panel panel-default">
    <div class="panel-heading">Carport <strong>${carport.name}</strong></div>
    <div class="panel-body">
        *Insert cool SVG/OpenGL render here*
        <hr>
        <form class="form-horizontal" id="dimensions-form">
            <div class="form-group">
                <label class="control-label col-sm-2" form="width">Width (in cm):</label>
                <div class="col-sm-10">
                    <input type="number" class="dimension form-control" id="width" name="width" value="${carport.defaultWidth}">
                </div>
                <hr>
                <label class="control-label col-sm-2" form="length">Length (in cm): </label>
                <div class="col-sm-10">
                    <input type="number" class="dimension form-control" id="length" name="length"
                           value="${carport.defaultLength}">

                    <hr>
                    <strong>Price: </strong>
                    <p id="price">${carport.defaultPrice} DKK.</p> <strong></strong>
                    <hr>
                    <button type="button" class="btn btn-success">Confirm</button>
                    <button type="button" class="btn btn-danger">Discard</button>
                </div>

            </div>

        </form>
        <hr>

    </div>
</div>

<script>
    $( document ).ready(function() {
        $('.dimension').on('keyup',calculatePrice);
    });
    function calculatePrice() {
        var width = document.getElementById('width').value;
        var length = document.getElementById('length').value;
        $.ajax({
            url: "${root}api/carport/get-price/${carport.id}",
            data: {
                "width": width,
                "length": length
            }
        })
            .done(function (data) {
                if(data.price != null) {
                    $('#price').text(data.price + " DKK")
                }
            });
    }
</script>