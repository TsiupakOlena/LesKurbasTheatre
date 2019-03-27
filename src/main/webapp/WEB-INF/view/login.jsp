<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Les Kurbas Theatre</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
        .formTitle{
            font-size: 1.5rem;
        }
        .error{
            color: red;
        }
        #signUpUrl{
            padding-top: 5px;
        }
    </style>
</head>
<body height="100%" width="100%">
<div class="container d-flex h-100" style="padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
    <div class="row justify-content-center align-self-center">
        <form action="/leskurbas/login" method="post" name="loginForm">
            <label class="formTitle" style="padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
                Log in
            </label>
            <c:if test="${state=='error'}">
                <label class="error">
                    Login or password is invalid.
                </label>
            </c:if>
            <div class="form-group">
                <label for="inputLogin">
                    Login
                </label>
                <input id="login" name="login" required type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp" placeholder="Enter login" minlength="6" maxlength="25">
            </div>
            <div class="form-group">
                <label for="inputPassword">
                    Password
                </label>
                <input name="password" required type="password" class="form-control" id="inputPassword" placeholder="Enter password" minlength="6" maxlength="25">
            </div>
            <div class="form-group">
                <label for="rememberMe" style="display: inline-block;">
                    Remember me
                </label>
                <input type="checkbox" name="rememberMe" id="rememberMe" value="yes" style="display: inline-block;"/>
            </div>
            <input type="submit" value="Submit" class="btn btn-primary" style="background-color:#fd8250;padding:3px;
		display: flex;
    justify-content: center;
    align-items: center;">
            <label id="signUpUrl">Don't have an account? <a href="/leskurbas/signup">Sign up.</a></label>
        </form>
    </div>
</div>
</body>
</html>