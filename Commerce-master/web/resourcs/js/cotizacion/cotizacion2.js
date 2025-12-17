function desplegarMenus()
{

    var opcionReporte = document.getElementById('listaReportes').value;
    if (opcionReporte == '1') {
        document.getElementById("panelBusquedaMateria").hidden = false;
        var myModal = new bootstrap.Modal(document.getElementById("modal-sel-mat-prima"), {});
        $.post('CotizacionServletRef2', {xopcion: 'listaMatPrimas'
        }, function (e) {
            $('#tablaModalMat').html(e);
            document.getElementById("panelDivisas").hidden = true;
            myModal.show();
        });
    } else if (opcionReporte == '2') {
        document.getElementById("panelBusquedaMateria").hidden = true;
        var myModal = new bootstrap.Modal(document.getElementById("modal-sel-mat-prima"), {});
        $.post('CotizacionServletRef2', {xopcion: 'listaMatPrimasDivisas'}, function (e) {
            $('#tablaModalMat').html(e);
            document.getElementById("panelDivisas").hidden = false;
            calcularCostoSegunDivisas();
            myModal.show();
        });
    }
}

function evaluarOpcion() {
    var opcionReporte = document.getElementById('listaReportes').value;
    if (opcionReporte == '1' || opcionReporte == '2') {
        desplegarMenus();

    } else if (opcionReporte == '0') {

        consultarReporteMargen();
    }
}

function calcularCostoSegunDivisas()
{
    var num = document.getElementById('tablaMateria').getElementsByTagName('tr').length - 1;
    var divisaActual = parseFloat(document.getElementById('divisaActual').value);
    var divisaFuturo = parseFloat(document.getElementById('divisaFuturo').value);
    for (var i = 0; i < parseInt(num); i++) {
        var costo = (parseFloat(document.getElementById(('xmat' + i)).value) / divisaActual) * divisaFuturo;

        document.getElementById('mat' + i).value = costo.toFixed(2);
    }
}


function listClients()
{
    $.post('CotizacionServlet', {xopcion: 'lista'}, function (e) {
        $('#detalleCliente').html(e);
    });

}
function listClientsModal()
{
    $('#modal-sel-cliente').on('shown.bs.modal', function () {
        var nombre = document.getElementById("nombreEmpresa").value;
        $.post('CotizacionServlet', {xopcion: 'listaModal', nombre: nombre}, function (e) {
            $('#tablaModalClientes').html(e);
            $('#nombreEmpresa').focus();
            primerCliente();
        });
    });
}


function bajarPagina() {
    $('body,html').animate({scrollTop: '5000px'}, 2000);
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
            $('.ir-abajo').slideUp(600);
        } else {
            $('.ir-arriba').slideUp(600);
            $('.ir-abajo').slideDown(600);
        }
    });
    $('.ir-abajo').click(function () {
        $('body,html').animate({scrollTop: '4000px'}, 2000);
    });
}


function consultarReporteMargen() {
    document.getElementById('btnSpinnerBusqueda').hidden = false;
    document.getElementById('botonBuscarReporteMargenes').hidden = true;
    var numRep = document.getElementById('listaReportes').value;
    if (numRep == '0') {

        var idCliente = document.getElementById('listaClientes4').value;
        var excludeEmptyOrders = document.getElementById('checkOmitt').checked;
        $.post('CotizacionServletRef2', {xopcion: 'listaReportRevenue',
            idCliente: idCliente
            , excludeEmptyOrders: excludeEmptyOrders}, function (e) {
            $('#reporteMargenes').html(e);
            document.getElementById('btnSpinnerBusqueda').hidden = true;
            document.getElementById('botonBuscarReporteMargenes').hidden = false;
        });
    } else
    if (numRep == '1')
    {
        var num = document.getElementById('tablaMateria').getElementsByTagName('tr').length - 1;
        var idCliente = document.getElementById('listaClientes4').value;
        var costos = null;
        var plus = null;
        for (var i = 0; i < parseInt(num); i++) {
            costos = costos + parseFloat(document.getElementById(('mat' + i)).value) + '#';
            plus = plus + parseFloat(document.getElementById(('plu' + i)).value) + '#';
        }
        $.post('CotizacionServletRef2', {xopcion: 'listaReportRevenue2', costos: costos, plus: plus, idCliente: idCliente}, function (e) {
            $('#reporteMargenes').html(e);
            document.getElementById('btnSpinnerBusqueda').hidden = true;
            document.getElementById('botonBuscarReporteMargenes').hidden = false;

        });
    } else
    if (numRep == '2')
    {
        var num = document.getElementById('tablaMateria').getElementsByTagName('tr').length - 1;
        var idCliente = document.getElementById('listaClientes4').value;
        var costos = null;
        var plus = null;
        var divisaActual = document.getElementById('divisaActual').value;
        var divisaFuturo = document.getElementById('divisaFuturo').value;
        for (var i = 0; i < parseInt(num); i++) {
            costos = costos + parseFloat(document.getElementById(('mat' + i)).value) + '#';
            plus = plus + parseFloat(document.getElementById(('plu' + i)).value) + '#';
        }
        $.post('CotizacionServletRef2', {xopcion: 'listaReportRevenue3', costos: costos, plus: plus, idCliente: idCliente, divisaActual: divisaActual, divisaFuturo: divisaFuturo}, function (e) {
            $('#reporteMargenes').html(e);
            document.getElementById('btnSpinnerBusqueda').hidden = true;
            document.getElementById('botonBuscarReporteMargenes').hidden = false;

        });
    }

}

//muestra carga cuando se imprime PDF en boton de portal principal
function mostrarCarga() {
    document.getElementById('botonImprimirPDF').hidden = true;
    document.getElementById('botonImprimirPDFSpinner').hidden = false;
}

