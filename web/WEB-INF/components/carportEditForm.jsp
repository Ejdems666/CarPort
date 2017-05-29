<div class="form-group">
    <%= request.getAttribute("lengthSelect") %>
</div>
<div class="form-group">
    <%= request.getAttribute("widthSelect") %>
</div>
<hr>
<div class="form-group">
    <label for="withShed">With shed</label>
    <input name="withShed" id="withShed"
           type="checkbox" <% if (((boolean)request.getAttribute("withShed"))) { %> checked <% } %> value="1">
    <hr>
</div>
<div class="form-group">
    <b>Shed Length</b>
    <input name="shedLength" id="shedLength" class="form-control" type="text"
           data-slider-id='shedLengthSlider' data-slider-min="100" data-slider-value="${shedLength}" data-slider-step="10">
</div>
<hr>
<strong>Price: </strong>
<p id="price">${carport.defaultPrice} DKK.</p> <strong></strong>
<hr>
<button type="submit" class="btn btn-success">Confirm</button>
<button type="reset" class="btn btn-danger">Discard</button>

<script>
    $(document).ready(function () {
        $('#frameDimensions-form').find(':input').on('change', calculatePriceCall(${carport.id}));

        $('#shedLength').slider({
            tooltip: 'always'
        });

        switchSlider($("#withShed").is(':checked'));
        $("#withShed").on("click", function () {
            switchSlider(this.checked);
        });

        changeSliderMax($("#frameLength").val());
        $("#frameLength").on("change", function () {
            changeSliderMax(this.value);
        })
    });

    function switchSlider(checked) {
        if (checked) {
            $('#shedLength').slider("enable");
        }
        else {
            $('#shedLength').slider("disable");
        }
    }

    function changeSliderMax(frameLength) {
        frameLength = parseInt(frameLength);
        $('#shedLength').slider({
            max: frameLength - 60
        });
    }
</script>
