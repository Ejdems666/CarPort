<%@ page import="static org.cba.Path.ROOT" %>
<div class="panel panel-default">
    <div class="panel-heading">Editing order n. ${purchaseNumber} | <strong>${carport.name}</strong></div>
    <div class="panel-body">
        <form id="dimensions-form" method="POST" action="<%=ROOT%>cart/edit-confirm/${purchaseNumber}">
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
            <button type="submit" class="btn btn-success">Confirm</button>
            <button type="reset" class="btn btn-danger">Discard</button>
        </form>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#dimensions-form').find('select').on('change', calculatePriceCall(${carport.id}));
    });
</script>
