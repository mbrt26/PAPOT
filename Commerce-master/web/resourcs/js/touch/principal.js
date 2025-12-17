function buscarOT(parametro) {
    var numeroOrden = document.getElementById('campoOrden').value;
    var idOperacion = parametro;
    document.getElementById(('campoOrden')).focus();
    $.post('TouchPrincipalServlet', {xopcion: 'printOrdenTrabajo', numeroOrden: numeroOrden, idOperacion: idOperacion}, function (e) {
        $('#detalleOrdenTrabajo').html(e);

    });
}

function colocarCursor() {
    document.getElementById(('campoOrden')).focus();

}

    