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