<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Les Kurbas Theatre</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
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
    </script>
    <script type="text/javascript">
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
    </script>
    <style>
        h3 {
            margin: 2px;
        }
        h4 {
            margin: 2%;
        }
        .shadow {
            -webkit-box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
            -moz-box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
            box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
        }
        .notice {
            margin-left: 40%;
            margin-top: 10%;
            margin-bottom: 0%;
            margin-right: 5%;
            font-size: 1.5rem;
        }
        .border {
            border: 1px solid rgba(163,160,163,1);
            padding: 2px;
            margin: 2px;
            border-radius: 8px;
        }
        .data-block {
            padding: 5px;
            margin: 8px;
        }
        a.mainmenu-nav-link {
            display:block;
            background-color:#fd8250;
        }
        #home, #myPage {
            border-right: 2px solid #000000;">
        }

        .navbar-brand,.user-nav {
            color: white;
        }
        .user-nav:hover {
            color: rgba(253, 128, 78, 0.74);
        }

        .page-footer{
            background-color:rgba(253, 128, 78, 0.74);
        }
        .reservation-date{
            padding:2px;
        }

        .user-nav {
            width: 30%;
            max-height: 500px;
        }

        .bookings-list {
            list-style-type: none;
        }

        .bookings-item {
            border-radius: 5px;
            margin-left: 30%;
            margin-top: 2%;
            margin-bottom: 0%;
            margin-right: 2%;
        }

        @media screen and (max-width: 650px) {
            .bookings-item  {
                margin-right:2px;
                margin-left:2px;
                float:left;
                width: 100%;
                -webkit-box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
                -moz-box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
                box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
            }
            .user-nav {
                width: 100%;
                float: none;
            }
        }

        .circle-container-zero {
            position: relative;
            width: 17em;
            height: 0.85em;
            border-radius: 50%;
            padding: 0;
            list-style: none;
            margin-left: 22em;
        }
        .circle-container-zero > * {
            display: block;
            position: absolute;
            top: 0%;
            left: 0%;
            margin: 0%;
            width: 6em;
            height: 6em;
        }
        .circle-container-zero > *:nth-of-type(1) {
            transform: rotate(-6deg) translate(8.5em) rotate(6deg);
        }
        .circle-container-zero > *:nth-of-type(2) {
            transform: rotate(26.14286deg) translate(8.5em) rotate(-26.14286deg);
        }
        .circle-container-zero > *:nth-of-type(3) {
            transform: rotate(58.28571deg) translate(8.5em) rotate(-58.28571deg);
        }
        .circle-container-zero > *:nth-of-type(4) {
            transform: rotate(90.42857deg) translate(8.5em) rotate(-90.42857deg);
        }
        .circle-container-zero > *:nth-of-type(5) {
            transform: rotate(122.57143deg) translate(8.5em) rotate(-122.57143deg);
        }
        .circle-container-zero > *:nth-of-type(6) {
            transform: rotate(154.71429deg) translate(8.5em) rotate(-154.71429deg);
        }
        .circle-container-zero > *:nth-of-type(7) {
            transform: rotate(186.85714deg) translate(8.5em) rotate(-186.85714deg);
        }
        .circle-container-one {
            position: relative;
            width: 26em;
            height: 1.3em;
            border-radius: 50%;
            padding: 0;
            list-style: none;
            margin-left: 22em;
        }
        .circle-container-one > * {
            display: block;
            position: absolute;
            top: 0%;
            left: 0%;
            margin: 0%;
            width: 6em;
            height: 6em;
        }
        .circle-container-one > *:nth-of-type(1) {
            transform: rotate(-11deg) translate(13em) rotate(11deg);
        }
        .circle-container-one > *:nth-of-type(2) {
            transform: rotate(11.5deg) translate(13em) rotate(-11.5deg);
        }
        .circle-container-one > *:nth-of-type(3) {
            transform: rotate(34deg) translate(13em) rotate(-34deg);
        }
        .circle-container-one > *:nth-of-type(4) {
            transform: rotate(56.5deg) translate(13em) rotate(-56.5deg);
        }
        .circle-container-one > *:nth-of-type(5) {
            transform: rotate(79deg) translate(13em) rotate(-79deg);
        }
        .circle-container-one > *:nth-of-type(6) {
            transform: rotate(101.5deg) translate(13em) rotate(-101.5deg);
        }
        .circle-container-one > *:nth-of-type(7) {
            transform: rotate(124deg) translate(13em) rotate(-124deg);
        }
        .circle-container-one > *:nth-of-type(8) {
            transform: rotate(146.5deg) translate(13em) rotate(-146.5deg);
        }
        .circle-container-one > *:nth-of-type(9) {
            transform: rotate(169deg) translate(13em) rotate(-169deg);
        }
        .circle-container-one > *:nth-of-type(10) {
            transform: rotate(191.5deg) translate(13em) rotate(-191.5deg);
        }
        .circle-container-two {
            position: relative;
            width: 35em;
            height: 1.75em;
            border-radius: 50%;
            padding: 0;
            list-style: none;
            margin-left: 22em;
        }
        .circle-container-two > * {
            display: block;
            position: absolute;
            top: 0%;
            left: 0%;
            margin: 0%;
            width: 6em;
            height: 6em;
        }
        .circle-container-two > *:nth-of-type(1) {
            transform: rotate(-14deg) translate(17.5em) rotate(14deg);
        }
        .circle-container-two > *:nth-of-type(2) {
            transform: rotate(3.30769deg) translate(17.5em) rotate(-3.30769deg);
        }
        .circle-container-two > *:nth-of-type(3) {
            transform: rotate(20.61538deg) translate(17.5em) rotate(-20.61538deg);
        }
        .circle-container-two > *:nth-of-type(4) {
            transform: rotate(37.92308deg) translate(17.5em) rotate(-37.92308deg);
        }
        .circle-container-two > *:nth-of-type(5) {
            transform: rotate(55.23077deg) translate(17.5em) rotate(-55.23077deg);
        }
        .circle-container-two > *:nth-of-type(6) {
            transform: rotate(72.53846deg) translate(17.5em) rotate(-72.53846deg);
        }
        .circle-container-two > *:nth-of-type(7) {
            transform: rotate(89.84615deg) translate(17.5em) rotate(-89.84615deg);
        }
        .circle-container-two > *:nth-of-type(8) {
            transform: rotate(107.15385deg) translate(17.5em) rotate(-107.15385deg);
        }
        .circle-container-two > *:nth-of-type(9) {
            transform: rotate(124.46154deg) translate(17.5em) rotate(-124.46154deg);
        }
        .circle-container-two > *:nth-of-type(10) {
            transform: rotate(141.76923deg) translate(17.5em) rotate(-141.76923deg);
        }
        .circle-container-two > *:nth-of-type(11) {
            transform: rotate(159.07692deg) translate(17.5em) rotate(-159.07692deg);
        }
        .circle-container-two > *:nth-of-type(12) {
            transform: rotate(176.38462deg) translate(17.5em) rotate(-176.38462deg);
        }
        .circle-container-two > *:nth-of-type(13) {
            transform: rotate(193.69231deg) translate(17.5em) rotate(-193.69231deg);
        }
        .circle-container-three {
            position: relative;
            width: 44em;
            height: 2.2em;
            border-radius: 50%;
            padding: 0;
            list-style: none;
            margin-left: 22em;
        }
        .circle-container-three > * {
            display: block;
            position: absolute;
            top: 0%;
            left: 0%;
            margin: 0%;
            width: 6em;
            height: 6em;
        }
        .circle-container-three > *:nth-of-type(1) {
            transform: rotate(-16deg) translate(22em) rotate(16deg);
        }
        .circle-container-three > *:nth-of-type(2) {
            transform: rotate(-1.9375deg) translate(22em) rotate(1.9375deg);
        }
        .circle-container-three > *:nth-of-type(3) {
            transform: rotate(12.125deg) translate(22em) rotate(-12.125deg);
        }
        .circle-container-three > *:nth-of-type(4) {
            transform: rotate(26.1875deg) translate(22em) rotate(-26.1875deg);
        }
        .circle-container-three > *:nth-of-type(5) {
            transform: rotate(40.25deg) translate(22em) rotate(-40.25deg);
        }
        .circle-container-three > *:nth-of-type(6) {
            transform: rotate(54.3125deg) translate(22em) rotate(-54.3125deg);
        }
        .circle-container-three > *:nth-of-type(7) {
            transform: rotate(68.375deg) translate(22em) rotate(-68.375deg);
        }
        .circle-container-three > *:nth-of-type(8) {
            transform: rotate(82.4375deg) translate(22em) rotate(-82.4375deg);
        }
        .circle-container-three > *:nth-of-type(9) {
            transform: rotate(96.5deg) translate(22em) rotate(-96.5deg);
        }
        .circle-container-three > *:nth-of-type(10) {
            transform: rotate(110.5625deg) translate(22em) rotate(-110.5625deg);
        }
        .circle-container-three > *:nth-of-type(11) {
            transform: rotate(124.625deg) translate(22em) rotate(-124.625deg);
        }
        .circle-container-three > *:nth-of-type(12) {
            transform: rotate(138.6875deg) translate(22em) rotate(-138.6875deg);
        }
        .circle-container-three > *:nth-of-type(13) {
            transform: rotate(152.75deg) translate(22em) rotate(-152.75deg);
        }
        .circle-container-three > *:nth-of-type(14) {
            transform: rotate(166.8125deg) translate(22em) rotate(-166.8125deg);
        }
        .circle-container-three > *:nth-of-type(15) {
            transform: rotate(180.875deg) translate(22em) rotate(-180.875deg);
        }
        .circle-container-three > *:nth-of-type(16) {
            transform: rotate(194.9375deg) translate(22em) rotate(-194.9375deg);
        }
        button {
            font-size: 0.1em;
            max-width: 100%;
            border-radius: 20%;
            color: black;
            background-color:black;
            border: solid 4em black;
            transition: 0.15s;
        }
        .standard{
        }
        .vip {
            color: #fd8250;
            background-color: #fd8250;
            border: solid 4em #fd8250;
        }
        .reserved {
            color: silver;
            background-color:  silver;
            border: solid 4em silver;
        }
        .chosen-place {
            opacity:0.5;
        }

        .ticket {
            background-color: silver;
            border-left: solid 2px black;
            text-align:center;
            padding:1em;
            margin-top:0.4em;
            margin-bottom:0.4em;
            margin-left:0.5em;
        }

    </style>

</head>

<body>

<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
    <label class="navbar-brand">Les Kurbas</label>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse form-inline my-2 my-lg-0" id="navbarToggler">
        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item form-inline my-2 my-lg-0">
                <a class="nav-link mainmenu-nav-link" id ="home" href="/leskurbas/home">Home</a>
            </li>
            <li class="nav-item" id ="myPage" href="/leskurbas/mypage">
                <a class="nav-link mainmenu-nav-link" href="/leskurbas/mypage">My page</a>
            </li>
            <li class="nav-item">
                <a class="nav-link mainmenu-nav-link" href="/leskurbas/logout">Log out</a>
            </li>
        </ul>
    </div>
</nav>

<input type="hidden" id="sessionID" value="${session.id}">

<div class="row data-block" style="margin-top:2em;">
    <div class="col-md-10" style="width:40em; height:0px;">
        <ul class="circle-container-zero">
            <c:forEach var="seat" items="${session.seats}" begin="0" end="6">
                <li><button data-row="${seat.row}"
                            data-place="${seat.place}"
                            data-price="${seat.price}"
                            onclick="AddTicket(this);"
                            class="${seat.seatClass}"
                >.....</button></li>
            </c:forEach>
        </ul>

        <ul class="circle-container-one">
            <c:forEach var="seat" items="${session.seats}" begin="7" end="16">
                <li><button data-row="${seat.row}"
                            data-place="${seat.place}"
                            data-price="${seat.price}"
                            onclick="AddTicket(this);"
                            class="${seat.seatClass}"
                >.....</button></li>
            </c:forEach>
        </ul>

        <ul class="circle-container-two">
            <c:forEach var="seat" items="${session.seats}" begin="17" end="29">
                <li><button data-row="${seat.row}"
                            data-place="${seat.place}"
                            data-price="${seat.price}"
                            onclick="AddTicket(this);"
                            class="${seat.seatClass}"
                >.....</button></li>
            </c:forEach>
        </ul>
        <ul class="circle-container-three">
            <c:forEach var="seat" items="${session.seats}" begin="30" end="45">
                <li><button data-row="${seat.row}"
                            data-place="${seat.place}"
                            data-price="${seat.price}"
                            onclick="AddTicket(this);"
                            class="${seat.seatClass}"
                >.....</button></li>
            </c:forEach>
        </ul>
    </div>
    <div style="margin-left:47em; font-size:1.1em;">
        This is how the hall looks like. Click on the places you want to book. If you change your mind, click on the chosen place again to cancel your choice.
    </div>
    <div id="tickets-container" class="w-100" style="margin-left:51em; min-height:24em;">
    </div>
    <div style="margin-left:70em; margin-bottom:1em;">
        <input type="submit" value="Book" class="btn btn-primary" style="background-color:#fd8250;" onclick="Book();">
    </div>
    <div style="margin-left:52em; margin-bottom:2em;">
        <span style="display:inline-block; background-color:rgba(253, 128, 78, 0.74); height:2em; width:4em;"></span>
        <span style="display:inline-block; height:2em; vertical-align:super; margin-right:2em;">VIP</span>
        <span class="bg-dark" style="display:inline-block; height:2em; width:4em;"></span>
        <span style="display:inline-block; height:2em; vertical-align:super; margin-right:2em;">Regular</span>
        <span style="display:inline-block; background-color:silver; height:2em; width:4em;"></span>
        <span style="display:inline-block; height:2em; vertical-align:super; margin-right:2em;">Booked</span>
    </div>
</div>


<footer class="page-footer font-small pt-4">
    <div class="footer-copyright text-center py-3">
        © 2018 Copyright: <a href="/leskurbas/check">LesKurbas.com</a>
    </div>
</footer>

</body>
</html>
