/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function crear()
{//Permite crear una orden nueva para apartar dicho nuemero y no interrumpir a otros usuarios
    $.post('TrasladoInventarioServlet', {xopcion: 'crear'}, function (e) {
        var cadenaArray = e.split(',');
        $('#campoNumeroOrden').val(cadenaArray[0]);//Se imprime el numero de orden unico
        $('#botonImprimirPDF').val(cadenaArray[0]);//Se imprime el numero de orden en el boton de imprimir para que ese valor sea el evaluado cuando se imprima el PDF
        $('#campoNumeroDocumento').val(cadenaArray[1]);//Se imprime el numero del documento para el usuario

        //Activamos todos los elementos de la UI que el usuario usara, ademas de limpiar los campos 
        document.getElementById('ordenOrigen').disabled = false;
        document.getElementById('ordenDestino').disabled = false;
        document.getElementById('botonBuscarOrden2').disabled = false;
        document.getElementById('botonBuscarOrden').disabled = false;
        document.getElementById('ordenOrigen').focus();
        document.getElementById('ControlTextarea').value = "";
        document.getElementById('ControlTextarea').disabled = false;
        document.getElementById('botonNuevo').disabled = true;
        document.getElementById('ordenOrigen').value = "";
        document.getElementById('ordenDestino').value = "";
        consultarHistorico();//Imprimimos esa orden en el historico vacia
    });
}

//Permite buscar el nombre del producto dado por el cliente y colocarlo en los datos de la orden origen
function colocarReferencia()
{
    var idOrden = document.getElementById('ordenOrigen').value;
    $.post('TrasladoInventarioServlet', {xopcion: 'referenciaCliente', ordenOrigen: idOrden}, function (e) {
        var cadenaArray = e.split(',');
        $('#campoNombreProducto').val(cadenaArray[0]);
    });
}
//Permite buscar el nombre del producto dado por el cliente y colocarlo en los datos de la orden destino
function colocarReferenciaDestino()
{
    var idOrden = document.getElementById('ordenDestino').value;
    $.post('TrasladoInventarioServlet', {xopcion: 'referenciaCliente', ordenOrigen: idOrden}, function (e) {
        var cadenaArray = e.split(',');
        $('#campoNombreProducto2').val(cadenaArray[0]);
    });
}
//Permite formatear un numero para facil compresion al usuario
function format(num)
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
    return (((sign) ? '' : '-') + num);
}

//Busca PLU de Orden de Origen mediante presion de tecla Enter
function buscarOrden(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        document.getElementById('spinner2').hidden = false;
        colocarReferencia();
        var idOrdenOrigen = document.getElementById('ordenOrigen').value;
        $.post('TrasladoInventarioServlet', {xopcion: 'buscarOrden', ordenOrigen: idOrdenOrigen},
                function (e) {
                    $('#detalle').html(e);
                    document.getElementById('ordenDestino').focus();
                    $('#collapse1.panel-collapse').collapse('show');
                    document.getElementById('spinner2').hidden = true;
                });
    }
}
//Busca PLU de Orden de Origen mediante presion boton de busqueda
function buscarOrdenBoton()
{
    document.getElementById('spinner2').hidden = false;

    colocarReferencia();
    var idOrdenOrigen = document.getElementById('ordenOrigen').value;
    $.post('TrasladoInventarioServlet', {xopcion: 'buscarOrden', ordenOrigen: idOrdenOrigen},
            function (e) {
                $('#detalle').html(e);
                document.getElementById('ordenDestino').focus();
                $('#collapse1.panel-collapse').collapse('show');
                document.getElementById('spinner2').hidden = true;

            });
}
//Busca PLU de Orden de Destino mediante presion de tecla Enter
function buscarOrden2(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        document.getElementById('spinner3').hidden = false;
        colocarReferenciaDestino();
        var idOrdenDestino = document.getElementById('ordenDestino').value;
        var idOrdenTraslado = document.getElementById('campoNumeroOrden').value;
        $.post('TrasladoInventarioServlet', {xopcion: 'buscarOrdenDestino', ordenDestino: idOrdenDestino, idOrdenTraslado: idOrdenTraslado},
                function (e) {
                    $('#detalle2').html(e);
                    $('#collapse2.panel-collapse').collapse('show');
                    document.getElementById('spinner3').hidden = true;
                });
    }
}
//Busca PLU de Orden de Destino mediante presion de boton de busqueda
function buscarOrdenBoton2() {
    document.getElementById('spinner3').hidden = false;
    colocarReferenciaDestino();
    var idOrdenDestino = document.getElementById('ordenDestino').value;
    var idOrdenTraslado = document.getElementById('campoNumeroOrden').value;
    $.post('TrasladoInventarioServlet', {xopcion: 'buscarOrdenDestino', ordenDestino: idOrdenDestino, idOrdenTraslado: idOrdenTraslado},
            function (e) {
                $('#detalle2').html(e);
                $('#collapse2.panel-collapse').collapse('show');
                document.getElementById('spinner3').hidden = true;
            });
}
//Permite retirar un PLU que el usuario no desea trasladar
function borrarPLU()
{
    var item = document.getElementById('itemB').value;
    var plu = document.getElementById('pluB').value;
    var operacion = document.getElementById('operacionB').value;
    var ordenTraslado = document.getElementById('campoNumeroOrden').value;
    var id = document.getElementById('botonBorrarCampo').value;
    document.getElementById('botonBorrar' + id).hidden = true;
    document.getElementById('botonSpinnerBorrar' + id).hidden = false;
    $.post('TrasladoInventarioServlet', {xopcion: 'borrarPLU', ordenTraslado: ordenTraslado, item: item, plu: plu, operacion: operacion}, function (e) {
        buscarOrdenBoton2();
    });
}

function guardarBorrar(_i, _item, _operacion, _plu)
{
    document.getElementById('itemB').value = _item;
    document.getElementById('pluB').value = _plu;
    document.getElementById('operacionB').value = _operacion;
    document.getElementById('botonBorrarCampo').value = _i;
}
//Permite rescatar una orden ya previamente creada que nunca fue completada.
function editarOrden(_i, _orden, _documento) {
    document.getElementById('botonEditarOrden' + _i).hidden = true;
    document.getElementById('spinnerEditar' + _i).hidden = false;
    $('#campoNumeroDocumento').val(_documento);
    $('#campoNumeroOrden').val(_orden);
    document.getElementById('ordenOrigen').disabled = false;
    document.getElementById('ordenDestino').disabled = false;
    document.getElementById('botonBuscarOrden2').disabled = false;
    document.getElementById('botonBuscarOrden').disabled = false;
    document.getElementById('ordenOrigen').focus();
    document.getElementById('ControlTextarea').value = "";
    document.getElementById('ControlTextarea').disabled = false;
    document.getElementById('botonNuevo').disabled = true;
    document.getElementById('ordenOrigen').value = "";
    document.getElementById('ordenDestino').value = "";
    document.getElementById('botonImprimirPDF').value = _orden;
    buscarOrdenBoton();
    buscarOrdenBoton2();
    $.post('TrasladoInventarioServlet', {xopcion: 'borrarPLUS', orden: _orden}, function (e) {
        document.getElementById('botonEditarOrden' + _i).hidden = false;
        document.getElementById('spinnerEditar' + _i).hidden = true;
    });
}

//Permite traer un PLU al modal para realizar la precarga del traslado
function editar(_id, _idOperacion, _idBoton) {
    var idOrden = document.getElementById('ordenOrigen').value;
    $.post('TrasladoInventarioServlet', {xopcion: 'cargarPLU', item: _id, orden: idOrden, idOperacion: _idOperacion}, function (e) {
        var cadenaArray = e.split(',');
        $('#xItem').val(cadenaArray[0]);
        $('#xPLU').val(cadenaArray[1]);
        $('#nombreProducto').val(cadenaArray[2]);
        $('#campoCantidad').val(cadenaArray[3]);
        $('#campoPeso').val(cadenaArray[4]);
        $('#xIdOp').val(cadenaArray[5]);
        $('#xCampoBoton').val(_idBoton);
        $('#exampleModal').on('shown.bs.modal', function () {
            var cantidad = document.getElementById("campoCantidad");
            var peso = document.getElementById("campoPeso");
            cantidad.setAttribute('max', cadenaArray[3]);
            peso.setAttribute('max', cadenaArray[4]);
            cantidad.focus();
        });
    });
}
//Realiza el movimiento del PLU con la tecla Enter
function moverEnter(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        moverItem();
        $('#exampleModal').modal('hide');
    }
}
//Ejecuta en detalle el movimiento del PLU
function moverItem() {
    var item = document.getElementById('xItem').value;
    var idOrden = document.getElementById('ordenOrigen').value;
    var idOrdenDestino = document.getElementById('ordenDestino').value;
    var idPLU = document.getElementById('xPLU').value;
    var cantidad = document.getElementById('campoCantidad').value;
    var peso = document.getElementById('campoPeso').value;
    var idOperacion = document.getElementById('xIdOp').value;
    var idOrdenTraslado = document.getElementById('campoNumeroOrden').value;
    var id = document.getElementById('xCampoBoton').value;
    var campoNombreProducto = document.getElementById('campoNombreProducto').value;
    var botonSeleccionado = document.getElementById('botonTraslado' + id);
    var tabla = document.getElementById("dataTable");
    var i = parseInt(id);
    botonSeleccionado.hidden = true;
    document.getElementById('botonTrasladoSpinner' + id).hidden = false;

    $.post('TrasladoInventarioServlet', {xopcion: 'moverPLU', item: item, orden: idOrden,
        idPLU: idPLU, cantidad: cantidad, peso: peso, idOperacion: idOperacion, idOrdenTraslado: idOrdenTraslado, idOrdenDestino: idOrdenDestino, campoNombreProducto: campoNombreProducto}, function (e) {
        buscarOrdenBoton2();
        document.getElementById('botonEjecutarTraslado').disabled = false;//activamos el boton de trasladar
        tabla.rows[i + 1].cells[5].innerText = format(parseFloat(tabla.rows[i + 1].cells[5].innerText) - parseFloat(peso));//colocamos el resultado de la resta
        tabla.rows[i + 1].cells[4].innerText = format(parseFloat(tabla.rows[i + 1].cells[4].innerText) - parseFloat(cantidad));//colocamos el resultado de la resta
        document.getElementById('ordenOrigen').disabled = true;
        document.getElementById('ordenDestino').disabled = true;
        document.getElementById('botonTrasladoSpinner' + id).hidden = true;
        botonSeleccionado.hidden = false;
        botonSeleccionado.setAttribute('disabled', false);
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
function ejecutarTraslado() {
    document.getElementById('spinner').hidden = false;
    document.getElementById('botonEjecutarTraslado').disabled = true;
    var idOrdenTraslado = document.getElementById('campoNumeroOrden').value;
    var idOrdenOrigen = document.getElementById('ordenOrigen').value;
    var idOrdenDestino = document.getElementById('ordenDestino').value;
    var observaciones = document.getElementById('ControlTextarea').value;
    $.post('TrasladoInventarioServlet', {xopcion: 'ejecutarTraslado',
        idOrdenTraslado: idOrdenTraslado, idOrdenOrigen: idOrdenOrigen,
        idOrdenDestino: idOrdenDestino, observaciones: observaciones}, function (e) {
        $('#alertas').html(e);
        document.getElementById('botonImprimirPDF').disabled = false;
        document.getElementById('spinner').hidden = true;
        document.getElementById('ordenDestino').disabled = true;
        document.getElementById('ordenOrigen').disabled = true;
        document.getElementById('botonNuevo').disabled = false;
        consultarHistorico();
    });
}

function colocarEspera(_i) {
    document.getElementById('spinnerPDF' + _i).hidden = false;
    document.getElementById('botonPDF' + _i).hidden = true;
}
function consultarHistorico() {

    var fechaInicial = document.getElementById('datepicker').value;
    var fechaFinal = document.getElementById('datepicker2').value;
    var ordenBusqueda = document.getElementById('ordenBusqueda').value;
    var tipoDoc = document.getElementById('tipoDoc').value;
    $.post('TrasladoInventarioServlet', {xopcion: 'printHistorial', fechaInicial: fechaInicial, fechaFinal: fechaFinal, ordenBusqueda: ordenBusqueda, tipoDoc: tipoDoc}, function (e) {

        $('#historial').html(e);
        document.getElementById('botonBuscarSpinner').hidden = true;
        document.getElementById('botonBuscarTraslado').hidden = false;
    });

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