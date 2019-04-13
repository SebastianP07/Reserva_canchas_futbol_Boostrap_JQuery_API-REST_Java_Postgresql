$(document).ready(function () {
    
    $('#btnInicioSesion').click(login);
    $('#btnRegistrar').click(registrar);
    $('#resetPass').click(restablecePassword);
    
    window.addEventListener('load', init, false);
    
});
    
function login() {
    var password = $('#pass').val();
    var usaurio = $('#user').val();
    alert(usaurio + " - " + password);
};

function registrar() {
    var pNombre = $('#pnommbre').val();
    var sNombre = $('#snombre').val();
    var pApellido = $('#papellido').val();
    var sApellido = $('#sapellido').val();
    var fechaNacimiento = $('#fechaNacimiento').val();
    var genero = $('#genero').val();
    var usuario = $('#usuario').val();
    var correo = $('#email').val();
    var password = $('#inputPassword').val();
    var confirmPassword = $('#confirmPassword').val();
    
    var datosUsuario = {
        pNombre: pNombre,
        sNombre: sNombre,
        pApellido: pApellido,
        sApellido: sApellido,
        fechaNacimiento: fechaNacimiento,
        genero: genero,
        usuario: usuario,
        correo: correo,
        password: password,
        confirmPassword: confirmPassword
    };
    console.log(datosUsuario);
};

function restablecePassword() {
    var restPAssword = $('#inputEmail').val();
    alert(restPAssword);
};

//CARGA LA IMAGEN
function init() {
  var inputFile = document.getElementById('inputFile1');
    console.log(inputFile);
  inputFile.addEventListener('change', mostrarImagen, false);
} 
function mostrarImagen(event) {
  var file = event.target.files[0];
    console.log(file);
  var reader = new FileReader();
  reader.onload = function(event) {
    var img = document.getElementById('img1');
    img.src= event.target.result;
  }
  reader.readAsDataURL(file);
}

