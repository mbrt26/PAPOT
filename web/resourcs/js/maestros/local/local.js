/*   Document   :TouchRetalServlet
 Created on : 15-jul-2019, 10:18:03
 Author     : Edgar J. García L.*/
//function cargar() {
//    document.getElementById('orden').focus();
//    $('.alerta').alert('dispose');
//    printListRetalByProcess();
//}
//function clickOperador() {
//    $('#radioButtonO1').click();
//}
//function copiarOperador(id) {
//    document.getElementById('idOperadorSelected').value = document.getElementById('radioButtonO' + id).value;
//    document.getElementById('nombreOperadorSelected').value = document.getElementById('inputO' + id).value;
//}
//
//function copiarRetal(id) {
//    document.getElementById('idRetalSelected').value = document.getElementById('radioButtonR' + id).value;
//    document.getElementById('nombreRetalSelected').value = document.getElementById('inputR' + id).value;
//
//}
//
//function clickRetal() {
//    $('#radioButtonR1').click();
//}
//function confirmarRetalT(e) {
//    var tecla = (document.all) ? e.keyCode : e.which;
//    var cantidad = document.getElementById('cantidadRetal').value;
//    if (cantidad == '') {
//        document.getElementById('botonInsertar').disabled = true;
//    } else {
//        var xCantidad = parseFloat(cantidad);
//        document.getElementById('botonInsertar').disabled = false;
//        var guardar = true;
//    }
//    if (tecla == 13) {
//        if (guardar) {
//            guardarRetal();
//        }
//    }
//}
//function focusCantRetal() {
//    var validado = $("#formularioRetal").valid();
//    if (validado) {
//        $('#bd-example-modal-sm').modal('show');
//        $('#bd-example-modal-sm').on('shown.bs.modal', function () {
//            document.getElementById('ordenModal').value = document.getElementById('orden').value;
//            var options = document.getElementById("listaProcesos").options;
//            var index = document.getElementById("listaProcesos").selectedIndex;
//            document.getElementById("procesoModal").value = options[parseInt(index)].text;
//            var options = document.getElementById("listaMaquinas").options;
//            index = document.getElementById("listaMaquinas").selectedIndex;
//            document.getElementById("maquinaModal").value = options[parseInt(index)].text;
//            document.getElementById("operarioModal").value = document.getElementById('nombreOperadorSelected').value;
//            document.getElementById("retalModal").value = document.getElementById('nombreRetalSelected').value;
//            $('#cantidadRetal').focus();
//        });
//    }
//}
//
//function printListRetalByProcess() {
//    document.getElementById('guardarRetal').disabled = true;
//    var idProceso = document.getElementById('listaProcesos').value;
//    $.post('TouchRetalServlet', {xopcion: 'printRetales', sk_proceso: idProceso}, function (e) {
//        $('#listaRetales').html(e);
//        copiarRetal(1);
//        printListOperatorslByProcess();
//    });
//
//}
//function printListaMaquinas() {
//    var idProceso = document.getElementById('listaProcesos').value;
//    $.post('TouchRetalServlet', {xopcion: 'printMaquinas', sk_proceso: idProceso}, function (e) {
//        $('#listaMaquinas').html(e);
//        document.getElementById('guardarRetal').disabled = false;
//    });
//}
//function printListaMaquinasReporteUnaVez() {
//    var idProceso = document.getElementById('listaProcesos').value;
//    $.post('TouchRetalServlet', {xopcion: 'printMaquinasReporte', sk_proceso: idProceso}, function (e) {
//        $('#listaMaquinas').html(e);
//        consultarGlobal();
//    });
//    //
//}
//
//function printListaMaquinasReporte() {
//    var idProceso = document.getElementById('listaProcesos').value;
//    $.post('TouchRetalServlet', {xopcion: 'printMaquinasReporte', sk_proceso: idProceso}, function (e) {
//        $('#listaMaquinas').html(e);
//
//    });
//
//}

//function recargarPagina() {
//    $("#formularioRetal").submit();
//}
//function printListOperatorslByProcess() {
//    var idProceso = document.getElementById('listaProcesos').value;
//
//}
$(document).ready(function () { //Hacia arriba
    irArriba();
    //validarFormulario();
});
//
//function miFuncion() {
//    guardarRetal();
//}
//function focusObservacion(e) {
//    var tecla = (document.all) ? e.keyCode : e.which;
//    if (tecla == 13) {
//        document.getElementById('ControlTextarea').focus();
//    }
//}
function irArriba() {
    $('.ir-arriba').click(function () {
        $('body,html').animate({scrollTop: '0px'}, 1000);
    });
    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('.ir-arriba').slideDown(600);
            $('.ir-abajo').slideUp(600);
        } else {
            $('.ir-arriba').slideUp(600);
            $('.ir-abajo').slideDown(600);
        }
    });
    $('.ir-abajo').click(function () {
        $('body,html').animate({scrollTop: '6000px'}, 2000);
    });
}
function getDatos() {

//
//    $.post('LocalServlet', {xopcion: 'getDatos'}, function (e) {
//        let datos = e.split('\\');
//        let i = 0;
//        document.getElementById('razonSocial').value = datos[i++];
//        document.getElementById('nombreLocal').value = datos[i++];
//        document.getElementById('nit').value = datos[i++];
//        document.getElementById('email').value = datos[i++];
//        document.getElementById('direccion').value = datos[i++];
//        document.getElementById('idCiudad').value = datos[i++];
//        document.getElementById('telefono').value = datos[i++];
//        document.getElementById('fax').value = datos[i++];
//        document.getElementById('resolucion').value = datos[i++];
//        document.getElementById('resolucion2').value = datos[i++];
//        document.getElementById('idRes1').value = datos[i++];
//        document.getElementById('idRes2').value = datos[i++];
//        document.getElementById('idRes3').value = datos[i++];
//        document.getElementById('idRes4').value = datos[i++];
//        document.getElementById('idRegimen').value = datos[i++];
//        document.getElementById('idOperacion').value = datos[i++];
//        ///console.log(datos);
//    });
}
//function validarFormulario() {
//    jQuery.validator.messages.required = 'Obligatorio';
//    jQuery.validator.messages.number = 'El valor debe ser numerico.';
//}
//
//function pulsar(e) {
//    tecla = (document.all) ? e.keyCode : e.which;
//    return (tecla != 13);
//}
//function clickHistorico() {
//    document.getElementById('botonBuscarTraslado').click();
//}

function guardarDatos() {
    loadWaitingSpinner();
//    let razonSocial = document.getElementById('razonSocial').value;
//    let nombreLocal = document.getElementById('nombreLocal').value;
//    let nit = document.getElementById('nit').value;
//    let email = document.getElementById('email').value;
//    let direccion = document.getElementById('direccion').value;
//    let idCiudad = document.getElementById('idCiudad').value;
//    let telefono = document.getElementById('telefono').value;
//    let fax = document.getElementById('fax').value;
//    let resolucion = document.getElementById('resolucion').value;
//    let resolucion2 = document.getElementById('resolucion2').value;
//    let idRes1 = document.getElementById('idRes1').value;
//    let idRes2 = document.getElementById('idRes2').value;
//    let idRes3 = document.getElementById('idRes3').value;
//    let idRes4 = document.getElementById('idRes4').value;
//    let idRegimen = document.getElementById('idRegimen').value;
//    let idOperacion = document.getElementById('idOperacion').value;
    /*$.post('LocalServlet', {xopcion: 'guardarDatos', razonSocial: razonSocial, nombreLocal: nombreLocal, nit: nit, email: email,
        direccion: direccion, idCiudad: idCiudad, telefono: telefono, fax: fax, idRes1: idRes1, idRes2, idRes3: idRes3, idRes4: idRes4,
        idRegimen: idRegimen, idOperacion: idOperacion,resolucion:resolucion,resolucion2:resolucion2
    }, function (e) {
        quiteWaitingSpinner();
    });*/

}

function loadWaitingSpinner() {
    document.getElementById('btnSpinner').hidden = false;
    document.getElementById('btnGuardar').hidden = true;
}

function quiteWaitingSpinner() {
    document.getElementById('btnSpinner').hidden = true;
    document.getElementById('btnGuardar').hidden = false;
}











