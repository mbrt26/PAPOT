

var cacheIdCliente = 0;
var precioVenta = 0;
var flete = 0;
var datosSel;
function guardarSeleccion(data) {
    datosSel = data;

}
function printSeleccion() {
    $.post('CotizacionServlet', {xopcion: 'printClienteCot', nit: datosSel
    }, function (e) {
        $('#detalleClienteCot').html(e);
        printTablaRef();
    });

}

function printTablaRef() {
    $.post('CotizacionServletRef', {xopcion: 'listaCot', idCliente: datosSel}, function (e) {
        $('#detalleReferenciasCot').html(e);
    });

}
function refrescarCostoFlete() {
    flete = parseFloat(document.getElementById('flete').value) * parseFloat(document.getElementById('bache').value);
    document.getElementById('costosFlete').value = flete.toFixed(2);
    console.log("refrescarCostoFlete() ");
    getCostoTotalReferenciaSinMargen();
}
function refrescarCostoFleteEdit() {
    flete = parseFloat(document.getElementById('flete').value) * parseFloat(document.getElementById('bache').value);
    document.getElementById('costosFlete').value = flete.toFixed(2);
}

function calcularCantidad() {
    var pesoMillar = document.getElementById('pesoMillar').value;
    var batch = document.getElementById('bache').value;
    document.getElementById('cantidadPedido').value = (1000 * (batch / pesoMillar)).toFixed(2);
    calcularPrecioVenta();
}
function calcularCantidadNewRef() {
    var pesoMillar = document.getElementById('pesoMillar').value;
    var batch = document.getElementById('bache').value;
    document.getElementById('cantidadPedido').value = (1000 * (batch / pesoMillar)).toFixed(2);
}
function calcularCantidadEdit() {
    var pesoMillar = document.getElementById('pesoMillar').value;
    var batch = document.getElementById('bache').value;
    document.getElementById('cantidadPedido').value = (1000 * (batch / pesoMillar)).toFixed(2);
    calcularPrecioVentaEdit();
}
function calcularPrecioVenta() {
    var precioUnitario = document.getElementById('precio').value;
    var cantidadPedido = document.getElementById('cantidadPedido').value;
    document.getElementById('precioVenta').value = formatCurrency(parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    precioVenta = (parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    console.log("calcularPrecioVenta()");
    getCostoTotalReferenciaSinMargen();
}
function calcularPrecioVentaEdit() {
    var precioUnitario = document.getElementById('precio').value;
    var cantidadPedido = document.getElementById('cantidadPedido').value;
    document.getElementById('precioVenta').value = formatCurrency(parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    precioVenta = (parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    console.log("calcularPrecioVenta()");
    getCostoTotalReferencia();
}
function calcularPrecioVentaMargen() {
    var precioUnitario = document.getElementById('precio').value;
    var cantidadPedido = document.getElementById('cantidadPedido').value;
    document.getElementById('precioVenta').value = formatCurrency(parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    precioVenta = (parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    getCostoTotalReferencia();
}
function calcularPrecioVentaSinMargen() {
    var precioUnitario = document.getElementById('precio').value;
    var cantidadPedido = document.getElementById('cantidadPedido').value;
    document.getElementById('precioVenta').value = formatCurrency(parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    precioVenta = (parseFloat(precioUnitario) * parseFloat(cantidadPedido));
    console.log("calcularPrecioVentaSinMargen()");
    getCostoTotalReferenciaSinMargen();
}
function calcularPrecioVentaACC() {
    var precioUnitario = document.getElementById('precio').value;
    var costosACC = document.getElementById('costosACC').value = "" ? "0" : document.getElementById('costosACC').value;
    document.getElementById('precioVentaAcc').value = (parseFloat(precioUnitario) + parseFloat(costosACC)).toFixed(2);
}
function traePesoMillar()
{
    var datosCliente = document.getElementById('listaCliente2').value.split(',');
    var ficha = document.getElementById('ficha').value;
    document.getElementById('pesoMillar').disabled = true;
    $('#pesoMillar').val("0");
    document.getElementById('btnPesoSpinner2').hidden = false;
    $.post('CotizacionServletRef', {xopcion: 'traerPeso', idCliente: datosCliente[0], ficha: ficha}, function (e) {
        $('#pesoMillar').val(parseFloat(e.toString()));
        document.getElementById('pesoMillar').hidden = false;
        document.getElementById('btnPesoSpinner2').hidden = true;
        document.getElementById('pesoMillar').disabled = false;
        calcularCantidadNewRef();
    });
}
function colocarCosto()
{
    var selectProceso = document.getElementById('listaProceso');
    var indice = selectProceso.selectedIndex;
    if (indice != 0) {
        document.getElementById('btnAgregarProceso').disabled = false;
        var datosProceso = selectProceso.value.split(',');
        $('#costoProceso').val(datosProceso[2]);
    } else {

        document.getElementById('btnAgregarProceso').disabled = true;
        document.getElementById('costoProceso').value = 0;
    }
}
function colocarCostoAcc()
{
    var datos = document.getElementById('listaAccesorios').value.split(',');
    $('#costoAccesorio').val(datos[2]);
}
function traerUnRef(idPLU)
{

    var datosCliente = document.getElementById('listaCliente2').value.split(',');
    document.getElementById('nombreCliente2').value = datosCliente[1];
    $.post('CotizacionServletRef', {xopcion: 'ListaEditar', idPLU: idPLU}, function (e) {
        var edicion = true;
        var cadenaArray = e.split(',');
        var i = 0;
        $('#nombre').val(cadenaArray[i++]);
        $('#ancho').val(cadenaArray[i++]);
        $('#largo').val(cadenaArray[i++]);
        $('#calibre').val(cadenaArray[i++]);
        $('#referencia').val(cadenaArray[i++]);
        $('#pesoMillar').val(cadenaArray[i++]);
        $('#ficha').val(cadenaArray[i++]);
        $('#bache').val(cadenaArray[i++]);
        $('#retal').val(cadenaArray[i++]);
        $('#idPlu').val(cadenaArray[i++]);
        $('#flete').val(cadenaArray[i++]);
        $('#precio').val(cadenaArray[i++]);
        $('#material').val(cadenaArray[i++]);
        $('#observacion').val(cadenaArray[i++]);
        listaProcesoEdit();
        listaMateriaEdit();
        listaAccEdit();
        $('#modal-ref-cliente').on('shown.bs.modal', function () {
            if (edicion)
            {
                document.getElementById('listaRefHistorico').hidden = true;
            }
            edicion = false;
            $('#campoRazonSocial2').focus();
            refrescarCostoFleteEdit();
        });
    });
}

function listaProcesoEdit()
{
    var idPlu = document.getElementById('idPlu').value;
    $.post('CotizacionServletRef', {xopcion: 'listaProcesoEdit', idPlu: idPlu
    }, function (e) {
        $('#detalleProcesos').html(e);
        impCostoProcEdit();
    });
}
function removerMateria(idMateria)
{
    var bache = document.getElementById('bache').value;
    var retal = document.getElementById('retal').value;
    $.post('CotizacionServletRef', {xopcion: 'removerMateria', idMateria: idMateria, bache: bache, retal: retal
    }, function (e) {
        $('#detalleMaterias').html(e);
        impCostoMP();
    });
}
function removerProceso(id)
{

    $.post('CotizacionServletRef', {xopcion: 'removerProceso', idProceso: id
    }, function (e) {
        $('#detalleProcesos').html(e);
        impCostoProc()();
    });
}
function removerAccesorio(id)
{

    $.post('CotizacionServletRef', {xopcion: 'removerAccesorio', id: id
    }, function (e) {
        $('#detalleAccesorios').html(e);
        impCostoAccEdit();
    });
}
function listaMateriaEdit()
{
    var idPlu = document.getElementById('idPlu').value;
    $.post('CotizacionServletRef', {xopcion: 'listaMateriaEdit', idPlu: idPlu
    }, function (e) {
        $('#detalleMaterias').html(e);
        impCostoMPEdit();
    });
}
function listaAccEdit()
{
    var idPlu = document.getElementById('idPlu').value;
    $.post('CotizacionServletRef', {xopcion: 'listaAccEdit', idPlu: idPlu
    }, function (e) {
        $('#detalleAccesorios').html(e);
        impCostoAccEdit();
    });
}
function colocarReferencia()
{
    var datosRef = document.getElementById('selRefHistorico').value.split(',');
    var i = 0;
    document.getElementById('ficha').value = datosRef[i++];
    document.getElementById('nombre').value = datosRef[i++];
    document.getElementById('ancho').value = datosRef[i++];
    document.getElementById('largo').value = datosRef[i++];
    document.getElementById('calibre').value = datosRef[i++];
    document.getElementById('referencia').value = datosRef[i++];
    traePesoMillar();
}
function colocarCliente()
{
    $('#idPlu').val("0");
    document.getElementById('listaRefHistorico').hidden = false;
    document.getElementById('ficha').value = "";
    document.getElementById('nombre').value = "";
    document.getElementById('ancho').value = "";
    document.getElementById('largo').value = "";
    document.getElementById('calibre').value = "";
    document.getElementById('pesoMillar').value = "";
    document.getElementById('referencia').value = "";
    document.getElementById('retal').value = "3";
    document.getElementById('flete').value = "0";
    document.getElementById('precioVentaAcc').value = "0";
    document.getElementById('precioVenta').value = "0";
    document.getElementById('costoTotal').value = "0";
    document.getElementById('cantidadPedido').value = "0";
    document.getElementById('costosMP').value = "0";
    document.getElementById('costosProcesos').value = "0";
    document.getElementById('costosACC').value = "0";
    document.getElementById('costosFlete').value = "0";
    document.getElementById('precio').value = "100";
    document.getElementById('material').value = "";
    document.getElementById('observacion').value = "";
    var datosCliente = document.getElementById('listaCliente2').value.split(',');
    document.getElementById('nombreCliente2').value = datosCliente[1];
    limpiarProceso();
    limpiarMaterias();
    limpiarAccesorios();
    if (cacheIdCliente !== datosCliente[0])
    {
        document.getElementById('listaRefHistorico').hidden = true;
        document.getElementById('listaRefHistoricoEspera').hidden = false;
        cacheIdCliente = datosCliente[0];
        $.post('CotizacionServletRef', {xopcion: 'listaHistorico',
            idCliente: datosCliente[0]
        }, function (e) {


            if (e == 'nulo') {
                document.getElementById('nombre').focus();
                document.getElementById('listaRefHistoricoEspera').hidden = true;
                document.getElementById('listaRefHistorico').hidden = true;
            } else {
                document.getElementById('nombre').focus();
                document.getElementById('listaRefHistoricoEspera').hidden = true;
                document.getElementById('listaRefHistorico').hidden = false;
                $('#listaRefHistorico').html(e);
            }

        });
    } else {

        document.getElementById('listaRefHistoricoEspera').hidden = true;
        document.getElementById('listaRefHistorico').hidden = false;
        $('#modal-ref-cliente').on('shown.bs.modal', function () {
            document.getElementById('nombre').focus();
        });
    }
}

function buscarRefsCliente() {
    document.getElementById('btnBuscarSpinner').hidden = false;
    document.getElementById('btnBuscarRefsHistorico').hidden = true;
    var datosCliente = document.getElementById('listaCliente2').value.split(',');
    $.post('CotizacionServletRef', {xopcion: 'listaHistorico',
        idCliente: datosCliente[0]
    }, function (e) {


        if (e == 'nulo') {
            document.getElementById('nombre').focus();
            document.getElementById('listaRefHistoricoEspera').hidden = true;
            document.getElementById('listaRefHistorico').hidden = true;
        } else {
            document.getElementById('nombre').focus();
            document.getElementById('listaRefHistoricoEspera').hidden = true;
            document.getElementById('listaRefHistorico').hidden = false;
            $('#listaRefHistorico').html(e);
        }
        document.getElementById('btnBuscarSpinner').hidden = true;
        document.getElementById('btnBuscarRefsHistorico').hidden = false;
    });
}

function saveReferencia()
{
    var datosCliente = document.getElementById('listaCliente2').value.split(',');
    var idCliente = datosCliente[0];
    var nombre = document.getElementById('nombre').value;
    var ancho = document.getElementById('ancho').value;
    var largo = document.getElementById('largo').value;
    var calibre = document.getElementById('calibre').value;
    var referencia = document.getElementById('referencia').value;
    var pesoMillar = document.getElementById('pesoMillar').value;
    var ficha = document.getElementById('ficha').value;
    var bache = document.getElementById('bache').value;
    var retal = document.getElementById('retal').value;
    var idPlu = document.getElementById('idPlu').value;
    var flete = document.getElementById('flete').value;
    var precio = document.getElementById('precio').value;
    var material = document.getElementById('material').value;
    var observacion = document.getElementById('observacion').value;
    var precioVenta = document.getElementById('precioVentaAcc').value;
    $.post('CotizacionServletRef', {xopcion: 'crear',
        idCliente: idCliente, nombre: nombre,
        ancho: ancho, largo: largo, calibre: calibre,
        referencia: referencia, pesoMillar: pesoMillar,
        ficha: ficha, bache: bache, retal: retal, idPlu: idPlu, flete: flete, precio: precio, precioVenta: precioVenta, material: material, observacion: observacion
    }, function (e) {
        $('#detalleReferencia').html(e);
        $('.toast').toast('show');
    });
}
function limpiarProceso()
{
    document.getElementById('listaProceso').value = 0;
    document.getElementById('costosProcesos').value = 0;
    document.getElementById('costoProceso').value = 0;
    $.post('CotizacionServletRef', {xopcion: 'limpProcesos'

    }, function (e) {
        $('#detalleProcesos').html(e);
    });
}
function limpiarMaterias()
{
    document.getElementById('listaMaterias').value = 0;
    document.getElementById('cantMP').value = 1;
    $.post('CotizacionServletRef', {xopcion: 'limpMaterias'

    }, function (e) {
        $('#detalleMaterias').html(e);
    });
}
function limpiarAccesorios()
{
    document.getElementById('listaAccesorios').value = 0;
    document.getElementById('costoAccesorio').value = 0;
    $.post('CotizacionServletRef', {xopcion: 'limpAcc'

    }, function (e) {
        $('#detalleAccesorios').html(e);
    });
}
function agregarMateria()
{
    var datos = document.getElementById('listaMaterias').value.split(',');
    var id = datos[0];
    var nombre = datos[1];
    var costo = datos[2];
    var cantMP = document.getElementById('cantMP').value;
    var bache = document.getElementById('bache').value;
    var retal = document.getElementById('retal').value;
    ;
    $.post('CotizacionServletRef', {xopcion: 'aggMateria',
        id: id, nombre: nombre, cant: cantMP, bache, retal: retal, costo: costo
    }, function (e) {
        $('#detalleMaterias').html(e);
        impCostoMP();
    });
}


function agregarProceso()
{
    var datos = document.getElementById('listaProceso').value.split(',');
    var idPlu = document.getElementById('idPlu').value;
    var id = datos[0];
    var nombre = datos[1];
    var costo = datos[2];
    var cantidad = document.getElementById('cantProceso').value;
    $.post('CotizacionServletRef', {xopcion: 'aggProceso',
        id: id, nombre: nombre, cant: cantidad, costo: costo, idPlu: idPlu
    }, function (e) {
        $('#detalleProcesos').html(e);
        impCostoProc();
        document.getElementById('btnAgregarProceso').disabled = true;
    });
}
function agregarAccesorio()
{
    var datos = document.getElementById('listaAccesorios').value.split(',');
    var id = datos[0];
    var nombre = datos[1];
    var costo = document.getElementById('costoAccesorio').value;
    $.post('CotizacionServletRef', {xopcion: 'aggAccesorio',
        id: id, nombre: nombre, costo: costo
    }, function (e) {
        $('#detalleAccesorios').html(e);
        impCostoAcc();
    });
}
function impCostoMP()
{


    $.post('CotizacionServletRef', {xopcion: 'printCostMP'
    }, function (e) {
        var costMP = parseFloat(e).toFixed(2);
        $('#costosMP').val(costMP);
        console.log("impCostoMP()");
        getCostoTotalReferenciaSinMargen();
    });
}
function impCostoMPEdit()
{
    $.post('CotizacionServletRef', {xopcion: 'printCostMP'
    }, function (e) {
        var costMP = parseFloat(e).toFixed(2);
        $('#costosMP').val(costMP);
        console.log("impCostoMP()");
        calcularCantidadEdit();
    });
}
function impCostoProc()
{
    $.post('CotizacionServletRef', {xopcion: 'printCostProc'
    }, function (e) {
        var costProc = parseFloat(e).toFixed(2);
        $('#costosProcesos').val(costProc);
        console.log(" impCostoProc()");
        getCostoTotalReferenciaSinMargen(); //No calcula margen sino precio
    });
}
function impCostoProcEdit()
{
    $.post('CotizacionServletRef', {xopcion: 'printCostProc'
    }, function (e) {
        var costProc = parseFloat(e).toFixed(2);
        $('#costosProcesos').val(costProc);
        calcularCantidadEdit();
    });
}
function impCostoAcc()
{
    $.post('CotizacionServletRef', {xopcion: 'printCostAcc'
    }, function (e) {
        var costo = parseFloat(e).toFixed(2);
        $('#costosACC').val(costo);
        console.log("  impCostoAcc()");
        getCostoTotalReferenciaSinMargen();
    });
}
function impCostoAccEdit()
{
    $.post('CotizacionServletRef', {xopcion: 'printCostAcc'
    }, function (e) {
        var costo = parseFloat(e).toFixed(2);
        $('#costosACC').val(costo);
        calcularCantidadEdit();
    });
}
function colocarCantProceso()
{
    var batch = document.getElementById('bache').value;
    var retal = document.getElementById('retal').value;
    document.getElementById('cantProceso').value = batch * (1 + retal / 100);
}
function getCostoTotalReferencia()
{
    document.getElementById('costoTotal').value = (parseFloat(document.getElementById('costosMP').value) + parseFloat(document.getElementById('costosProcesos').value) + (parseFloat(document.getElementById('flete').value) * parseFloat(document.getElementById('bache').value))).toFixed(2);
    var margenBruto = ((parseFloat(precioVenta) - parseFloat(document.getElementById('costoTotal').value)) * 100 / parseFloat(precioVenta)).toFixed(2);
    document.getElementById('margen').value = margenBruto;
    calcularPrecioVentaACC();
}
function getCostoTotalReferenciaSinMargen()
{
    var cantidadPedido = document.getElementById('cantidadPedido').value;
    var costoTotal = (parseFloat(document.getElementById('costosMP').value) + parseFloat(document.getElementById('costosProcesos').value) + parseFloat(document.getElementById('flete').value) * parseFloat(document.getElementById('bache').value)).toFixed(2);
    document.getElementById('costoTotal').value = costoTotal;
    var costo = parseFloat(costoTotal);
    var margen = parseFloat(document.getElementById('margen').value);
    document.getElementById('precio').value = (100 * costo / (100 - margen) / cantidadPedido).toFixed(2);
    calcularPrecioVentaACC();
    calcularPrecioVentaMargen();
}


function listRefs()
{
    var datosCliente = document.getElementById('listaCliente2').value.split(',');
    var idCliente = datosCliente[0];
    if (idCliente == 0) {
        document.getElementById('botonNuevoRef').disabled = true;
    } else {
        document.getElementById('botonNuevoRef').disabled = false;
    }
    $.post('CotizacionServletRef', {xopcion: 'lista', idCliente: idCliente}, function (e) {
        $('#detalleReferencia').html(e);
    });
}
function listRefsRevenue()
{
  var idCliente = document.getElementById('listaClientes4').value;
    
    $.post('CotizacionServletRef', {xopcion: 'listaReportRevenue', idCliente: idCliente}, function (e) {
        $('#reporteMargenes').html(e);
    });
}

function seleccionarCliente()
{
    var datosCliente = document.getElementById('listaCliente').value.split(',');
    document.getElementById('campoRazonSocial').value = datosCliente[1];
    document.getElementById('campoNit').value = datosCliente[0];
    document.getElementById('campoContacto').value = datosCliente[2];
    if (datosCliente[0] == '0')
    {
        document.getElementById('campoContacto').disabled = false;
        document.getElementById('campoRazonSocial').disabled = false;
        document.getElementById('campoNit').disabled = false;
        document.getElementById('campoRazonSocial').value = '';
        document.getElementById('campoNit').value = '';
        document.getElementById('campoContacto').value = '';
        document.getElementById('campoNit').focus();
        document.getElementById('customSwitch1').checked = true;
    } else {
        document.getElementById('campoContacto').disabled = true;
        document.getElementById('campoRazonSocial').disabled = true;
        document.getElementById('campoNit').disabled = true;
        document.getElementById('customSwitch1').checked = false;
    }

}
function nuevoCliente()
{
    document.getElementById('listaCliente').value = '0';
    if ($('#customSwitch1').prop('checked')) {
        document.getElementById('campoRazonSocial').disabled = false;
        document.getElementById('campoNit').disabled = false;
        document.getElementById('campoContacto').disabled = false;
        document.getElementById('campoRazonSocial').value = '';
        document.getElementById('campoContacto').value = '';
        document.getElementById('campoNit').value = '';
        document.getElementById('campoNit').focus();
        document.getElementById('customSwitch1').checked = true;
    } else {
        document.getElementById('campoRazonSocial').disabled = true;
        document.getElementById('campoNit').disabled = true;
        document.getElementById('campoContacto').disabled = true;
    }

}
function saveCliente()
{

    var razon = document.getElementById('campoRazonSocial').value;
    var nit = document.getElementById('campoNit').value;
    var contacto = document.getElementById('campoContacto').value;
    $.post('CotizacionServlet', {xopcion: 'crear', nit: nit, razon: razon, contacto: contacto}, function (e) {
        $('#detalleCliente').html(e);
        document.getElementById('listaCliente').value = '0';
        document.getElementById('campoContacto').value = '';
        document.getElementById('campoRazonSocial').disabled = true;
        document.getElementById('campoNit').disabled = true;
        document.getElementById('campoRazonSocial').value = '';
        document.getElementById('campoNit').value = '';
        document.getElementById('campoNit').focus();
        document.getElementById('customSwitch1').checked = false;
    });
}
function updateCliente()
{

    var razon = document.getElementById('campoRazonSocial2').value;
    var nit = document.getElementById('campoNit2').value;
    var contacto = document.getElementById('campoContacto2').value;
    $.post('CotizacionServlet', {xopcion: 'Actualizar', nit: nit, razon: razon, contacto: contacto}, function (e) {
        $('#detalleCliente').html(e);
    });
}
function listClients()
{

    $.post('CotizacionServlet', {xopcion: 'lista'}, function (e) {
        $('#detalleCliente').html(e);
    });
    colocarCantProceso();
    printHistorial();

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
function printListaClientesModal()
{
    var nombre = document.getElementById("nombreEmpresa").value;
    $.post('CotizacionServlet', {xopcion: 'listaModal', nombre: nombre}, function (e) {
        $('#tablaModalClientes').html(e);
        primerCliente();
    });
}
function primerCliente() {

    var miTabla = document.getElementById("tablaClientesModal");
    var mitbody = miTabla.getElementsByTagName("tbody")[0];
    var miFila = mitbody.getElementsByTagName("tr")[0];
    var miCelda = miFila.getElementsByTagName("td")[1].innerHTML;
    datosSel = miCelda;

}
function printListaClientesModalEnter(e)
{
    var tecla = (document.all) ? e.keyCode : e.which;
//    if (tecla == 13) {
    printListaClientesModal();
//    }
}



function editarCliente(nit) {
    $.post('CotizacionServlet', {xopcion: 'ListaEditar', nit: nit}, function (e) {
        var cadenaArray = e.split(',');
        $('#campoNit2').val(cadenaArray[0]);
        $('#campoRazonSocial2').val(cadenaArray[1]);
        $('#campoContacto2').val(cadenaArray[2]);
        $('#modal-act-cliente').on('shown.bs.modal', function () {
            $('#campoRazonSocial2').focus();
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

function colocarEspera(_i) {
    document.getElementById('spinnerPDF' + _i).hidden = false;
    document.getElementById('botonPDF' + _i).hidden = true;
}


function consultarHistoricoTeclado(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        consultarHistorico();
    }
}
//muestra carga cuando se imprime PDF en boton de portal principal
function mostrarCarga() {
    document.getElementById('botonImprimirPDF').hidden = true;
    document.getElementById('botonImprimirPDFSpinner').hidden = false;
}
function formatCurrency(num)
{
// Se limpia la cadena
    num = num.toString().replace(/$|,/g, '');
//validamos que se numero
    if (isNaN(num))
        num = "0";
// se busca signo (pues hay perdidas osea dinero negativo
    sign = (num == (num = Math.abs(num)));
//los centavos se hacen enteros y se redondea hacia arriba
    num = Math.floor(num * 100 + 0.50000000001);
// modulo 100 para ver cuantos centavos son
    cents = num % 100;
//se regresa a enteros 
    num = Math.floor(num / 100).toString();
// se da formato
    if (cents < 10)
        cents = "0" + cents;
//se ponen las comas cada 3 posiciones
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + '.' +
                num.substring(num.length - (4 * i + 3));
//se estructura la cadena que regresa incluye signo de negativo, signo de pesos, entero, punto, centavos
//    return (((sign) ? '' : '-') + '$' + num + '.' + cents);
    return (((sign) ? '' : '-') + num);
}


function printHistorial() {

    var idCliente = document.getElementById('listaclientes3').value.split(',')[0];
    var fechaInicial = document.getElementById('datepicker').value;
    var fechaFinal = document.getElementById('datepicker2').value;

    $.post('CotizacionServletRef', {xopcion: 'printHistDct', idCliente: idCliente, fechaInicial: fechaInicial, fechaFinal: fechaFinal}, function (e) {
        $('#historialDctos').html(e);

    });

}

