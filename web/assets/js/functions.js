var calculatePriceCall = function (carportId) {
    return function () {
        $.ajax({
            url: root + "api/carport/get-price/" + carportId,
            data: $(this).closest('form').serialize()
        })
            .done(function (data) {
                if (data.price != null) {
                    $('#price').text(data.price + " DKK");
                } else {
                    $('#price').text("error");
                }
            });
    };
};