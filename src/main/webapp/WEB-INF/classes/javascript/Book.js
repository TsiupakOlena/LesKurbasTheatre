function Book() {
    var sessionID = $("#sessionID").attr("value");
    var placesArray = [];
    $(".chosen-place").each(function(i, obj) {
        var place = {
            row: obj.getAttribute("data-row"),
            seat: obj.getAttribute("data-place")
        };
        placesArray.push(place);
    });

    $.post('/leskurbas/book', {
            "bookedPlaces": JSON.stringify(placesArray),
            "sessionID": sessionID
        },

        function(){
            window.location.href = "/leskurbas/mypage";
        }).fail(function(){
        window.location.href = "/leskurbas/book?session=" + sessionID;
    });

}