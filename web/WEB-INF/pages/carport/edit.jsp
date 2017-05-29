<%@ page import="static org.cba.Path.ROOT" %>
<div class="panel panel-default">
    <div class="panel-heading">Carport <strong>${carport.name}</strong></div>
    <div class="panel-body">
        <form id="frameDimensions-form" method="POST" action="<%=ROOT%>cart/add/${carport.id}">
            <div class="form-group">
                <%= request.getAttribute("lengthSelect") %>
            </div>
            <div class="form-group">
                <%= request.getAttribute("widthSelect") %>
            </div>
            <hr>
            <div class="form-group">
                <label for="withShed">With shed</label>
                <input name="withShed" id="withShed" class="form-control"
                       type="checkbox" <% if (((boolean)request.getAttribute("withShed"))) { %> checked <% } %> value="1">
            </div>
            <div class="form-group">
                <label for="shedLength">Shed Length</label>
                <input name="shedLength" value="${shedLength}" id="shedLength" class="form-control" type="number">
            </div>
            <hr>
            <strong>Price: </strong>
            <p id="price">${carport.defaultPrice} DKK.</p> <strong></strong>
            <hr>
            <button type="submit" class="btn btn-success">Confirm</button>
            <button type="reset" class="btn btn-danger">Discard</button>
        </form>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#frameDimensions-form :input').on('change', calculatePriceCall(${carport.id}));
    });
</script>