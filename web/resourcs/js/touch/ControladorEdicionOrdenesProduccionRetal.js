


function printListHistory() {

    document.getElementById('botonBuscarSpinner').hidden = false;
    document.getElementById('botonBuscarTraslado').hidden = true;
    var fechaInicial = document.getElementById('datepicker').value;
    var fechaFinal = document.getElementById('datepicker2').value;
    var idProceso = document.getElementById('listaProcesos').value;
    var idOperador = document.getElementById('listaOperadores').value;
    var idDcto = document.getElementById('ordenBusqueda').value;
    if (idDcto.indexOf("-") > -1)
    {
        var item = idDcto.split('-')[1];
        var idDcto = idDcto.split('-')[0];
        getOneRecord2(idProceso, idDcto, item);
    } else {
        $.post('TouchEdicionOrdenServlet', {xopcion: 'printHistorial', fechaInicial: fechaInicial, fechaFinal: fechaFinal,
            idProceso: idProceso, idOperador: idOperador, idDcto: idDcto}, function (data) {
            $('#historialGlobal').html(data);
            document.getElementById('botonBuscarSpinner').hidden = true;
            document.getElementById('botonBuscarTraslado').hidden = false;
        });




    }

}

function getOneRecord2(idOperacion, numeroOrden, item)
{
    printListRetalByProcess(idOperacion);
    printListParoByProcess(idOperacion);
    document.getElementById('botonUpdate2').hidden = false;
    document.getElementById('botonUpdate1').hidden = true;

    $('#idOrden').val('');
    $('#item').val('');
    $('#numeroOrden').val('');
    $('#cliente').val('');
    $('#ficha').val('');
    $('#referencia').val('');
    $('#fechaInicio').val('');
    $('#fechaFin').val('');
    $('#cantFin').val('');
    $('#pesoFin').val('');
    $('#tara').val('');
    $('#retal').val('');
    $('#paro').val('');
    $('#observacion').val('');
    document.getElementById('listaOperadores2').value = '';
    document.getElementById('listaProcesos2').value = '';
    document.getElementById('observacion').value = '';
    $.post('TouchEdicionOrdenServlet', {xopcion: 'getOneOT2', numeroOrden: numeroOrden, item: item, idOperacion: idOperacion}, function (e) {

        var cadenaArray = e.split(',');
        var i = 0;
        var idOrden = cadenaArray[i++];
        if (idOrden != 0) {
            $('#modal-ref-cliente').modal('show');
            $('#idOrden').val(idOrden);
            $('#item').val(cadenaArray[i++]);
            $('#numeroOrden').val(cadenaArray[i++]);
            $('#cliente').val(cadenaArray[i++]);
            $('#ficha').val(cadenaArray[i++]);
            $('#referencia').val(cadenaArray[i++]);
            $('#fechaInicio').val(cadenaArray[i++]);
            $('#fechaFin').val(cadenaArray[i++]);
            $('#cantFin').val(cadenaArray[i++]);
            $('#pesoFin').val(cadenaArray[i++]);
            $('#tara').val(cadenaArray[i++]);
            var retal = cadenaArray[i++];
            $('#retal').val(retal);
            document.getElementById('listaOperadores2').value = parseInt(cadenaArray[i++]);
            var idOperacion = parseInt(cadenaArray[i++]);
            document.getElementById('listaProcesos2').value = idOperacion;
            var idMaquina = cadenaArray[i++];
            var observacion = cadenaArray[i++];
            var tiempoPerdido = cadenaArray[i++];
            $('#paro').val(tiempoPerdido);
            var idCausa = cadenaArray[i++];

            if (retal == '0.0' && tiempoPerdido == '0.0') {
                //Es una produccion
                document.getElementById('retal').disabled = true;
                document.getElementById('paro').disabled = true;
                document.getElementById('cantFin').disabled = false;
                document.getElementById('pesoFin').disabled = false;
                document.getElementById('tara').disabled = false;
                document.getElementById('grupoProduccion').hidden = false;
                document.getElementById('grupoRetal').hidden = true;
                document.getElementById('grupoParo').hidden = true;
                document.getElementById('listaRetal').value = 0;
                document.getElementById('listaParo').value = 0;



            } else if (retal == '0.0' && tiempoPerdido != '0.0') {
                //Es un paro
                document.getElementById('retal').disabled = true;
                document.getElementById('paro').disabled = false;
                document.getElementById('cantFin').disabled = true;
                document.getElementById('pesoFin').disabled = true;
                document.getElementById('tara').disabled = true;
                document.getElementById('listaParo').value = parseInt(idCausa);
                document.getElementById('listaRetal').value = 0;
                document.getElementById('grupoProduccion').hidden = true;
                document.getElementById('grupoRetal').hidden = true;
                document.getElementById('grupoParo').hidden = false;


            } else
            {
                //Retal
                document.getElementById('retal').disabled = false;
                document.getElementById('paro').disabled = true;
                document.getElementById('cantFin').disabled = true;
                document.getElementById('pesoFin').disabled = true;
                document.getElementById('tara').disabled = true;
                document.getElementById('listaRetal').value = parseInt(idCausa);
                document.getElementById('listaParo').value = 0;
                document.getElementById('grupoProduccion').hidden = true;
                document.getElementById('grupoRetal').hidden = false;
                document.getElementById('grupoParo').hidden = true;


            }

            if (observacion != '') {
                $('#observacion').val(observacion);
            }


            printListaMaquinasEdicion(idMaquina);
        } else {
            $('#modal-ref-cliente').modal('hide');
            alert("Numero de Orden no existe con ese item y/o operacion verifique los datos e intente nuevamente");
        }
        document.getElementById('botonBuscarSpinner').hidden = true;
        document.getElementById('botonBuscarTraslado').hidden = false;


    });
}
function getOneRecord(idOperacion, idOrden, item)
{
    printListRetalByProcess(idOperacion);
    printListParoByProcess(idOperacion);
    document.getElementById('botonUpdate2').hidden = true;
    document.getElementById('botonUpdate1').hidden = false;
    $('#idOrden').val('');
    $('#idOrden').val('');
    $('#item').val('');
    $('#numeroOrden').val('');
    $('#cliente').val('');
    $('#ficha').val('');
    $('#referencia').val('');
    $('#fechaInicio').val('');
    $('#fechaFin').val('');
    $('#cantFin').val('');
    $('#pesoFin').val('');
    $('#tara').val('');
    $('#paro').val('0.0');
    $('#retal').val('');
    $('#observacion').val('');
    document.getElementById('listaOperadores2').value = '';
    document.getElementById('listaProcesos2').value = '';
    document.getElementById('observacion').value = '';
    $.post('TouchEdicionOrdenServlet', {xopcion: 'getOneOT', idOrden: idOrden, item: item, idOperacion: idOperacion}, function (e) {

        var cadenaArray = e.split(',');
        var i = 0;
        $('#idOrden').val(cadenaArray[i++]);
        $('#item').val(cadenaArray[i++]);
        $('#numeroOrden').val(cadenaArray[i++]);
        $('#cliente').val(cadenaArray[i++]);
        $('#ficha').val(cadenaArray[i++]);
        $('#referencia').val(cadenaArray[i++]);
        $('#fechaInicio').val(cadenaArray[i++]);
        $('#fechaFin').val(cadenaArray[i++]);
        $('#cantFin').val(cadenaArray[i++]);
        $('#pesoFin').val(cadenaArray[i++]);
        $('#tara').val(cadenaArray[i++]);
        var retal = cadenaArray[i++];
        $('#retal').val(retal);
        document.getElementById('listaOperadores2').value = parseInt(cadenaArray[i++]);
        var idOperacion = parseInt(cadenaArray[i++]);
        document.getElementById('listaProcesos2').value = idOperacion;
        var idMaquina = cadenaArray[i++];
        var observacion = cadenaArray[i++];
        var tiempoPerdido = cadenaArray[i++];
        $('#paro').val(tiempoPerdido);
        var idCausa = cadenaArray[i++];
        var idTurno = cadenaArray[i++];
        var fechaProduccion = cadenaArray[i++];
        document.getElementById('listaTurnos').value = parseInt(idTurno);
        $('#fechaProduccion').val(fechaProduccion);

        if (retal == '0.0' && tiempoPerdido == '0.0') {
            //Es una produccion
            document.getElementById('retal').disabled = true;
            document.getElementById('paro').disabled = true;
            document.getElementById('cantFin').disabled = false;
            document.getElementById('pesoFin').disabled = false;
            document.getElementById('tara').disabled = false;
            document.getElementById('grupoProduccion').hidden = false;
            document.getElementById('grupoRetal').hidden = true;
            document.getElementById('grupoParo').hidden = true;
            document.getElementById('listaRetal').value = 0;
            document.getElementById('listaParo').value = 0;



        } else if (retal == '0.0' && tiempoPerdido != '0.0') {
            //Es un paro
            document.getElementById('retal').disabled = true;
            document.getElementById('paro').disabled = false;
            document.getElementById('cantFin').disabled = true;
            document.getElementById('pesoFin').disabled = true;
            document.getElementById('tara').disabled = true;
            document.getElementById('listaParo').value = parseInt(idCausa);
            document.getElementById('listaRetal').value = 0;
            document.getElementById('grupoProduccion').hidden = true;
            document.getElementById('grupoRetal').hidden = true;
            document.getElementById('grupoParo').hidden = false;


        } else
        {
            //Retal
            document.getElementById('retal').disabled = false;
            document.getElementById('paro').disabled = true;
            document.getElementById('cantFin').disabled = true;
            document.getElementById('pesoFin').disabled = true;
            document.getElementById('tara').disabled = true;
            document.getElementById('listaRetal').value = parseInt(idCausa);
            document.getElementById('listaParo').value = 0;
            document.getElementById('grupoProduccion').hidden = true;
            document.getElementById('grupoRetal').hidden = false;
            document.getElementById('grupoParo').hidden = true;


        }
//        if (idOperacion == '5' || idOperacion == '6') {
//            document.getElementById('listaTurnos').disable = false;
//            document.getElementById('fechaProduccion').disable = false;
//
//        } else {
//            document.getElementById('listaTurnos').disable = true;
//            document.getElementById('fechaProduccion').disable = true;
//
//        }

        if (observacion != '') {
            $('#observacion').val(observacion);
        }

        printListaMaquinasEdicion(idMaquina);

    });
}



function printListaMaquinasEdicion(idMaquina) {
    var idOperacion = document.getElementById('listaProcesos2').value;
    $.post('TouchRetalServlet', {xopcion: 'printMaquinas', sk_proceso: idOperacion}, function (e) {
        $('#listaMaquinas2').html(e);
        document.getElementById('listaMaquinas2').value = parseInt(idMaquina);

    });
}
function save(printListHist) {

    var idOrden = document.getElementById('idOrden').value;
    var item = document.getElementById('item').value;
    var fechaInicio = document.getElementById('fechaInicio').value;
    var fechaFin = document.getElementById('fechaFin').value;
    var cantFin = document.getElementById('cantFin').value;
    var pesoFin = document.getElementById('pesoFin').value;
    var tara = document.getElementById('tara').value;
    var retal = document.getElementById('retal').value;
    var idOperador = document.getElementById('listaOperadores2').value;
    var idOperacion = document.getElementById('listaProcesos2').value;
    var idMaquina = document.getElementById('listaMaquinas2').value;
    var observacion = document.getElementById('observacion').value;
    var tiempoPerdido = document.getElementById('paro').value;
    var idCausaParo = document.getElementById('listaParo').value;
    var idCausaRetal = document.getElementById('listaRetal').value;
    var idTurno = document.getElementById('listaTurnos').value;
    var fechaProduccion = document.getElementById('fechaProduccion').value;

    if (tara == ''
            || retal == ''
            || fechaInicio == ''
            || fechaFin == ''
            || cantFin == ''
            || pesoFin == ''
             ||fechaProduccion == '') {
        alert('No puede ingresar valores vacios');

    } else {
        $('#modal-ref-cliente').modal('hide');
        $.post('TouchEdicionOrdenServlet', {xopcion: 'save',
            idOrden: idOrden,
            item: item, fechaInicio: fechaInicio,
            fechaFin: fechaFin, cantFin: cantFin,
            pesoFin: pesoFin, tara: tara,
            retal: retal, idOperador: idOperador,
            idOperacion, idOperacion,
            idMaquina: idMaquina,
            observacion: observacion,
            tiempoPerdido: tiempoPerdido,
            idCausaParo: idCausaParo,
            idCausaRetal: idCausaRetal,
            idTurno: idTurno,
            fechaProduccion: fechaProduccion


        }, function (e) {
            if (printListHist)
            {
                printListHistory();
            } else {

                $('#historialGlobal').html('');
            }

            $('.toast').toast('show');



        });

    }
}
function deleteRecord(idOrden, idOperacion, item) {
    var idOrden = document.getElementById('idOrdenM').value;
    var idOperacion = document.getElementById('idOperacionM').value;
    var item = document.getElementById('itemM').value;
    $.post('TouchEdicionOrdenServlet', {xopcion: 'deleteMove',
        idOrden: idOrden,
        item: item,
        idOperacion: idOperacion
    }, function (e) {
        $('#historialGlobal').html(e);
        printListHistory();
    });
}


function colocarDatosModalDelete(idOrden, idOperacion, item) {

    $('#idOrdenM').val(idOrden);
    $('#idOperacionM').val(idOperacion);
    $('#itemM').val(item);

}

function printListRetalByProcess(idOperacion) {
    $.post('TouchRetalServlet', {xopcion: 'printRetalesDesplegable', sk_proceso: idOperacion}, function (e) {
        $('#listaRetal').html(e);
    });

}
function printListParoByProcess(idOperacion) {
    $.post('TouchParoServlet', {xopcion: 'printListaParoDesplegable', sk_proceso: idOperacion}, function (e) {
        $('#listaParo').html(e);
    });

}