function cargarBodegas() {
    document.getElementById('bodega').focus();
    $.post('ParoMaquinaServlet', {xopcion: 'listaBodegas'}, function (e) {
        $('#detalle').html(e);
    });
}
function creaBodega() {
    var bod = document.getElementById('bodega').value;
    if (bod == "") {
        alert('EL nombre no es valido ingrese nombre valido');
    } else {
        $.post('ParoMaquinaServlet', {xopcion: 'crear', xbodega: bod}, function (e) {
            $('#detalle').html(e);
            $('#bodega').val('');
            $('#bodega').focus();
            $('body,html').animate({scrollTop: '2000px'}, 1000);
        });
    }
}
function editar(_id) {
    $.post('ParoMaquinaServlet', {xopcion: 'ListaBodegaEditar', xskmarca: _id}, function (e) {
        var cadenaArray = e.split(',');
        $('#skbodegaactualiza').val(cadenaArray[0]);
        $('#actualizabodega').val(cadenaArray[1]);
           $('#edita').on('shown.bs.modal', function () {
            $('#actualizabodega').focus();
        });
    });
}
function ActualizarBodega() {
    var nombre = document.getElementById('actualizabodega').value;
    var bodega = document.getElementById('skbodegaactualiza').value;
    $.post('ParoMaquinaServlet', {xopcion: 'ActualizarNombre', xnombre: nombre, skbodega: bodega}, function (e) {
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
    $.post('ParoMaquinaServlet', {xopcion: 'EliminaBodega', skbodega: _id}, function (e) {
        $('#detalle').html(e);
    });
}

function validar(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        creaBodega();
    }
}

$(document).ready(function () { //Hacia arriba
    irArriba();
});

function irArriba() {
    $('.ir-arriba').click(function () {
        $('body,html').animate({scrollTop: '0px'}, 1000);
    });
    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('.ir-arriba').slideDown(600);
        } else {
            $('.ir-arriba').slideUp(600);
        }
    });
    $('.ir-abajo').click(function () {
        $('body,html').animate({scrollTop: '1000px'}, 1000);
    });
}

function focusNombre2() {
    document.getElementById('bodega').focus();
}

function focusNombre() {
    document.getElementById('actualizabodega').focus();
}