<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Les Kurbas Theatre</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>

<style>

    h3 {
        margin: 2px;
    }
    .shadow {
        -webkit-box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
        -moz-box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
        box-shadow: 5px 5px 4px 0px rgba(163,160,163,1);
    }
    .play-list-item{
        margin-bottom: 8px;
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
    a.nav-link {
        display:block;
        background-color:#fd8250;
    }
    #home, #myPage, #adminPage {
        border-right: 2px solid #000000;">
    }

    .navbar-brand {
        color: white;
    }

    .carousel-item {
        min-width: auto;
    }

    .page-footer{
        background-color:rgba(253, 128, 78, 0.74);
    }
    .plays {
        list-style-type: none;
    }
</style>
<body>
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <label class="navbar-brand">Les Kurbas</label>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse form-inline my-2 my-lg-0" id="navbarToggler">
            <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                <li class="nav-item form-inline my-2 my-lg-0">
                    <a class="nav-link" id ="home" href="/leskurbas/home">Home</a>
                </li>
                <c:if test="${sessionState=='Log out'}">
                    <c:if test="${isAdmin == true}">
                        <li class="nav-item" id ="adminPage">
                            <a class="nav-link" href="/leskurbas/adminpage">Management</a>
                        </li>
                    </c:if>
                    <c:if test="${isAdmin == false}">
                        <li class="nav-item" id ="adminPage">
                            <a class="nav-link" href="/leskurbas/mypage">My page</a>
                        </li>
                    </c:if>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="${sessionUrl}">${sessionState}</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="row data-block">
        <div class="col-md-8 offset-md-2">

            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">

                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleControls" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleControls" data-slide-to="1"></li>
                    <li data-target="#carouselExampleControls" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="/leskurbas/image?picture=carousel1.png" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="/leskurbas/image?picture=carousel2.png" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="/leskurbas/image?picture=carousel3.png" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
    <div class="row data-block">
        <div class="col-md-8 offset-md-2 shadow">
            <h1>The heart of Lviv modern culture</h1>
            <p>
                Lviv Academic Theater named after Les Kurbas was founded in 1988 by Volodymyr Kuchinsky and a group of young actors who, like the prominent Ukrainian director Les Kurbas and his colleagues in 1918, felt the need "...to say something new and for this one and only reason create a theater. "  Criticism calls this collective a unique theatrical phenomenon, because it has returned intellectual prestige to the Ukrainian stage, created its methodological school and combined multifaceted stage practice with the process of cognition of Man.
            </p>

        </div>
    </div>

    <div class="row data-block">
        <div class="col-md-8 offset-md-2 shadow">
            <h1>Plays</h1>
            <ul class="plays">
                <c:forEach var="entry" items="${plays}">
                    <li class="shadow play-list-item">
                        <div class="row">
                            <div class="col-4">
                                <img class="d-block w-100" style="height:100px; " src="/leskurbas/image?picture=${entry.image}" height="10%" width="10%" alt="Play picture" style="float: left;">
                            </div>
                            <div class="col-8">
                                <h3>${entry.name}</h3>
                                <span>Duration: ${entry.duration}</span>
                                <c:forEach var="session" items="${entry.sessions}">
                                    <a href="/leskurbas/book?session=${session.id}" class="border" style="display: inline-block;">
                                        Session:
                                        <span>${session.date}</span>
                                        <span>${session.time}</span>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <footer class="page-footer font-small pt-4">
        <div class="footer-copyright text-center py-3">
            © 2018 Copyright: <a href="/leskurbas/home">LesKurbas.com</a>
        </div>
    </footer>

</body>
</html>