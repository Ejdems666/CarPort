<head>
    <title>Carport Editing</title>
</head>
<div class="panel panel-default">
    <div class="panel-heading">Carport <strong>${carport.name}</strong></div>
    <div class="panel-body">
        <form id="dimensions-form">
            <div class="form-group">
                <%= request.getAttribute("lengthSelect") %>
            </div>
            <hr>
            <div class="form-group">
                <%= request.getAttribute("widthSelect") %>
            </div>
            <hr>
            <strong>Price: </strong>
            <p id="price">${carport.defaultPrice} DKK.</p> <strong></strong>
            <hr>
            <button type="button" class="btn btn-success">Confirm</button>
            <button type="button" class="btn btn-danger">Discard</button>
        </form>
        <hr>

    </div>
</div>

<script>
    $(document).ready(function () {
        $('#dimensions-form').find('select').on('change', calculatePrice);
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
                    $('#price').text(data.price + " DKK");
                } else {
                    $('#price').text("error");
                }
            });
    }
</script>