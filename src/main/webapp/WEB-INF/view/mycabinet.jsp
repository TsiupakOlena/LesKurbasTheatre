<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <style>
        h3 {
            margin: 2px;
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
                float:left;
                width: 100%;
                -webkit-box-shadow: 3px 3px 2px 0px rgba(163,160,163,1);
                -moz-box-shadow: 3px 3px 2px 0px rgba(163,160,163,1);
                box-shadow: 3px 3px 2px 0px rgba(163,160,163,1);
            }
            .user-nav {
                width: 100%;
                float: none;
            }
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
<div style="overflow:hidden;">
    <div class="user-nav navbar-dark bg-dark" style="float:left;">
        <h3 class="text-center">
            ${user.login}
        </h3>
        <div class="row justify-content-center align-self-center">
            <img src="/leskurbas/image?picture=${user.picture}" class="rounded-circle" style="border: 5px solid rgba(253, 128, 78, 0.74); width:100px; height:100px;" alt="user image" width="304" height="236">
        </div>
        <nav class="nav flex-column w-100">
            <a class="nav-link user-nav w-100" href="/leskurbas/editprofile">My profile</a>
            <a class="nav-link user-nav active w-100" href="/leskurbas/mypage">My bookings</a>
        </nav>
    </div>

    <div>
        <ul class="bookings-list">
                <c:if test="${fn:length(user.reservations) != 0}">
                    <c:forEach var="entry" items="${user.reservations}">
                        <li class="shadow bookings-item">
                            <div class="row">
                                <div class="col-4">
                                    <img class="d-block w-100" style="height:100px; float: left;" src="/leskurbas/image?picture=${entry.picture}" height="10%" width="10%" alt="Play picture">
                                </div>
                                <div class="col-8">
                                    <h3>${entry.name}</h3>
                                    <span>Date:
                                        <span class="reservation-date">${entry.date}</span>
                                        <span class="reservation-date">${entry.time}</span>
                                    </span>
                                    <span style="float: right; padding-right:5%;">Price: ${entry.price}</span>
                                    <div>
                                        Places:
                                        <c:forEach var="seat" items="${entry.seats}">
                                            <span class="border">
                                                Row: <span>${seat.row}</span>
                                                Number: <span>${seat.place}</span>
                                            </span>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            <c:if test="${fn:length(user.reservations) == 0}">
                <li class="notice">
                    You have no bookings yet. All your bookings will be displayed here.
                </li>
            </c:if>
        </ul>
    </div>
</div>
<footer class="page-footer font-small pt-4" style="position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  text-align: center;">
    <div class="footer-copyright text-center py-3">
        © 2018 Copyright: <a href="/leskurbas/check">LesKurbas.com</a>
    </div>
</footer>
</body>
<html>
