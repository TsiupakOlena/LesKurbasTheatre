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

    .formTitle{
        font-size: 1.5rem;
    }
    .success {
        color: dodgerblue;
    }
    .error {
        color: red;
    }
</style>

<script type="text/javascript">
    function loginEmailCheck() {
        let login = $('#login').val();
        $('#login').removeClass("success");
        $('#login').removeClass("error");

        if (login.length > 3) {
            $.get("LoginValidation?login=" + login, function (data) {
                let result = JSON.parse(data);
                if (result.status == "error") {
                    $('#login').addClass("error");
                } else {
                    $('#login').addClass("success");
                }
                console.log(result);
            });
        }

        let email = $('#email').val();
        $('#email').removeClass("success");
        $('#email').removeClass("error");

        if (email.length > 3) {
            $.get("EmailValidation?email=" + email, function (data) {
                let result = JSON.parse(data);
                if (result.status == "error") {
                    $('#email').addClass("error");
                } else {
                    $('#email').addClass("success");
                }
                console.log(result);
            });
        }
    }
</script>

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

<div class="row justify-content-center align-self-center">
    <form action="/leskurbas/editprofile" method="post" name="editForm" onsubmit="return validateSignupForm();" enctype="multipart/form-data">
        <label class="formTitle" style="padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
            Edit profile
        </label>
        <input type="hidden" name="id" value="${person.id}">
        <div class="form-group">
            <label for="inputSurname">
                Surname
            </label>
            <input value="${person.surname}" name="surname" required type="text" class="form-control" id="inputSurname" placeholder="Enter surname" maxlength="30">
            <label for="inputName">
                Name
            </label>
            <input value="${person.name}" name="name" required type="text" class="form-control" id="inputName" placeholder="Enter name" maxlength="30">
        </div>
        <div class="form-group">
            <label for="inputLogin">
                Login
            </label>
            <input value="${person.login}" id="login" name="login" required type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp" placeholder="Enter login" minlength="6" maxlength="25" onchange="loginEmailCheck()">
            <small id="loginHelp" class="form-text text-muted">
                Login must be unique.
            </small>
        </div>
        <div class="form-group">
            <label for="inputEmail">
                Email address
            </label>
            <input value="${person.email}" id="email" name="email" required type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Enter email" maxlength="30" onchange="loginEmailCheck()">
            <small id="emailHelp" class="form-text text-muted">
                We'll never share your email with anyone else. Email must be unique.
            </small>
        </div>
        <div class="form-group">
            <label for="inputPassword">
                Password
            </label>
            <input value="${person.password}" name="password" required type="password" class="form-control" id="inputPassword" placeholder="Enter password" minlength="6" maxlength="25">
            <label for="confirmPassword">
                Confirm password
            </label>
            <input value="${person.password}" name="password2" required type="password" class="form-control" id="confirmPassword" placeholder="Comfirm password" minlength="6" maxlength="25">
        </div>
        <div class="form-group">
            <label for="inputFile">
                Image
            </label>
            <input name="image" type="file" class="form-control" id="inputFile">
        </div>
        <input type="submit" value="Submit" class="btn btn-primary" style="background-color:#fd8250;padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
    </form>
</div>

<script type="text/javascript">
    function validateSignupForm(){

        var name = document.editForm.name.value;
        var surname = document.editForm.surname.value;
        var login = document.editForm.login.value;
        var email = document.editForm.email.value;
        var password = document.editForm.password.value;
        var password2 = document.editForm.password2.value;
        var pattern;

        pattern =/[A-Z][A-Za-z]{2,30}/;
        if(!pattern.test(surname) || !pattern.test(name)) {
            alert("Error: Name and surname must consist of letters!");
            return false;
        }

        pattern =/[A-Za-z0-9_]{6,25}/;
        if(!pattern.test(login)) {
            alert("Error: Login must consist of letters, numbers and underscores!");
            return false;
        }
        if(password === password2) {
            if(password === login) {
                alert("Error: Password must be different from login!");
                return false;
            }
            pattern = /[0-9]/;
            if(!pattern.test(password)) {
                alert("Error: password must contain at least one number (0-9)!");
                return false;
            }
            pattern = /[a-z]/;
            if(!pattern.test(password)) {
                alert("Error: password must contain at least one lowercase letter (a-z)!");
                return false;
            }
            pattern = /[A-Z]/;
            if(!pattern.test(password)) {
                alert("Error: password must contain at least one uppercase letter (A-Z)!");
                return false;
            }
        } else {
            alert("Error: passwords don't match!");
            return false;
        }

        if (document.getElementById("email").classList.contains("error")
            || document.getElementById("login").classList.contains("error")) {
            alert("Error: Login and email must be unique!");
            return false;
        }
        return true;
    }
</script>
<footer class="page-footer font-small pt-4">
    <div class="footer-copyright text-center py-3">
        © 2018 Copyright: <a href="/leskurbas/home">LesKurbas.com</a>
    </div>
</footer>

</body>
</html>
