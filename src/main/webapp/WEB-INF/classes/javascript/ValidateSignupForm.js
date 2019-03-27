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
    return true;
}