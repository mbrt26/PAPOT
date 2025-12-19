/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/* global campoNit */


function buscarDatosProv(_tipoOperacion) {
    var par = document.getElementById('listaSelProveedor').value;
    var tipo = _tipoOperacion;
    document.getElementById('campoNitProveedor').value = par;
    var fechaOrden = document.getElementById('selectorFechaOrden').value;
    $.post('OrdenCompraServlet', {xopcion: 'buscarDatosProveedor', parametro: par, fechaOrden: fechaOrden}, function (e) {
        $('#detalleProveedor').html(e);
        if (parseInt(par) != 0)
        {
            configurarCalendario("selectorEntrega", "%Y/%m/%d", "boton3");
            configurarCalendario("selectorVencimiento", "%Y/%m/%d", "boton2");
        }
        buscarDetalleProductos(tipo);
//        guardarOC('1');
    });


}
function buscarDatosProvSencillo() {
    var par = document.getElementById('listaSelProveedor').value;
    document.getElementById('campoNitProveedor').value = par;
    var fechaOrden = document.getElementById('selectorFechaOrden').value;
    $.post('OrdenCompraServlet', {xopcion: 'buscarDatosProveedor', parametro: par, fechaOrden: fechaOrden}, function (e) {
        $('#detalleProveedor').html(e);
//        configurarCalendario("selectorEntrega", "%Y/%m/%d", "boton3");
        configurarCalendario("selectorVencimiento", "%Y/%m/%d", "boton2");
    });
}
function buscarDatosProv2(_sk_tercero, _fechaOrden, _fecha_vencimiento) {
    var par = _sk_tercero;
    //   document.getElementById('campoNitProveedor').value = par;
//    var fechaOrden = document.getElementById('selectorFechaOrden').value;
    $.post('OrdenCompraServlet', {xopcion: 'buscarDatosProveedor', parametro: par,
        fechaOrden: _fechaOrden, fechaVencimiento: _fecha_vencimiento
    }, function (e) {
        $('#detalleProveedor').html(e);
        configurarFechas();
        refrescarAgregados();
    });
}

function buscarDetalleProductos(_tipoOp) {
    var par = document.getElementById('listaSelProveedor').value;
    var ord = document.getElementById('campoNumeroOrden').value;
    $.post('OrdenCompraServlet', {xopcion: 'actTablaDetalles', parametro: par, orden: ord, recuperar: _tipoOp}, function (e) {
        $('#detalleOrden1').html(e);
        buscarProductosLibres();
        sumar();
    });
}
function buscarProductosLibres() {
    var par = document.getElementById('listaSelProveedor').value;
    var ord = document.getElementById('campoNumeroOrden').value;
    $.post('OrdenCompraServlet', {xopcion: 'actTablaModal', parametro: par, orden: ord}, function (e) {
        $('#detalleModal').html(e);
    });
}
function configurarCalendario(inputField, format, boton) {
    Calendar.setup(
            {
                inputField: inputField,
                ifFormat: format,
                button: boton
            }
    );
}

function preguntaBorrar(_sk_plu)
{
    var mensaje;
    var opcion = confirm("¿Desea retirar este producto?");
    var ord = document.getElementById('campoNumeroOrden').value;
    if (opcion == true) {
        $.post('OrdenCompraServlet', {xopcion: 'borrarProducto', plu: _sk_plu, orden: ord}, function (e) {
            $('#detalleModal').html(e);
            refrescarAgregados();
            // buscarDetalleProductos('true');
        });
    } else {
        mensaje = "No";
    }
//	document.getElementById("ejemplo").innerHTML = mensaje;
}


function activarBotones() {
    document.getElementById('listaSelProveedor').disabled = false;
    document.getElementById('botonAgregarProducto').disabled = false;
    document.getElementById('botonGuardar').disabled = false;
}
function desactivarBotones() {
    document.getElementById('listaSelProveedor').disabled = true;
    document.getElementById('botonAgregarProducto').disabled = true;
    document.getElementById('botonGuardar').disabled = true;

}
function crearAjuste()
{   activarBotones();
    $.post('', {xopcion: 'crearAjuste'}, function (e) {
        var cadenaArray = e.split(',');
        $('#campoNumeroOrden').val(cadenaArray[0]);
        document.getElementById('listaSelProveedor').value = 0;
        document.getElementById('campoObservaciones').value = "";
        buscarDatosProv('true');
    });
}
function verificarMarcado()
{
    $(document).ready(function () {
        $('#edit input[type=checkbox]').each(function () {
            if (!this.checked) {
                document.getElementById("seleccionTotal").checked = false;
            }
        });
        return false;

    });
}
function marcarTodos()
{
    $(document).ready(function () {
        $('#edit input[type=checkbox]').each(function () {
            if (document.getElementById("seleccionTotal").checked) {
                this.checked = true;
            } else {
                this.checked = false;
            }
        });
        return false;
    });
}
function sumar() {
    var nFilas = $("#tablaDetalles tr").length;
    var filas = parseFloat(nFilas);
    filas -= 1;
    var totalIva = 0;
    var acum = 0;

    for (var i = 0; i < filas; i++) {
        var a1 = document.getElementById("campoPedido" + i).value;
        var a2 = document.getElementById("campoCostoNeg" + i).value;
        var a3 = document.getElementById("ivaProducto" + i).value;
        var a4 = document.getElementById("campoEmp" + i).value;
        var tmp = parseFloat(a1) / parseFloat(a4);
        tmp = Math.ceil(parseFloat(tmp));
        tmp = tmp * parseFloat(a4);
        a1 = tmp;
        document.getElementById("campoPedido" + i).value = a1;

        acum += (parseFloat(a1) * parseFloat(a2));

        totalIva += ((parseFloat(a1) * parseFloat(a2)) * parseFloat(a3)) / 100;
    }
    document.getElementById("campoNumArt").value = filas;
    document.getElementById("campoSubTotal").value = formatCurrency(acum);
    document.getElementById("campoIva").value = formatCurrency(totalIva);
    var total = acum + totalIva;
    document.getElementById("campoTotal").value = formatCurrency(total);

}
function sumar2() {

    var nFilas = $("#tablaDetalles tr").length;
    var filas = parseFloat(nFilas);
    filas -= 1;
    var totalIva = 0;
    var acum = 0;

    for (var i = 0; i < filas; i++) {
        var a1 = document.getElementById("campoPedido" + i).value;

        var a2 = document.getElementById("campoCostoNeg" + i).value;
        var a3 = document.getElementById("ivaProducto" + i).value;
        acum += (parseFloat(a1) * parseFloat(a2));
        totalIva += ((parseFloat(a1) * parseFloat(a2)) * parseFloat(a3)) / 100;
    }
    document.getElementById("campoNumArt").value = filas;
    document.getElementById("campoSubTotal").value = formatCurrency(acum);
    document.getElementById("campoIva").value = formatCurrency(totalIva);
    var total = acum + totalIva;
    document.getElementById("campoTotal").value = formatCurrency(total);

}
function configurarFechas() {
    configurarCalendario("selectorEntrega", "%Y/%m/%d", "boton3");
    configurarCalendario("selectorVencimiento", "%Y/%m/%d", "boton2");
}

function guardarOC(_mostrarNotificacion) {
    sumar();
    var xorden = document.getElementById('campoNumeroOrden').value;
    var xsk_tercero = document.getElementById('listaSelProveedor').value;
    var xfechaOrden = document.getElementById('selectorFechaOrden').value;
    var xfechaVencimiento = document.getElementById('selectorVencimiento').value;
    var xobservaciones = document.getElementById('campoObservaciones').value;
    var xsubtotal = document.getElementById('campoSubTotal').value;
    var xivaTotal = document.getElementById('campoIva').value;
    var xtotal = document.getElementById('campoTotal').value;
    var nFilas = $("#tablaDetalles tr").length;
    var filas = parseFloat(nFilas);
    filas -= 1;
    var xsk_plu = new Array(filas);
    var xcost = new Array(filas);
    var xcantidades = new Array(filas);
    var xivas = new Array(filas);
    for (var i = 0; i < filas; i++) {
        xsk_plu[i] = document.getElementById("skPLU" + i).value;
        xcantidades[i] = document.getElementById("campoPedido" + i).value;
        xcost[i] = document.getElementById("campoCostoNeg" + i).value;
    }
    $.post('OrdenCompraServlet', {xopcion: 'guardarOC', orden: xorden,
        sk_tercero: xsk_tercero, fechaOrden: xfechaOrden, fechaVencimiento: xfechaVencimiento,
        sk_plu: xsk_plu, costos: xcost, cantidades: xcantidades, ivas: xivas, observaciones: xobservaciones,
        subTotal: xsubtotal, ivaTotal: xivaTotal, total: xtotal}, function (e) {
        var avisar = parseInt(_mostrarNotificacion);
        if (avisar === 1)
            alert("Registros actualizados exitosamente");
        actOrdenes();
    });
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
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
                num.substring(num.length - (4 * i + 3));
//se estructura la cadena que regresa incluye signo de negativo, signo de pesos, entero, punto, centavos
//    return (((sign) ? '' : '-') + '$' + num + '.' + cents);
    return (((sign) ? '' : '-') + '$' + num);
}


function refrescarAgregados() {
    var selected = '';
    $('#edit input[type=checkbox]').each(function () {
        if (this.checked) {
            selected += $(this).val() + ', ';
        }
    });
    var ord = document.getElementById('campoNumeroOrden').value;
    var par = document.getElementById('listaSelProveedor').value;
    selected.split(',');
    $.post('OrdenCompraServlet', {xopcion: 'agregarPLU', orden: ord, codigosPLU: selected, recuperar: 'true', parametro: par},
            function (e) {
                $('#detalleOrden1').html(e);
                buscarProductosLibres();
                sumar();
            });
}
function actOrdenesVencidas() {
    $.post('OrdenCompraServlet', {xopcion: 'actTabVenc'},
            function (e) {
                $('#tablaVencida').html(e);
            });
}
function actOrdenes() {
    $.post('OrdenCompraServlet', {xopcion: 'actTabVig'},
            function (e) {
                $('#tablaVigente').html(e);
                actOrdenesVencidas();
            });
}


function preguntaBorrar1(_orden)
{

    var mensaje;
    var opcion = confirm("¿Desea retirar este regisro?");
    if (opcion == true) {
        $.post('OrdenCompraServlet', {xopcion: 'borrarOrden', orden: _orden}, function (e) {
            actOrdenes();
        });
    } else {
        mensaje = "No";
    }
}


function editarOrden(_orden, _sk_tercero) {
    document.getElementById('campoObservaciones').value = "";

    var condicional = _sk_tercero;
//    alert(condicional);
    if (condicional == '0') {
        document.getElementById('listaSelProveedor').disabled = false;
    } else {
        document.getElementById('listaSelProveedor').disabled = true;
    }
    document.getElementById('botonAgregarProducto').disabled = false;
    document.getElementById('botonGuardar').disabled = false;
    document.getElementById('campoNumeroOrden').value = _orden;
    document.getElementById('listaSelProveedor').value = _sk_tercero;
    document.getElementById('campoNitProveedor').value = _sk_tercero;
    buscarDatosEdicionOrden(_orden, _sk_tercero);
}
function buscarDatosEdicionOrden(_orden, _skTercero) {
    var fechas;
    $.post('OrdenCompraServlet', {xopcion: 'edicionOrden', parametro: _orden, sk_tercero: _skTercero}, function (e) {
        var datos = e.split(',');
        document.getElementById('selectorFechaOrden').value = datos[0];// se imprime la fecha de emision original de la OC
        document.getElementById('campoObservaciones').value = datos[2];// se coloca el datos string en el campo de observaciones
        buscarDatosProv2(_skTercero, datos[0], datos[1]);
        document.getElementById('SelectorVencimiento').value = fechas[1];
    });
}
