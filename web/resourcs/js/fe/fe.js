function ListaFe() {
    $.post('FERestransmisionServlet', {xopcion: 'ListarFe'}, function (e) {
        $('#detalle').html(e);
    });
}

function Transmitir(tipoorden, orden, dcto, id) {
    document.getElementById('botonTransmitir' + id).hidden = true;
    document.getElementById('botonTransmitirSpinner' + id).hidden = false;
  
    let isReferenced = false; // Valor por defecto

    try {
        const checkbox = document.getElementById('isReferenced' + id);
        if (checkbox) {
            isReferenced = checkbox.checked;
        }
    } catch (error) {
        console.log("Checkbox de referencia no existe");
    }

    $.post('FERestransmisionServlet', {
        xopcion: 'TransmitirFactura',
        xorden: orden,
        xtipoorden: tipoorden,
        xdcto: dcto,
        isReferenced: isReferenced
    }, function (e) {
        var p = e.split('!');
        $('#detalle').html(p[0]);
        alert(p[1]);
        document.getElementById('botonTransmitir' + id).hidden = false;
        document.getElementById('botonTransmitirSpinner' + id).hidden = true;
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