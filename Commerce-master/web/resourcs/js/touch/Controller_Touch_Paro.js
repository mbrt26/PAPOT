/*   Document   :TouchRetalServlet
 Created on : 15-jul-2019, 10:18:03
 Author     : Edgar J. García L.*/
function cargar() {
    document.getElementById('botonInsertar').disabled = false;

    var idProceso = document.getElementById('listaProcesos').value;
    if (idProceso == '2' || idProceso == '3') {
        document.getElementById("listaTurnos").disabled = true;
        document.getElementById("fechaProduccion").disabled = true;
        document.getElementById("listaTurnos").selectedIndex = 0;
        document.getElementById("fechaProduccion").value = "";
    } else {

        document.getElementById("listaTurnos").disabled = false;
        document.getElementById("fechaProduccion").disabled = false;
    }
    document.getElementById('orden').focus();
    $('.alerta').alert('dispose');
    printListlByProcess();
}
function clickOperador() {
    $('#radioButtonO1').click();
}
function copiarOperador(id) {
    document.getElementById('idOperadorSelected').value = document.getElementById('radioButtonO' + id).value;
    document.getElementById('nombreOperadorSelected').value = document.getElementById('inputO' + id).value;
}

function copiarRetal(id) {
    document.getElementById('idRetalSelected').value = document.getElementById('radioButtonR' + id).value;
    document.getElementById('nombreRetalSelected').value = document.getElementById('inputR' + id).value;

}

function clickRetal() {
    $('#radioButtonR1').click();
}
function confirmarRetalT(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    var cantidad = document.getElementById('cantidadRetal').value;
    if (cantidad == '') {
        document.getElementById('botonInsertar').disabled = true;
    } else {
        var xCantidad = parseFloat(cantidad);
        document.getElementById('botonInsertar').disabled = false;
        var guardar = true;
    }
    if (tecla == 13) {
        if (guardar) {
            guardarRetal();
        }
    }
}
function focusCantRetal() {
    var idProceso = document.getElementById('listaProcesos').value;
    var validado = $("#formularioParo").valid();
    var listaTurnos = document.getElementById("listaTurnos");

    if (validado && (listaTurnos.selectedIndex !== 0 || !(idProceso == '6' || idProceso == '5'))) {
        $('#bd-example-modal-sm').modal('show');
        $('#bd-example-modal-sm').on('shown.bs.modal', function () {
            document.getElementById('ordenModal').value = document.getElementById('orden').value;
            var options = document.getElementById("listaProcesos").options;
            var index = document.getElementById("listaProcesos").selectedIndex;
            document.getElementById("procesoModal").value = options[parseInt(index)].text;
            var options = document.getElementById("listaMaquinas").options;
            index = document.getElementById("listaMaquinas").selectedIndex;
            document.getElementById("maquinaModal").value = options[parseInt(index)].text;
            document.getElementById("operarioModal").value = document.getElementById('nombreOperadorSelected').value;
            document.getElementById("retalModal").value = document.getElementById('nombreRetalSelected').value;
            $('#cantidadRetal').focus();
        });
    } else if (listaTurnos.selectedIndex === 0 && (idProceso == '6' || idProceso == '5')) {
        alert("Por favor, seleccione un turno");
    }
}

function printListlByProcess() {
    document.getElementById('guardarRetal').disabled = true;
    var idProceso = document.getElementById('listaProcesos').value;
    $.post('TouchParoServlet', {xopcion: 'printParos', sk_proceso: idProceso}, function (e) {
        $('#listaRetales').html(e);
        copiarRetal(1);
        printListOperatorslByProcess();
    });

}
function printListaMaquinas() {
    var idProceso = document.getElementById('listaProcesos').value;
    $.post('TouchRetalServlet', {xopcion: 'printMaquinas', sk_proceso: idProceso}, function (e) {
        $('#listaMaquinas').html(e);
        document.getElementById('guardarRetal').disabled = false;
    });
}
function printListaMaquinasReporteUnaVez() {
    var idProceso = document.getElementById('listaProcesos').value;
    $.post('TouchRetalServlet', {xopcion: 'printMaquinasReporte', sk_proceso: idProceso}, function (e) {
        $('#listaMaquinas').html(e);
        consultarGlobal();
    });
    //
}

function printListaMaquinasReporte() {
    var idProceso = document.getElementById('listaProcesos').value;
    $.post('TouchRetalServlet', {xopcion: 'printMaquinasReporte', sk_proceso: idProceso}, function (e) {
        $('#listaMaquinas').html(e);

    });

}

function recargarPagina() {
    document.getElementById('listaProcesos').disabled = false;
    document.getElementById('listaMaquinas').disabled = false;
    document.getElementById('orden').disabled = false;
    document.getElementById('alerta').hidden = true;

}
function printListOperatorslByProcess() {
    var idProceso = document.getElementById('listaProcesos').value;
    $.post('TouchRetalServlet', {xopcion: 'printOperadores', sk_proceso: idProceso}, function (e) {
        $('#listaOperarios').html(e);
        copiarOperador(1);
        printListaMaquinas();
    });
}
$(document).ready(function () { //Hacia arriba
    irArriba();
    validarFormulario();
});

function miFuncion() {
    guardarRetal();
}
function focusObservacion(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        document.getElementById('ControlTextarea').focus();
    }
}
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
function guardarRetal() {
    document.getElementById('guardarRetal').hidden = true;
    document.getElementById('botonSpinner').hidden = false;

    $('#bd-example-modal-sm').modal('hide');
    var idProceso = document.getElementById('listaProcesos').value;
    var idMaquina = document.getElementById('listaMaquinas').value;
    var idDcto = document.getElementById('orden').value;
    var idRetal = document.getElementById('idRetalSelected').value;
    var idOperador = document.getElementById('idOperadorSelected').value;
    var comentario = document.getElementById('ControlTextarea').value;
    var cantidad = document.getElementById('cantidadRetal').value;
     var listaTurnos = document.getElementById("listaTurnos").value;
    var fechaProduccion = document.getElementById("fechaProduccion").value;
    $.post('TouchParoServlet', {xopcion: 'guardarRetal', 
        idProceso: idProceso, 
        idDcto: idDcto,
        idRetal: idRetal, 
        idOperador: idOperador, 
        comentario: comentario, 
        cantidad: cantidad, 
        idMaquina: idMaquina,
        fechaProduccion: fechaProduccion, 
        listaTurnos: listaTurnos
        },
            function (e) {
                var cadenaArray = e.split(',');
                document.getElementById('guardarRetal').hidden = false;
                document.getElementById('botonSpinner').hidden = true;
                if (cadenaArray[0] == 'true') {
                    document.getElementById('alerta').hidden = false;
                } else {
                    document.getElementById('mensajeError').innerText = cadenaArray[1];
                    document.getElementById('alertaError').hidden = false;
                }
                document.getElementById('ir-arriba').click();
            });
}
function validarFormulario() {
    jQuery.validator.messages.required = 'Obligatorio';
    jQuery.validator.messages.number = 'El valor debe ser numerico.';
}

function pulsar(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    return (tecla != 13);
}
function clickHistorico() {
    document.getElementById('botonBuscarTraslado').click();
}


function consultarGlobal() {

//Ahora combinamos los resultados para construir la hora con el formato que deseamos.

    document.getElementById('botonBuscarSpinner').hidden = false;
    document.getElementById('botonBuscarTraslado').hidden = true;
    var fechaInicial = document.getElementById('datepicker').value;
    var fechaFinal = document.getElementById('datepicker2').value;
    var idProceso = document.getElementById('listaProcesos').value;
    var idMaquina = document.getElementById('listaMaquinas').value;
    var idOperador = document.getElementById('listaOperadores').value;
    var agrupar = document.getElementById('checkAgrupar').checked;
    var idDcto = document.getElementById('ordenBusqueda').value;
    $.post('TouchRetalServlet', {xopcion: 'printHistorialGlobal', fechaInicial: fechaInicial, fechaFinal: fechaFinal,

        idProceso: idProceso, idMaquina: idMaquina, idOperador: idOperador, idDcto: idDcto, agrupar: agrupar}, function (data) {
        $('#historialGlobal').html(data);
        printCharts(fechaInicial, fechaFinal, idProceso, idMaquina, idOperador, idDcto);
    });

}


function formatAMPM(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'pm' : 'am';
    ampm = ampm.toUpperCase();
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0' + minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return strTime;
}

function loadWaitingSpinner() {
    document.getElementById('botonExcelDetallado').click();
    document.getElementById('botonBuscarSpinner2').hidden = false;
    document.getElementById('botonExcel').hidden = true;
    checkCookie();
}
function loadWaitingSpinnerGlobal() {
    document.getElementById('botonExcelGlobal').click();
    document.getElementById('botonBuscarSpinner2').hidden = false;
    document.getElementById('botonExcel').hidden = true;
    checkCookie();
}
function  checkCookie() {
    var verif = setInterval(isWaitingCookie, 500, verif);
}
function isWaitingCookie(verif) {
    var loadState = getCookie("waitingCookie");
    if (loadState == "done") {
        clearInterval(verif);
        document.cookie = "waitingCookie=done; expires=Tue, 31 Dec 1985 21:00:00 UTC;";
        removeWaitingSpinner();
    }
}
function getCookie(cookieName) {
    var name = cookieName + "=";
    var cookies = document.cookie
    var cs = cookies.split(';');
    for (var i = 0; i < cs.length; i++) {
        var c = cs[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function removeWaitingSpinner() {
    document.getElementById('botonBuscarSpinner2').hidden = true;
    document.getElementById('botonExcel').hidden = false;
}
function quitarEspera() {
    document.getElementById('botonBuscarSpinner2').hidden = true;
    document.getElementById('botonExcel').hidden = false;
}

var myChart;
var myPieChart;
function printCharts(fechaInicial, fechaFinal, idProceso, idMaquina, idOperador, idDcto) {
    $.post('TouchRetalServlet', {xopcion: 'printBarras', fechaInicial: fechaInicial, fechaFinal: fechaFinal,
        idProceso: idProceso, idMaquina: idMaquina, idOperador: idOperador, idDcto: idDcto}, function (e) {
        var arregloConjunto = e.split('*');
        var arregloMaquinas = arregloConjunto[0];
        var arregloRetales = arregloConjunto[1];
        var xArregloMaquinas = arregloMaquinas.split(',');
        var xArregloRetales = arregloRetales.split(',');
        var ctx = document.getElementById('myBarChart').getContext('2d');
        ctx.innerHTML = "";
        if (myChart)
        {
            myChart.destroy();
        }
        myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: xArregloMaquinas,
                datasets: [{
                        data: xArregloRetales,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                }, legend: {
                    display: false
                }

            }

        });
        //consultarHistorico();
        drawPie(fechaInicial, fechaFinal, idProceso, idMaquina, idOperador, idDcto);

    });

}
function drawPie(fechaInicial, fechaFinal, idProceso, idMaquina, idOperador, idDcto) {
    $.post('TouchRetalServlet', {xopcion: 'printPie', fechaInicial: fechaInicial, fechaFinal: fechaFinal,
        idProceso: idProceso, idMaquina: idMaquina, idOperador: idOperador, idDcto: idDcto}, function (e) {
        var arregloConjunto = e.split('*');
        var arregloMaquinas = arregloConjunto[0];
        var arregloRetales = arregloConjunto[1];
        var xArregloMaquinas = arregloMaquinas.split(',');
        var xArregloRetales = arregloRetales.split(',');
        var ctx2 = document.getElementById("myPieChart");
        ctx2.innerHTML = "";
        if (myPieChart) {
            myPieChart.destroy();
        }
        myPieChart = new Chart(ctx2, {
            type: 'pie',
            data: {
                labels: xArregloMaquinas,
                datasets: [{
                        data: xArregloRetales,
                        backgroundColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                        ],
                    }],
            }, options: {
                legend: {
                    display: false
                }
            }
        });
        horaImprimible = formatAMPM(new Date());
        document.getElementById('pieBarra').innerHTML = "Actualizado hoy a las: " + horaImprimible;
        document.getElementById('piePie').innerHTML = "Actualizado hoy a las: " + horaImprimible;
        document.getElementById('pieTablaGlobal').innerHTML = "Actualizado hoy a las: " + horaImprimible;
        document.getElementById('pieTablaDetalle').innerHTML = "Actualizado hoy a las: " + horaImprimible;
        consultarHistorico();
    });
}

function limpiarCanvas() {
    var canvas = document.getElementById('myBarChart');
    var contexto = canvas.getContext('2d');
    contexto.clearRect(0, 0, canvas.width, canvas.height);
}













