function printParoTemporal() {
    var xIdDcto = document.getElementById('xIdDcto').value;
    var listaParos = document.getElementById('listaParos').value;
    var xIdOperario = document.getElementById('idOperario').value;
    var xObservacion = document.getElementById('xObservacion').value;
    var xPesoTerminado = document.getElementById('xPesoTerminado').value;
    var idProceso = document.getElementById('xIdOperacion').value;
    var listaMaquinas = document.getElementById('listaMaquinas').value;
    var xFechaHoraInicio = document.getElementById('xFechaHoraInicio').value;
    var xFechaHoraFin = document.getElementById('xFechaHoraFin').value;
    var tParada = document.getElementById('tParada').value;
    if (listaParos == "0") {
        alert("Seleccione un Paro");
        document.getElementById('listaParos').click();

    } else if (xPesoTerminado == "0" || xPesoTerminado == "") {
        alert("Coloque el peso terminado");
        document.getElementById('xPesoTerminado').focus();
                seleccionaTexto('xPesoTerminado');

    } else {
        if (xFechaHoraInicio == "") {
            alert("Debe Colocar Fecha/Hora Inicio");
            document.getElementById('xFechaHoraInicio').focus();

        } else {
            if (xFechaHoraFin == "") {
                alert("Debe colocar Fecha/Hora Fin");
                document.getElementById('xFechaHoraFin').focus();

            } else if (tParada == "0"||tParada == "") {
                alert("Debe colocar los minutos de parada");
                document.getElementById('tParada').focus();
                seleccionaTexto('tParada');

            } else {
                $.post('TouchParoServlet', {xopcion: 'guardarParo',
                    xIdDcto: xIdDcto, listaParos: listaParos, xIdOperario: xIdOperario,
                    xObservacion: xObservacion, xPesoTerminado: xPesoTerminado,
                    idProceso: idProceso, listaMaquinas: listaMaquinas,
                    xFechaHoraInicio: xFechaHoraInicio, xFechaHoraFin: xFechaHoraFin, tParada: tParada}, function (e) {
                    $('#tablaParo').html(e);
                    document.getElementById('tParada').value='0';   
                     document.getElementById('listaParos').value='0';
                });
            }
        }

    }
}
function printListaMaquinasReporteUnaVez() {
    var idProceso = document.getElementById('listaProcesos').value;
    $.post('TouchParoServlet', {xopcion: 'printMaquinasReporte', sk_proceso: idProceso}, function (e) {
        $('#listaMaquinas').html(e);
     //consultarHistorico();
//        consultarGlobal();
    });

}
function printListaMaquinas() {
    var idProceso = document.getElementById('listaProcesos').value;
    $.post('TouchParoServlet', {xopcion: 'printMaquinasReporte', sk_proceso: idProceso}, function (e) {
        $('#listaMaquinas').html(e);

    });
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
    $.post('TouchParoServlet', {xopcion: 'printHistorialGlobal', fechaInicial: fechaInicial, fechaFinal: fechaFinal,
        idProceso: idProceso, idMaquina: idMaquina, idOperador: idOperador, idDcto: idDcto, agrupar: agrupar}, function (data) {
        $('#historialGlobal').html(data);
        printCharts(fechaInicial, fechaFinal, idProceso, idMaquina, idOperador, idDcto);
        
    });
}

var myChart;
var myPieChart;
function printCharts(fechaInicial, fechaFinal, idProceso, idMaquina, idOperador, idDcto) {
    $.post('TouchParoServlet', {xopcion: 'printBarras', fechaInicial: fechaInicial, fechaFinal: fechaFinal,
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
    $.post('TouchParoServlet', {xopcion: 'printPie', fechaInicial: fechaInicial, fechaFinal: fechaFinal,
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
                            'rgba(153, 102, 255, 1)'                        ],
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

function consultarHistorico() {
    document.getElementById('botonBuscarSpinner').hidden = false;
    document.getElementById('botonBuscarTraslado').hidden = true;
    var fechaInicial = document.getElementById('datepicker').value;
    var fechaFinal = document.getElementById('datepicker2').value;
    var idProceso = document.getElementById('listaProcesos').value;
    var idMaquina = document.getElementById('listaMaquinas').value;
    var idOperador = document.getElementById('listaOperadores').value;
    var agrupar = document.getElementById('checkAgrupar').checked;
    var idDcto = document.getElementById('ordenBusqueda').value;
    $.post('TouchParoServlet', {xopcion: 'printHistorial', fechaInicial: fechaInicial, fechaFinal: fechaFinal,
        idProceso: idProceso, idMaquina: idMaquina, idOperador: idOperador, idDcto: idDcto, agrupar: agrupar}, function (e) {
        $('#historial').html(e);
        document.getElementById('botonBuscarSpinner').hidden = true;
        document.getElementById('botonBuscarTraslado').hidden = false;
    });

}

function seleccionaTexto(element) {
    var doc = document,
            text = doc.getElementById(element),
            range, 
    selection;
    if (doc.body.createTextRange) { //ms
        range = doc.body.createTextRange();
        range.moveToElementText(text);
        range.select();
    } else if (window.getSelection) { //all others
        selection = window.getSelection();
        range = doc.createRange();
        range.selectNodeContents(text);
        selection.removeAllRanges();
        selection.addRange(range);
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

function capturarOperario(idIoperario){
    document.getElementById('idOperario').value = idIoperario; 
//alert('se copio el operario');
}

$(document).ready(function () { //Hacia arriba
    irArriba();
    });
    
    
    