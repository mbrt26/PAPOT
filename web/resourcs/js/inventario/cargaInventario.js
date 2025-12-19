function subir() {
    document.getElementById("botonSubir").click(); // Click on the checkbox
}
function crearFoto() {
      var sk_bodega = document.getElementById("xBodega").value;
    $.post('CargarInventario', {xopcion: 'crear', sk_bodega: sk_bodega}, function (e) {
        $('#detalle').html(e);
    });
}
function subirExcel() {
    var file = document.getElementById("botonSubir").value;
    $.post('CargarInventario', {xopcion: 'subirExcel', file: file}, function (e) {
    });
}