function cargarBodegas() {
    document.getElementById('bodega').focus();
    $.post('OperacionesServlet', {xopcion: 'listaBodegas'}, function (e) {
        $('#detalle').html(e);
        actTablaParo();
        actTablaRetal();
        actTablaUnidad();
        actTablaMateria();
        actTablaAccesorio();
    });
}
function creaBodega() {
    var bod = document.getElementById('bodega').value;
    var servicios = document.getElementById('servicios').value;
    var conteo = document.getElementById('listaConteo').value;
    if (servicios == "")
        servicios = '0';
    var arriendo = document.getElementById('arriendo').value;
    if (arriendo == "")
        arriendo = '0';
    var mano_obra = document.getElementById('manoObra').value;
    if (mano_obra == "")
        mano_obra = '0';
    var costo_retal = document.getElementById('CostoProceso').value;
    if (costo_retal == "")
        costo_retal = '0';
    if (bod == "") {
        alert('El nombre no es valido ingrese nombre valido');
        $('#bodega').focus();
    } else {
        $.post('OperacionesServlet', {xopcion: 'crear', xbodega: bod, servicios: servicios, arriendo: arriendo, mano_obra: mano_obra, conteo: conteo, costo_retal: costo_retal}, function (e) {
            $('#detalle').html(e);
            $('#bodega').val('');
            $('#servicios').val('');
            $('#arriendo').val('');
            $('#manoObra').val('');
            $('#CostoProceso').val('');
            $('body,html').animate({scrollTop: '2000px'}, 1000);
            $('#bodega').focus();
        });
    }
}
function editar(_id) {
    $.post('OperacionesServlet', {xopcion: 'ListaBodegaEditar', xskmarca: _id}, function (e) {
        var cadenaArray = e.split(',');
        $('#skbodegaactualiza').val(cadenaArray[0]);
        $('#actualizabodega').val(cadenaArray[1]);
        $('#xServicio').val(cadenaArray[2]);
        $('#xArriendo').val(cadenaArray[3]);
        $('#xManoObra').val(parseFloat(cadenaArray[4]));
        $('#listaConteo2').val(parseFloat(cadenaArray[5]));
        $('#xCostoRetalModal').val(parseFloat(cadenaArray[6]));

        $('#edita').on('shown.bs.modal', function () {
            $('#actualizabodega').focus();
        });
    });
}
function editaMateria(id) {
    $.post('OperacionesServlet', {xopcion: 'MateriaEditar', xskmarca: id}, function (e) {
        var cadenaArray = e.split(',');
        // alert(cadenaArray);
        $('#sk_materia').val(cadenaArray[0]);
        $('#nombreMateriax').val(cadenaArray[1]);
        $('#costoxA').val(cadenaArray[2]);
        $('#editaMateria').on('shown.bs.modal', function () {
           
        });
    });
}
function borraMateria(id) {
    $.post('OperacionesServlet', {xopcion: 'borraMateria', xskmarca: id}, function (e) {
       $('#detalleMateria').html(e);     
     
    });
}
function crearParo() {
    var sk_proceso = document.getElementById('listaProcesos1').value;
    var sk_paro_maquina = document.getElementById('listaParos').value;
    $.post('OperacionesServlet', {xopcion: 'insertarParoMaquina', sk_proceso: sk_proceso, sk_paro_maquina: sk_paro_maquina}, function (e) {
        $('#detalleParo').html(e);
        $('body,html').animate({scrollTop: '2000px'}, 1000);
    });
}
function crearRetal() {
    var sk_proceso = document.getElementById('listaProcesos2').value;
    var sk_retal = document.getElementById('listaRetal').value;
    $.post('OperacionesServlet', {xopcion: 'insertarRetal', sk_proceso: sk_proceso, sk_retal: sk_retal}, function (e) {
        $('#detalleRetal').html(e);
        $('body,html').animate({scrollTop: '2000px'}, 1000);
    });
}
function crearUnidad() {
    var sk_proceso = document.getElementById('listaProcesos3').value;
    var sk_unidad = document.getElementById('listaUnidad3').value;
    $.post('OperacionesServlet', {xopcion: 'insertarUnidad', sk_proceso: sk_proceso, sk_unidad: sk_unidad}, function (e) {
        $('#detalleUnidad').html(e);
        $('body,html').animate({scrollTop: '2000px'}, 1000);
    });
}
function actTablaParo() {
    $.post('OperacionesServlet', {xopcion: 'actTabParo'}, function (e) {
        $('#detalleParo').html(e);
        //   $('body,html').animate({scrollTop: '2000px'}, 1000);
    });
}
function crearMateriaPrima() {
    var nombreMateriaPrima = document.getElementById('nombreMatPrima').value;
    var costoMateriaPrima = document.getElementById('costoMatPrima').value;

    document.getElementById('nombreMatPrima').value = "";
    document.getElementById('costoMatPrima').value = "";
    $.post('OperacionesServlet', {xopcion: 'crearMateria', matPrima: nombreMateriaPrima, costo: costoMateriaPrima}, function (e) {

        $('#detalleMateria').html(e);

    });
}
function actMateria() {

    var nombre = document.getElementById('nombreMateriax').value;
    var costo = document.getElementById('costoxA').value;
    var sk = document.getElementById('sk_materia').value;

    $.post('OperacionesServlet', {xopcion: 'ActualizarNombreMateria', xnombre: nombre, costo: costo, sk: sk}, function (e) {
        $('#detalleMateria').html(e);
    });

}
function actTablaUnidad() {
    $.post('OperacionesServlet', {xopcion: 'actTabUnidad'}, function (e) {
        $('#detalleUnidad').html(e);
    });
}
function actTablaRetal() {
    $.post('OperacionesServlet', {xopcion: 'actTabRetal'}, function (e) {
        $('#detalleRetal').html(e);
        //    $('body,html').animate({scrollTop: '2000px'}, 1000);
    });
}
function actTablaMateria() {
    $.post('OperacionesServlet', {xopcion: 'actTabMateria'}, function (e) {
        $('#detalleMateria').html(e);
        
    });
}
function actTablaAccesorio() {
    $.post('OperacionesServlet', {xopcion: 'actTabAccesorio'}, function (e) {
        $('#detalleAccesorios2').html(e);
        //    $('body,html').animate({scrollTop: '2000px'}, 1000);
    });
}
function ActualizarOperacion() {
    var nombre = document.getElementById('actualizabodega').value;
    var bodega = document.getElementById('skbodegaactualiza').value;
    var servicio = document.getElementById('xServicio').value;
    var arriendo = document.getElementById('xArriendo').value;
    var mano_obra = document.getElementById('xManoObra').value;
    var conteo = document.getElementById('listaConteo2').value;
    var costo = document.getElementById('xCostoRetalModal').value;
    $.post('OperacionesServlet', {xopcion: 'ActualizarNombre', xnombre: nombre, skbodega: bodega, servicio: servicio, arriendo: arriendo, manoObra: mano_obra, conteo: conteo, costo: costo}, function (e) {
        $('#detalle').html(e);
    });
}
function verificarEnterMod(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        $('#edita').modal('hide');
        ActualizarOperacion();
    }
}
function eliminar(_id) {
    $.post('OperacionesServlet', {xopcion: 'EliminaBodega', skbodega: _id}, function (e) {
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
function focusNext(_parametro, e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        document.getElementById(_parametro).focus();

    }

}
function buscarProductoDentroModal() {
    var xbuscar = document.getElementById('campoProductoModal').value;
    $.post('OperacionesServlet', {xopcion: 'BuscarProductosEnModal', xproducto: xbuscar}, function (e) {
        $('#detalleModal').html(e);
        document.getElementById('campoProductoModal').focus();
    });
}
function agregar(_sk_plu) {

    var sk_operacion = document.getElementById('listaProcesos4').value;
    $.post('OperacionesServlet', {xopcion: 'agregarPLU', sk_plu: _sk_plu, sk_operacion: sk_operacion}, function (e) {
        actTablaMateria();
    });
}
function eliminar(_sk_proceso_materia) {
    $.post('OperacionesServlet', {xopcion: 'eliminarMateria', sk_proc_mat: _sk_proceso_materia}, function (e) {
        actTablaMateria();
    });
}
function eliminarParo(_sk_proceso_paro) {
    $.post('OperacionesServlet', {xopcion: 'eliminarParo', sk_proc_paro: _sk_proceso_paro}, function (e) {
        actTablaParo();
    });
}
function eliminarRetal(_sk_proceso_retal) {
    $.post('OperacionesServlet', {xopcion: 'eliminarRetal', sk_proc_retal: _sk_proceso_retal}, function (e) {
        actTablaRetal();
    });
}
function eliminarUnidad(_sk_proceso_unidad) {
    $.post('OperacionesServlet', {xopcion: 'eliminarUnidad', sk_proc_unidad: _sk_proceso_unidad}, function (e) {
        actTablaUnidad()();
    });
}
function buscarProductoDentroModalTecla(e) {
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) {
        buscarProductoDentroModal();
    }
}

 