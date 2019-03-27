<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Les Kurbas Theatre</title>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous">
    </script>

 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
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
</head>
<body height="100%" width="100%">
<div class="container d-flex h-100" style="padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
    <div class="row justify-content-center align-self-center">
        <form action="/leskurbas/signup" method="post" name="signUpForm" onsubmit="return validateSignupForm();">
            <label class="formTitle" style="padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
                Sign up
            </label>
            <div class="form-group">
                <label for="inputSurname">
                    Surname
                </label>
                <input name="surname" required type="text" class="form-control" id="inputSurname" placeholder="Enter surname" maxlength="30">
                <label for="inputName">
                    Name
                </label>
                <input name="name" required type="text" class="form-control" id="inputName" placeholder="Enter name" maxlength="30">
            </div>
            <div class="form-group">
                <label for="inputLogin">
                    Login
                </label>
                <input id="login" name="login" required autocomplete="off" type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp" placeholder="Enter login" minlength="6" maxlength="25" onchange="loginEmailCheck()">
                <small id="loginHelp" class="form-text text-muted">
                    Login must be unique.
                </small>
            </div>
            <div class="form-group">
                <label for="inputEmail">
                    Email address
                </label>
                <input id="email" name="email" required autocomplete="off" type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Enter email" maxlength="30" onchange="loginEmailCheck()">
                <small id="emailHelp" class="form-text text-muted">
                    We'll never share your email with anyone else. Email must be unique.
                </small>
            </div>
            <div class="form-group">
                <label for="inputPassword">
                    Password
                </label>
                <input name="password" required type="password" class="form-control" id="inputPassword" placeholder="Enter password" minlength="6" maxlength="25">
                <label for="confirmPassword">
                    Confirm password
                </label>
                <input name="password2" required type="password" class="form-control" id="confirmPassword" placeholder="Comfirm password" minlength="6" maxlength="25">
            </div>
            <input type="submit" value="Submit" class="btn btn-primary" style="background-color:#fd8250;padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
        </form>
    </div>
</div>
<script type="text/javascript">
    function validateSignupForm(){

        var name = document.signUpForm.name.value;
        var surname = document.signUpForm.surname.value;
        var login = document.signUpForm.login.value;
        var email = document.signUpForm.email.value;
        var password = document.signUpForm.password.value;
        var password2 = document.signUpForm.password2.value;
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
</body>
</html>