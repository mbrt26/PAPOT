function cargarBodegas() {
    document.getElementById('bodega').focus();
    $.post('MaquinasServlet', {xopcion: 'listaBodegas'}, function(e) {
        $('#detalle').html(e);
    });
}
function crear() {
    var xIdOperacion = document.getElementById('listaOperaciones').value;
    var xbodega = document.getElementById('bodega').value;
    var tMontaje = document.getElementById('tMontaje').value;
    var capInstalada = '0';
    var velocidad = '0';
    $.post('MaquinasServlet', {xopcion: 'crear', xbodega: xbodega, xIdOperacion: xIdOperacion, tMontaje: tMontaje,
        capInstalada: capInstalada, velocidad: velocidad}, function(e) {
        $('#detalle').html(e);
        $('body,html').animate({scrollTop: '2000px'}, 1000);
        document.getElementById('bodega').value = "";
        document.getElementById('tMontaje').value = "";
    });

}
function editar(_id) {
    $.post('MaquinasServlet', {xopcion: 'ListaBodegaEditar', xskmarca: _id}, function(e) {
        var cadenaArray = e.split(',');
        $('#skbodegaactualiza').val(cadenaArray[0]);
        $('#actualizabodega').val(cadenaArray[1]);
        document.getElementById("listaOperaciones2").value = (parseInt(cadenaArray[2]));
        $('#tMontaje2').val(cadenaArray[3]);
        $('#capInstalada2').val(cadenaArray[4]);
        $('#velocidad2').val(cadenaArray[5]);



        $('#edita').on('shown.bs.modal', function() {
            $('#actualizabodega').focus();
        });
    });
}
function ActualizarBodega() {
    var nombre = document.getElementById('actualizabodega').value;
    var bodega = document.getElementById('skbodegaactualiza').value;
    var operacion = document.getElementById('listaOperaciones2').value;
    var tMontaje = document.getElementById('tMontaje2').value;
    var capacidad = document.getElementById('capInstalada2').value;
    var velocidad = document.getElementById('velocidad2').value;
    $.post('MaquinasServlet', {xopcion: 'ActualizarNombre', xnombre: nombre, skbodega: bodega, sk_operacion: operacion,
        tMontaje: tMontaje, capacidad: capacidad, velocidad: velocidad}, function(e) {
        $('#detalle').html(e);
    });
}

function verificarEnterModal(e) {

    tecla = (document.all) ? e.keyCode : e.which;

    if (tecla == 13) {
        $('#edita').modal('hide');
        ActualizarBodega();
    }


}
function eliminar(_id) {
    $.post('MaquinasServlet', {xopcion: 'EliminaBodega', skbodega: _id}, function(e) {
        $('#detalle').html(e);
    });
}

function validar(e) {

    tecla = (document.all) ? e.keyCode : e.which;

    if (tecla == 13) {
        crear();
    }
}


$(document).ready(function() { //Hacia arriba
    irArriba();
});

function irArriba() {
    $('.ir-arriba').click(function() {
        $('body,html').animate({scrollTop: '0px'}, 1000);
    });
    $(window).scroll(function() {
        if ($(this).scrollTop() > 0) {
            $('.ir-arriba').slideDown(600);
        } else {
            $('.ir-arriba').slideUp(600);
        }
    });
    $('.ir-abajo').click(function() {
        $('body,html').animate({scrollTop: '1000px'}, 1000);
    });
}

function focusNombreMaquina() {
    document.getElementById('bodega').focus();
}
function focusNombreMaquina2() {
    document.getElementById('actualizabodega').focus();
}

function transferFocus(e, _focusTarget) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        document.getElementById(_focusTarget).focus();
    }


}


