<%@ page import="org.cba.domain.Carport" %>
<%@ page import="org.cba.domain.finder.CarportFinder" %>
<%@ page import="org.cba.DynamicMaterialsCalulator" %>
<% int price = 0;
   int inputLength = 0;
   int inputWidth = 0; %>
<div class="panel panel-default">
    <div class="panel-heading">Carport <strong>${carport.name}</strong></div>
    <div class="panel-body">
        *Insert cool SVG/OpenGL render here*
        <hr>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" form="width">Width (in cm):</label>
                <div class="col-sm-10">
                    <input type="width" class="form-control" id="width" name = "width" placeholder="${carport.defaultWidth} centimeters">
                </div><hr>
                    <label class="control-label col-sm-2" form="length">Length (in cm): </label>
                    <div class="col-sm-10">
                        <input type="length" class="form-control" id="length" name = "length" placeholder="${carport.defaultLength} centimeters">
            </div>
                </div>
        </form>
        <hr>



        <div class="alert alert-info">
            <strong>Price: </strong> <p id = "price">${carport.defaultPrice} DKK.</p> <strong></strong>
            <hr>
            <button type="button" class="btn btn-success">Confirm</button>
            <button onclick = "calculatePrice()" type="button" class="btn btn-info">Calculate Price</button>
            <button type="button" class="btn btn-danger">Discard</button>
        </div>
    </div>
</div>

<script>
    function calculatePrice()
    {
        document.getElementById('width').value = <%= inputWidth %>;
        document.getElementById('length').value = <%= inputLength %>;
        document.getElementById("price").innerHTML = "<%= price %> DKK.";
    }
</script>