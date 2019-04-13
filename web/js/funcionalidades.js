$().ready(inicializar);

function inicializar() {

    inicioFadeOut();

    /*INICIO SESION*/
    $("#pagInicioSesion").click(accesoInicioSesion);
    $("#btnVolver").click(validarSesionUsuario);
    $("#btnInicioSesion").click(accesoValidarUsuario);
    $("#establecimientoDefault").hide();


    /*CERRAR SESION*/
    $('#cerrarSesionUsu').click(function () {
        $('#CerrarSesionVentana').modal('hide');
    });

    /*REGISTRAR CUENTA*/
    $("#registrarCuentaLogin").click(accesoRegistroUsuario);
    $("#iniciarSesionRegistro").click(accesoInicioSesion);
    $("#btnRegistrarUsuario").click(accionRegistroUsuario);

    /*PERFIL*/
    $("#NavPerfil").click(accesoPerfil);
    $("#btnEditarUsuarioModal").click(editarPerfil);
    $("#btnEditarUsuario").click(clickEditarUsuario);

    /*MAPA*/
    $("#NavMapa").click(accesoMapa);
    cargarmap();

    /*RESERVAS*/
    $("#NavReservas").click(accesoReservas);
    $("#btnActualizarReservas").click(accesoReservas);

    /*MIS CANCHAS FAVORITAS*/
    $("#NavCFavoritas").click(accesoCanchasFavoritas);

    /*HISTORIAL*/
    $("#NavHistorial").click(accesoHistorial);

    /*CONTACTENOS*/
    $("#NavContactenos").click(accesoContactenos);
    $("#btnEnviarDatos").click(envioDataContactenos);


    /*ESTABLECIMIENTOS Y CANCHAS*/
    $("#btnConsultarCancha").click(consultaDisponibilidadCancha);
    $("#btnBorrarBusqeudaCancha").click(limpiarTablaReservas);
    $("#cerrarModalCanchas").click(function () {
        localStorage.removeItem("fechaConsultaCalendario");
        localStorage.removeItem("idcancha");
//        localStorage.removeItem("idestablecimiento");
    });



    validarSesionUsuario();
    function validarSesionUsuario() {
        if (localStorage.getItem("currentSesion") == null) {
//            document.getElementById("usuarioLogin").innerHTML = localStorage.getItem("currentUsuario");
            skip();
        } else {
            document.getElementById("usuarioLogin").innerHTML = localStorage.getItem("currentUsuario");
            skipUsuarioRegistrado();
        }
    }
}

var server = 'http://localhost:8080/';
var server2 = '';

/*SKIP*/
function skip() {
    if (localStorage.getItem("currentUsuario") === null) {
        $("#cerrarSesion").hide();
        $("#iniciarSesion").show();
    } else {
        $("#iniciarSesion").hide();
        $("#cerrarSesion").show();
    }
    $("#establecimientoDefault").hide();
    $("#establecimientoDefault").hide();
    $("#pagLogin").hide();
    $("#pagRegistro").hide();
    $("#pagOlvidoContras").hide();
    $("#pagPerfilUsuario").hide();
    $("#pagReservas").hide();
    $("#pagCanchasFavoritas").hide();
    $("#pagHistorial").hide();
    $("#fondoLogin").hide();
    $("#pagContactenos").hide();
    $("#cuerpoPagina").show();
    $("#mainNav").show();
    $("#pagMapa").show();
}
function skipUsuarioRegistrado() {
    $("#iniciarSesion").hide();
    $("#pagLogin").hide();
    $("#pagRegistro").hide();
    $("#pagOlvidoContras").hide();
    $("#pagPerfilUsuario").hide();
    $("#pagReservas").hide();
    $("#pagCanchasFavoritas").hide();
    $("#establecimientoDefault").hide();
    $("#pagHistorial").hide();
    $("#fondoLogin").hide();
    $("#pagContactenos").hide();
    $("#cerrarSesion").show();
    $("#cuerpoPagina").show();
    $("#mainNav").show();
    $("#pagMapa").show();
}

/*ACCESOS A LAS PAGINAS*/
function accesoInicioSesion() {
    skip();
    $("#mainNav").hide();
    $("#cuerpoPagina").hide();
    $("#pagMapa").hide();
    $("#fondoLogin").show();
    $("#pagLogin").show();
}
function inicioFadeOut() {
    $("#mainNav").hide();
    $("#cuerpoPagina").hide();
    $("#pagMapa").hide();
    $("#fondoFadeOut").show();
    $(document).ready(function () {
        setTimeout(function () {
            $("#fondoFadeOut").fadeOut(500);
        }, 4000);
    });
}
function accesoRegistroUsuario() {
    $("#pagLogin").hide();
    $("#fondoLogin").show();
    $("#pagRegistro").show();
}
function accesoPerfil() {
    var direccion = server + 'Tribuna90/app/servicios/consultDataPerfil';
    $.ajax({
        url: direccion,
        data: JSON.stringify(({
            usuario: localStorage.getItem("currentUsuario")
        })),
        type: "POST",
        async: false,
        contentType: "application/json",
        success: function (rta) {
            if (rta.arregloUsuario.length == 1) {

                document.getElementById("nombresUsuarioPerfil").innerHTML = rta.arregloUsuario[0].pnombre + ' ' + rta.arregloUsuario[0].snombre;
                document.getElementById("apellidosUsuarioPerfil").innerHTML = rta.arregloUsuario[0].papellido + ' ' + rta.arregloUsuario[0].sapellido;
                document.getElementById("fechaNacimientoPerfil").innerHTML = rta.arregloUsuario[0].fechan;
                document.getElementById("usuarioPerfil").innerHTML = rta.arregloUsuario[0].usuario;
                document.getElementById("emailPerfil").innerHTML = rta.arregloUsuario[0].correo;
                skip();
                $("#pagMapa").hide();
                $("#iniciarSesion").hide();
                $("#cerrarSesion").show();
                $("#pagPerfilUsuario").show();
            } else {
                alert("Debe ser un usuario registrado para acceder al Perfil de Usuario\nPor Favor, Inicie Sesion para continuar...");
            }
        },
        error: function (xhr, status) {
            alert("Error al consultar " + status)
        }
    });
}
function accesoMapa() {
    skip();
    cargarmap();
}
function accesoReservas() {
    let usuarioSesion = localStorage.getItem("currentUsuario");
    if (usuarioSesion != null) {
        var date = new Date();
        let fechaActual = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        var direccion = server + 'Tribuna90/app/servicios/consultarReservas';
        $.ajax({
            url: direccion,
            data: JSON.stringify(({
                usuario: usuarioSesion
                        //            fechaActual: fechaActual
            })),
            type: "POST",
            async: false,
            contentType: "application/json",
            success: function (rta) {
                let arregloDatos = rta.reserva.arregloReserva;
                if (rta.codigo == 1) {
                    let t = $("#tblReservas").DataTable();
                    t.clear();
                    for (var i = 0; i < arregloDatos.length; i++) {
                        let rowNode = t.row.add([
                            arregloDatos[i].idreserva,
                            arregloDatos[i].fecha,
                            arregloDatos[i].hora,
                            arregloDatos[i].idestablecimiento,
                            arregloDatos[i].idcancha,
                            arregloDatos[i].observaciones,
                            '<a class="btn btn-danger" style="color: white;margin-top: 3%;" id="' + arregloDatos[i].idreserva + '/' + arregloDatos[i].fecha + '/' + arregloDatos[i].hora + '/' + arregloDatos[i].idestablecimiento + '/' + arregloDatos[i].idcancha + '" onclick="cancelarReserva(this.id)">Cancelar Reserva</a>'
                        ]).draw(true).node();
                    }
                    document.getElementById("fechaConsultaReserva").innerHTML = "Ultima actualizacion, " + fechaActual + " a las " + date.getHours() + ":" + date.getMinutes() + ".";
                    skip();
                    $("#iniciarSesion").hide();
                    $("#cerrarSesion").show();
                    $("#pagMapa").hide();
                    $("#pagReservas").show();
                } else {
                    alert('Usuaior: ' + usuarioSesion + ', sin reserva alguna');
                    skip();
                }
            },
            error: function (xhr, status) {
                alert("Error al consultar " + status)
            }
        });
    } else {
        alert("Debe ser un usuario registrado para acceder a las Reservas Activas\nPor Favor, Inicie Sesion para continuar...");
    }

}
function accesoCanchasFavoritas() {
    skip();
    $("#pagMapa").hide();
    $("#pagCanchasFavoritas").show();
}
function accesoHistorial() {
    let usuarioSesion = localStorage.getItem("currentUsuario");
    if (usuarioSesion != null) {
        let direccion = server + 'Tribuna90/app/servicios/consultarReservasHistorico';
        var date = new Date();
        let fechaActual = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        $.ajax({
            url: direccion,
            data: JSON.stringify(({
                usuario: usuarioSesion,
                fechaActual: fechaActual
            })),
            type: "POST",
            async: false,
            contentType: "application/json",
            success: function (rta) {
                let arregloDatos = rta.reserva.arregloReserva;
                if (rta.codigo == '1') {
                    let t = $("#tblReservasHistorico").DataTable();
                    t.clear();
                    for (var i = 0; i < arregloDatos.length; i++) {
                        let rowNode = t.row.add([
                            arregloDatos[i].idreserva,
                            arregloDatos[i].fecha,
                            arregloDatos[i].hora,
                            arregloDatos[i].idestablecimiento,
                            arregloDatos[i].idcancha,
                            arregloDatos[i].observaciones
                        ]).draw(true).node();
                    }
                    document.getElementById("fechaConsultaHistorico").innerHTML = "Ultima actualizacion, " + fechaActual + " a las " + date.getHours() + ":" + date.getMinutes() + ".";
                    skip();
                    $("#pagMapa").hide();
                    $("#iniciarSesion").hide();
                    $("#cerrarSesion").show();
                    $("#pagHistorial").show();
                } else {
                    alert('Usuaior: ' + usuarioSesion + ', sin reserva alguna');
//                location.reload(true);
                }
            },
            error: function (xhr, status) {
                alert("Error al consultar " + status)
            }
        });
    } else {
        alert("Debe ser un usuario registrado para ver el historial de Reservas\nPor Favor, Inicie Sesion para continuar...");
    }
}
function accesoContactenos() {
    skip();
    $("#pagMapa").hide();
    $("#pagContactenos").show();
}

/*LOGIN*/
function accesoValidarUsuario() {

    let usuario = $('#user').val();
    let password = $('#pass').val();


    if (usuario != '' && password != '') {
        var direccion = server + 'Tribuna90/app/servicios/validarUsuarioRegistrado';
        $.ajax({
            url: direccion,
            data: JSON.stringify({
                usuario: usuario,
                contrasena: password
            }),
            type: "POST",
            async: false,
            contentType: "application/json",
            success: function (rta) {
                var codigo = rta.codigo;
                if (codigo === 1) {
                    var date = new Date();
                    alert("Bienvenid@ " + usuario + " a Tribuna 90...");
                    localStorage.setItem("currentSesion", date.getDate() + '' + date.getDay() + '' + date.getHours());
                    localStorage.setItem("currentUsuario", rta.usuarioData.usuario);
                    localStorage.setItem("currentCorreo", rta.usuarioData.correo);
                    skip();
                    $("#pagRegistro").hide();
                    $("#cerrarSesion").show();
                    document.getElementById("usuarioLogin").innerHTML = localStorage.getItem("currentUsuario");

                } else
                {
                    alert("Usuario no registrado");
                    accesoRegistroUsuario();
                }
            },
            error: function () {
                alert("Error al Iniciar Sesion ");
                accesoRegistroUsuario();
            }
        });
    }
}
function cerrarSesionUsuario() {
    localStorage.removeItem("currentSesion");
    localStorage.removeItem("currentUsuario");
    localStorage.removeItem("currentCorreo");
    localStorage.removeItem("idestablecimiento");

    localStorage.removeItem("fechaConsultaCalendario");
    localStorage.removeItem("idcancha");
    localStorage.removeItem("idestablecimiento");

    document.getElementById("usuarioLogin").innerHTML = '';
    skip();
}

/*REGISTRO*/
function accionRegistroUsuario() {

    let pnommbre = $('#pnommbre').val();
    let snombre = $('#snombre').val();
    let papellido = $('#papellido').val();
    let sapellido = $('#sapellido').val();
    let cumpleanios = $('#fechaNacimiento').val();
    let genero = $('#genero').val();
    let usuario = $('#usuario').val();
    let emailRegistro = $('#emailRegistro').val();
    let inputPassword = $('#inputPassword').val();
    let confirmPassword = $('#confirmPassword').val();
    var validacion;
    validarUsuario(usuario, inputPassword);
    function validarUsuario(usuario, password) {
        var direccion = server + 'Tribuna90/app/servicios/validarUsuarioRegistrado';
        $.ajax({
            url: direccion,
            data: JSON.stringify({
                usuario: usuario,
                contrasena: password
            }),
            type: "POST",
            async: false,
            contentType: "application/json",
            success: function (rta) {
                var codigo = rta.codigo;
                if (codigo === 1) {
                    alert("Usuario ya registrado.")
                    validacion = true;
                    $("#pagRegistro").hide();
                    $("#pagLogin").show();
                } else
                {
                    validacion = false;
                }
            },
            error: function () {
                alert("Error al Iniciar Sesion ");
                accesoRegistroUsuario();
            }
        });
    }
    if (validacion == false) {
        if (pnommbre != '' && papellido != '' && sapellido != '' && cumpleanios != '' && genero != '' && usuario != '' && emailRegistro != '' && inputPassword != '' && confirmPassword != '') {
            if (inputPassword == confirmPassword) {
                var direccion = "http://localhost:8081/Tribuna90/app/servicios/registroUsuario";
                $.ajax({
                    url: direccion,
                    data: JSON.stringify(({
                        pnombre: pnommbre,
                        snombre: snombre,
                        papellido: papellido,
                        sapellido: sapellido,
                        fechan: cumpleanios,
                        genero: genero,
                        usuario: usuario,
                        contrasena: inputPassword,
                        correo: emailRegistro
                    })),
                    type: "POST",
                    async: true,
                    contentType: "application/json",
                    success: function (rta) {
                        var codigo = rta.codigo;
                        if (codigo === 1) {
                            alert("Bienvenido " + rta.usuario);
                            var date = new Date();
                            localStorage.setItem("currentSesion", date.getDate() + '' + date.getDay() + '' + date.getHours());
                            localStorage.setItem("currentUsuario", rta.usuarioData.usuario);
                            localStorage.setItem("currentCorreo", rta.usuarioData.correo);
                            skip();
                            $("#iniciarSesion").hide();
                            $("#cerrarSesion").show();
                            document.getElementById("usuarioLogin").innerHTML = localStorage.getItem("currentUsuario");
                        } else
                        {
                            alert("Error al registrar el usuario " + usuario);
                            location.reload(true);
                        }
                    },
                    error: function () {
                        alert("Error al Registrar el usuario ");
                        location.reload(true);
                    }
                });
            } else {
                alert("la contraseña no coincide,\nfavor verificar")
            }
        }
    }
}

/*PERFIL*/
function editarPerfil() {
    $('#modalEditarUsuario').modal('show');
}
function clickEditarUsuario() {
    let pNombre = $("#edicionPNombre").val();
    let sNombre = $("#edicionSNombre").val();
    let pApellido = $("#edicionPApellido").val();
    let sApellido = $("#edicionSApellido").val();
    let fechaNacimiento = $("#edicionFechaNacimiento").val();
    let genero = $("#edicionGenero").val();
    let email = $("#edicionEmail").val();
    let password = $("#edicionPassword").val();
    let usuario = localStorage.getItem("currentUsuario");


    $("#nombresUsuarioPerfil").append(pNombre + " " + sNombre);
    $("#apellidosUsuarioPerfil").append(pApellido + " " + sApellido);
    $("#emailPerfil").append(email);
    let direccion = server + 'Tribuna90/app/servicios/editarDataUsuario';
    $.ajax({
        url: direccion,
        data: JSON.stringify(({
            usuario: usuario,
            pnombre: pNombre,
            snombre: sNombre,
            papellido: pApellido,
            sapellido: sApellido,
            fechan: fechaNacimiento,
            genero: genero,
            contrasena: password,
            correo: email
        })),
        type: "POST",
        async: false,
        contentType: "application/json",
        success: function (rta) {
            if (rta.codigo == 1) {
                alert("Edicion con exito !");
                $('#modalEditarUsuario').modal('hide');
            } else {
                alert("Error al editar el Usuario: ");
            }
        },
        error: function (xhr, status) {
            alert("Error al editar. " + status)
        }
    });


}

/*MAPA -------------------------------------------------------------------------------------------- */
function cargarmap() {
    var x = document.getElementById("demo");
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    }

    function showPosition(position)
    {

        var lat = position.coords.latitude;
        var lon = position.coords.longitude;
        latlon = new google.maps.LatLng(lat, lon)
        mapholder = document.getElementById('mapholder')
        mapholder.style.height = 'auto';
        mapholder.style.width = '100%';
        var myOptions = {
            center: latlon, zoom: 17,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            mapTypeControl: false,
            navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL}
        };
        var map = new google.maps.Map(document.getElementById("mapholder"), myOptions);
        var marker = new google.maps.Marker({position: latlon, map: map, title: "Usted esta aqui", });
        var popup = new google.maps.InfoWindow({content: 'Aqui Estoy'});
        popup.open(map, marker);
        marker.addListener('click', function canchacall() {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        //marcador con la ubicación cancha1
        var place = new google.maps.LatLng(4.6299711, -74.1188146);
        var marker1 = new google.maps.Marker({
            position: place
            , title: '5 Site Futbol'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker1.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.6491782, -74.0896276);
        var marker2 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 2'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker2.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.5924352, -74.1064704);
        var marker3 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 3'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker3.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.7028799, -74.1149246, 13);
        var marker4 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 4'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker4.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.5924352, -74.1064704 / 5);
        var marker5 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 5'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker5.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.6766319, -74.103592, 14);
        var marker6 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 6'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker6.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.6328381, -74.1602707);
        var marker7 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 7'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker7.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.6596587, -74.1721447, 12);
        var marker8 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 8'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker8.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.6704756, -74.1607386, 12);
        var marker9 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 9'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker9.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });
        var place = new google.maps.LatLng(4.5924352, -74.1064704);
        var marker10 = new google.maps.Marker({
            position: place
            , title: 'Cancha de Funbol 10'
            , map: map
            , icon: src = "imagenes/balon.png"
            , });
        marker10.addListener('click', function () {
            accesoCanchaSities5Futbol('5 Site Futbol');
        });


    }
    //globo de informacion al dar un clic en el marcador 2

    function showError(error)
    {
        switch (error.code)
        {
            case error.PERMISSION_DENIED:
                x.innerHTML = "Denegada la peticion de Geolocalización en el navegador."
                break;
            case error.POSITION_UNAVAILABLE:
                x.innerHTML = "La información de la localización no esta disponible."
                break;
            case error.TIMEOUT:
                x.innerHTML = "El tiempo de petición ha expirado."
                break;
            case error.UNKNOWN_ERROR:
                x.innerHTML = "Ha ocurrido un error desconocido."
                break;
        }
    }
}

/*CANCHAS FAVORITAS - POR IMPLEMENTAR*/
function consultaCanchasFavoritas() {
    let arregloCanchas = [
        {
            "id_establecimiento": "red5",
            "id_cancha": "red5_01",
            "nombre_cancha": "Red 5",
            "capacidad": "10",
            "tipo_grama": "sintetica AAA",
            "valor": "250000",
            "caracteristicas": "La cancha tiene una grama tipo exportacion ect ect tec tec"
        },
        {
            "id_establecimiento": "red5",
            "id_cancha": "red5_01",
            "nombre_cancha": "Red 5",
            "capacidad": "10",
            "tipo_grama": "sintetica AAA",
            "valor": "250000",
            "caracteristicas": "La cancha tiene una grama tipo exportacion ect ect tec tec"
        },
        {
            "id_establecimiento": "red5",
            "id_cancha": "red5_01",
            "nombre_cancha": "Red 5",
            "capacidad": "10",
            "tipo_grama": "sintetica AAA",
            "valor": "250000",
            "caracteristicas": "La cancha tiene una grama tipo exportacion ect ect tec tec"
        },
        {
            "id_establecimiento": "red5",
            "id_cancha": "red5_01",
            "nombre_cancha": "Red 5",
            "capacidad": "10",
            "tipo_grama": "sintetica AAA",
            "valor": "250000",
            "caracteristicas": "La cancha tiene una grama tipo exportacion ect ect tec tec"
        }
    ];

    $(arregloCanchas).each(function (key, val) {
        $("#containerCanchasFavoritas").append('<div class="card mb-4 shadow-sm" id="cajaCFavoritas">'
                + '<img class="card-img-top" src="imagenes/Fondo_2.jpg" id="imgEstablFavoritos" data-holder-rendered="true">'
                + '<div class="card-body">'
                + '<p class="card-text"><b>Nombre: </b>' + val.nombre_cancha + '</p>'
                + '<p class="card-text"><b>Capacidad: </b>' + val.capacidad + '</p>'
                + '<p class="card-text"><b>Tipo Grama: </b>' + val.tipo_grama + '</p>'
                + '<p class="card-text"><b>Valor Hora: </b>' + val.valor + '</p>'
                + '<p class="card-text"><b>Caracteriticas: </b><p>' + val.caracteristicas + '</p> </p> '
                + '<div class="d-flex justify-content-between align-items-center">'
                + '<div class="btn-group">'
                + '<button id="btnCancha' + val.id_establecimiento + '" type="button" class="btn btn-sm btn-outline-secondary" onclick="alert()">Ver</button>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</div>');
    })

}


/*CONTACTENOS*/
function envioDataContactenos() {
    let nombre = $("#nombreContactenos").val();
    let email = $("#emailContactenos").val();
    let mensaje = $("#cajaMensajeContactenos").val();
    let direccion = server + 'Tribuna90/app/servicios/envioFormularioContactenos';
    $.ajax({
        url: direccion,
        data: JSON.stringify(({
            nombre: nombre,
            email: email,
            mensaje: mensaje
        })),
        type: "POST",
        async: false,
        contentType: "application/json",
        success: function (rta) {
            if (rta.codigo == 1) {
                alert("Gracias por escribirnos\nte contactaremos lo mas pronto posible");
//                location.reload(true);
            } else {
                alert("Error al editar el Usuario: ");
            }
        },
        error: function (xhr, status) {
            alert("Error al editar. " + status)
        }
    });
}


/*ESTABLECIMIENTOS Y CANCHAS*/
function modalCanchas(id_cancha) {
    limpiarTablaCalendarioCancha();
    document.getElementById("formCOnsultaDisponibilidad").reset();
    let id_establecimiento = localStorage.getItem("idestablecimiento");
    var direccion = server + 'Tribuna90/app/servicios/consultaCancha';
    $.ajax({
        url: direccion,
        data: JSON.stringify(({
            idcancha: id_cancha.id,
            idestablecimiento: id_establecimiento
        })),
        type: "POST",
        async: false,
        contentType: "application/json",
        success: function (rta) {
            let caracteristicas = rta.arregloCanchas[0].caracteristicas;
            let capacidad = rta.arregloCanchas[0].capacidad;
            let idcalendario = rta.arregloCanchas[0].idcalendario;
            let idcancha = rta.arregloCanchas[0].idcancha;
            let idestablecimiento = rta.arregloCanchas[0].idestablecimiento;
            let tgrama = rta.arregloCanchas[0].tgrama;

            /*IMAGENES*/
            let fotos = rta.arregloCanchas[0].foto;
            let imagen1 = $.parseJSON(fotos)['imagenesCancha'][0];
            let imagen2 = $.parseJSON(fotos)['imagenesCancha'][1];
            let imagen3 = $.parseJSON(fotos)['imagenesCancha'][2];

            /*NOMBRE ESTABLECIMIENTO*/
            document.getElementById("modalLabelCancha").innerHTML = localStorage.getItem("idestablecimiento") + " - " + idcancha;

            /*IMAGENES CAROUSEL*/
            document.getElementById("imgCarousel_1_cancha").innerHTML = '<img class="d-block w-100" src="' + imagen1 + '"><div class="carousel-caption d-none d-md-block"><h5>' + idestablecimiento + '</h5><p>No sabras si lo viviste o soñaste...</p></div>';
            document.getElementById("imgCarousel_2_cancha").innerHTML = '<img class="d-block w-100" src="' + imagen2 + '"><div class="carousel-caption d-none d-md-block"><h5>' + idestablecimiento + '</h5><p>No sabras si lo viviste o soñaste...</p></div>';
            document.getElementById("imgCarousel_3_cancha").innerHTML = '<img class="d-block w-100" src="' + imagen3 + '"><div class="carousel-caption d-none d-md-block"><h5>' + idestablecimiento + '</h5><p>No sabras si lo viviste o soñaste...</p></div>';
            /*CARACTERISTICAS*/
            document.getElementById("caracteristicasCancha").innerHTML = caracteristicas;
            /*CAPACIDAD*/
            document.getElementById("capacidadCancha").innerHTML = capacidad;
            /*TIPO GRAMA*/
            document.getElementById("tipoGramaCancha").innerHTML = tgrama;

            localStorage.setItem("idcancha", idcancha);

            $('#modalCancha').modal('show');
        },
        error: function (xhr, status) {
            alert("Error al consultar " + status)
        }
    });
}
/*CALENDARIO*/
function consultaDisponibilidadCancha() {

    var date = new Date();
    let fecha = $('#datepickerCancha').val();
    if (date.getDate() < 10) {
        var dia = '0' + date.getDate();
    }
//    let fechaActual = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + dia;
    let fechaActual = '2019-01-07';
    localStorage.setItem("fechaConsultaCalendario", fecha);
    if (fecha != '')
    {
        // AL VALIDAR LAS FECHAS NO CONCUERDAN 
        if (fecha >= fechaActual) {
            if (fecha <= '2019-06-30') {
                var direccion = server + 'Tribuna90/app/servicios/consultarDisponibilidadCalendario';
                $.ajax({
                    url: direccion,
                    data: JSON.stringify({
                        idestablecimiento: localStorage.getItem("idestablecimiento"),
                        idcancha: localStorage.getItem("idcancha"),
                        disponibilidad: 'Disponible',
                        fecha: fecha
                    }),
                    type: "POST",
                    async: false,
                    contentType: "application/json",
                    success: function (rta) {
                        console.log(rta);
                        let t = $("#tblCalendarioCancha").DataTable();
                        t.clear();
                        t.draw();
                        let t2 = $("#tblCalendarioCancha").DataTable();
                        for (var i = 0; i < rta.length; i++) {
                            let descripcionColor = rta[i].disponibilidad;
                            let rowNode = t2.row.add([
                                rta[i].hora,
                                rta[i].disponibilidad,
                                rta[i].precio,
                                '<form onsubmit="return false"><button class="btn btnCustom" style="width: -webkit-fill-available; background-color: #82FA58;" id="' + rta[i].hora + '" onclick="reservarCancha(this)">'
                                        + '<a class="fa fa-check fa-sm"></a></button></form>'
                            ]).draw(true).node();
                            if (descripcionColor === "Disponible")
                            {
                                $(rowNode).css('background-color', '#82FA58')
                                        .css('color', 'black');
                            }
                        }
                        document.getElementById("fechaConsultaCalendario").innerHTML = "Ultima actualizacion, " + fechaActual + " a las " + date.getHours() + ":" + date.getMinutes() + ".";
                    },
                    error: function (xhr, status) {
                        alert("Error al consultar " + status)
                    }
                });
            } else {
                alert("La fecha de la reserva supera el limite \nmaximo establecido por el administrador");
            }
        } else {
            if (date.getDate() < 10) {
                var dia = '0' + date.getDate();
            }
            let fechaActualCambio = dia + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();
            alert("No es posible realizar reservaciones \ncon fechas anteriores a hoy: " + fechaActualCambio + " \nPor favor, vuelva a intentarlo...");
            var table = $('#tblCalendarioCancha').DataTable();
            var rows = table
                    .rows()
                    .remove()
                    .draw();
        }

    } else {
        alert("por favor indique una fecha !");
    }
}
function reservarCancha(id_hora) {
    if (localStorage.getItem("currentUsuario") != null) {
        let idReserva = Math.round(Math.random() * (1000 - 10000) + parseInt(10000));
        let fechaLocalStorage = localStorage.getItem("fechaConsultaCalendario").split("-");
        let anio = fechaLocalStorage[0];
        let mes = fechaLocalStorage[1];
        let dia = fechaLocalStorage[2];
        var direccion = server + 'Tribuna90/app/servicios/insertarReserva';
        $.ajax({
            url: direccion,
            data: JSON.stringify({
                idreserva: idReserva,
                idestablecimiento: localStorage.getItem("idestablecimiento"),
                idcancha: localStorage.getItem("idcancha"),
                observaciones: "Sin Observaciones",
                usuario: localStorage.getItem("currentUsuario"),
                anio: anio,
                mes: mes,
                dia: dia,
                hora: id_hora.id
            }),
            type: "POST",
            async: false,
            contentType: "application/json",
            success: function (rta) {
                if (rta.codigo == 1) {
                    alert("Reserva Realizada con exito!"
                            + "\nReserna No. " + idReserva
                            + "\nCancha: " + localStorage.getItem("idcancha")
                            + "\nFecha: " + localStorage.getItem("fechaConsultaCalendario")
                            + "\nHora: " + id_hora.id + ":00");
                    consultaDisponibilidadCancha();


                } else {
                    alert("Error al registrar la reserva.")
                    localStorage.removeItem("fechaConsultaCalendario");
                    localStorage.removeItem("idcancha");
                    localStorage.removeItem("idestablecimiento");
//                    location.reload(true);
                }
            },
            error: function (xhr, status) {
                alert("Error al consultar " + status)
//                location.reload(true);
            }
        });
    } else {
        alert("Debe ser un usuario registrado para reservar\nPor Favor, Inicie Sesion para continuar...");
    }
}
function accesoCanchaSities5Futbol(nombreEstablecimiento) {
    let id_establecimiento = nombreEstablecimiento;
    var direccion = server + 'Tribuna90/app/servicios/consultaEstablecimiento';
    $.ajax({
        url: direccion,
        data: JSON.stringify(({
            idestablecimiento: id_establecimiento
        })),
        type: "POST",
        async: false,
        contentType: "application/json",
        success: function (rta) {
            /*IMAGENES DEL CAROUSEL*/
            let imagen1 = $.parseJSON(rta[0].foto)['imagenesEstablecimiento'][0];
            let imagen2 = $.parseJSON(rta[0].foto)['imagenesEstablecimiento'][1];
            let imagen3 = $.parseJSON(rta[0].foto)['imagenesEstablecimiento'][2];
            let imagen4 = $.parseJSON(rta[0].foto)['imagenesEstablecimiento'][3];
            /*DESCRIPCION*/
            let descripcion = rta[0].descripcion;
            /*SERVICIOS OFRECIDOS*/
            let servicio1 = rta[0].servicio1;
            let servicio2 = rta[0].servicio2;
            let servicio3 = rta[0].servicio3;
            /*REDES SOCIALES*/
            let facebook = rta[0].facebook;
            let instagram = rta[0].instagram;
            let twitter = rta[0].twitter;
            let youtube = rta[0].youtube;
            /*CARDS CANCHAS*/
            let idcancha = $.parseJSON(rta[0].idcancha)['canchas'];

            let cantCanchas = rta[0].cantcanchas;
            let idestablecimiento = rta[0].idestablecimiento;
            localStorage.setItem("idestablecimiento", id_establecimiento);
            /*IMAGENES DEL CAROUSEL*/
            document.getElementById("imgCarousel_1").innerHTML = '<img class="d-block w-100" src="' + imagen1 + '"><div class="carousel-caption d-none d-md-block"><h5>' + idestablecimiento + '</h5><p>No sabras si lo viviste o soñaste...</p></div>';
            document.getElementById("imgCarousel_2").innerHTML = '<img class="d-block w-100" src="' + imagen2 + '"><div class="carousel-caption d-none d-md-block"><h5>' + idestablecimiento + '</h5><p>No sabras si lo viviste o soñaste...</p></div>';
            document.getElementById("imgCarousel_3").innerHTML = '<img class="d-block w-100" src="' + imagen3 + '"><div class="carousel-caption d-none d-md-block"><h5>' + idestablecimiento + '</h5><p>No sabras si lo viviste o soñaste...</p></div>';
            document.getElementById("imgCarousel_4").innerHTML = '<img class="d-block w-100" src="' + imagen4 + '"><div class="carousel-caption d-none d-md-block"><h5>' + idestablecimiento + '</h5><p>No sabras si lo viviste o soñaste...</p></div>';
            /*DESCRIPCION*/
            document.getElementById("descripcionEstabl").innerHTML = descripcion;
            /*SERVICIOS OFRECIDOS*/
            document.getElementById("contenedorServiciosOfrecidos").innerHTML = '<div class="row">'
                    + '<div class="col-lg-6">'
                    + servicio1
                    + '</div><div class="col-lg-6">'
                    + servicio2
                    + '</div>'
                    + '<div class="row">'
                    + '<div class="col-lg-6">'
                    + servicio3
                    + '</div>'
                    + '<div class="col-lg-6">'
                    + '</div>'
                    + '</div>';
            /*REDES SOCIALES*/
            document.getElementById("contenedorRedes").innerHTML = '<div class="row">'
                    + '<div class="col-lg-3">'
                    + '<a title="Facebook"href="' + facebook + '"target="_blank">'
                    + '<img src="imagenes/Redes_Sociales/facebook_logo.png" alt="Facebook"/></a>'
                    + '</div>'
                    + '<div class="col-lg-3">'
                    + '<a title="Twitter" href="' + twitter + '" target="_blank">'
                    + '<img src="imagenes/Redes_Sociales/twitter_logo.png" alt="Twitter"/></a>'
                    + '</div>'
                    + '<div class="col-lg-3">'
                    + '<a title="YouTube" href="' + youtube + '" target="_blank">'
                    + '<img src="imagenes/Redes_Sociales/youtube_logo.png" alt="YouTube"/></a>'
                    + '</div>'
                    + '<div class="col-lg-3">'
                    + '<a title="Instagram" href="' + instagram + '" target="_blank">'
                    + '<img src="imagenes/Redes_Sociales/Instagram.png" alt="Instagram"/></a>'
                    + '</div>'
                    + '</div>';
            /*CARDS CANCHAS*/
            var arregloHTML = new Array();
            for (var i = 0; i < idcancha.length; i++) {
                let data = '<div class="col-lg-6">'
                        + '<div class="card mb-4 shadow-sm">'
                        + '<img class="card-img-top" src="' + idcancha[i]['imagenesCancha'][0] + '" data-holder-rendered="true">'
                        + '<div class="card-body">'
                        + '<p class="card-text"><b>' + idcancha[i]['nombreEstablecimiento'] + '</b></p>'
                        + '<p class="card-text"><b>Cancha ' + (i + 1) + '</b></p>'
                        + '<p class="card-text">' + idcancha[i]['direccionEstablecimiento'] + '</p>'
                        + '<p class="card-text">+57 ' + idcancha[i]['telefonoEstablecimiento'] + '</p>'
                        + '<p class="card-text">Puntuacion <b style="color: goldenrod">★ </b>' + idcancha[i]['puntuacionEstablecimiento'] + '</p>'
                        + '<div class="d-flex justify-content-between align-items-center">'
                        + '<div class="btn-group">'
                        + '<button type="button" class="btn btn-sm btn-outline-secondary" id="' + idcancha[i]['idCancha'] + '" onclick="modalCanchas(this)">Ver</button>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>';
                arregloHTML.push(data);
            }
            document.getElementById("contenedorCardsCanchas").innerHTML = arregloHTML[0] + arregloHTML[1];
            skip();
//            $("#iniciarSesion").hide();
//            $("#cerrarSesion").show();
            $("#pagMapa").hide();
            $("#establecimientoDefault").show();
        },
        error: function (xhr, status) {
            alert("Error al consultar " + status)
        }
    });
}
function limpiarTablaReservas() {
    var table = $('#tblCalendarioCancha').DataTable();
    var rows = table
            .rows()
            .remove()
            .draw();
}
function limpiarTablaCalendarioCancha() {
    var table = $('#tblCalendarioCancha').DataTable();
    var rows = table
            .rows()
            .remove()
            .draw();
}

//CANCELAR RESERVA
function cancelarReserva(id) {
    let data = id.split("/");
    var direccion = server + 'Tribuna90/app/servicios/eliminarReserva';
    $.ajax({
        url: direccion,
        data: JSON.stringify(({
            usuario: localStorage.getItem("currentUsuario"),
            idreserva: data[0],
            fecha: data[1],
            hora: data[2],
            idestablecimiento: data[3],
            idcancha: data[4]
        })),
        type: "POST",
        async: false,
        contentType: "application/json",
        success: function (rta) {
            alert(rta.descripcion);
            accesoReservas();
        },
        error: function (xhr, status) {
            alert("Error al Eliminar la Reserva " + id);
        }
    });
}