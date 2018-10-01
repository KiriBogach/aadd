"use strict";

$("#botonLogin").click(function () {
    var user = $("#usuario").val();
    var password = $("#password").val();

    /* Quitamos ' y " de las entradas */
    //user = user.replace('"', '').replace("'", '').trim();
    //password = password.replace('"', '').replace("'", '').trim();
    
    if (user && password) {
        $("#form").submit();
    } else {
        $('#alerta').fadeTo('slow', 1).delay(2000).fadeTo('slow', 0);
    }
});

$("#botonRegistro").click(function () {
    location.href = 'registro.html';
});