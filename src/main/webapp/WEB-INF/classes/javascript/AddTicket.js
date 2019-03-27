function AddTicket(clickedPlace) {
    if (!$(clickedPlace).hasClass("reserved")) {
        if($(clickedPlace).hasClass("chosen-place")) {
            $(clickedPlace).removeClass("chosen-place");

            var ticketId = "#" + $(clickedPlace).attr("data-row") +
                $(clickedPlace).attr("data-place");
            $(ticketId).remove();
        } else {
            $(clickedPlace).addClass("chosen-place");
            var row = $(clickedPlace).attr("data-row");
            var place = $(clickedPlace).attr("data-place");
            var price = $(clickedPlace).attr("data-price");
            var ticketId = row + place;

            var divText = "Ticket</br>" + "Row: " + row + " Place: " +
                place + "</br>" + "Price: " + price;

            $( "<div/>", {
                    "id": ticketId,
                    "class": "ticket",
                    html: divText,
                }
            )
                .appendTo( "#tickets-container" );
        }
    }
}