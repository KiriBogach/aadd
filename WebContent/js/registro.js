"use strict";

$("#botonRegistro").click(function () {
    var user = $("#usuario").val();
    var password = $("#password").val();
    var email = $("#email").val();

    user = user.replace('"', '').replace("'", '');
    password = password.replace('"', '').replace("'", '');
    email = email.replace('"', '').replace("'", '');


    if (user && password && email) {
        $("#form").submit();
        alert('Registro correcto');
    } else {
        $('#alerta').fadeTo('slow', 1).delay(2000).fadeTo('slow', 0);
    }
});


$("#botonAtras").click(function () {
    window.location.href = "index.html";
});